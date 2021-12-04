package walkingkooka.j2cl.java.text.annotationprocessor;

import walkingkooka.collect.list.Lists;
import walkingkooka.collect.set.Sets;
import walkingkooka.j2cl.java.io.string.StringDataInputDataOutput;
import walkingkooka.j2cl.locale.WalkingkookaLanguageTag;
import walkingkooka.j2cl.locale.annotationprocessor.LocaleAwareAnnotationProcessor;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.text.CharSequences;
import walkingkooka.text.LineEnding;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.Printer;
import walkingkooka.text.printer.Printers;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public abstract class ProviderToolTestCase<T> implements ClassTesting<T> {

    ProviderToolTestCase() {
        super();
    }

    final void generateAndCheck(final String filter,
                                final String expected) throws IOException {
        this.checkEquals(expected,
                generate(filter),
                () -> "filter=" + CharSequences.quoteAndEscape(filter));
    }

    final String generate(final String filter) throws IOException {
        final StringBuilder comments = new StringBuilder();
        final StringBuilder data = new StringBuilder();
        final LineEnding eol = LineEnding.NL;

        try (final Printer printer = Printers.stringBuilder(comments, eol)) {
            this.generate0(filter.isEmpty() ? Sets.empty() : WalkingkookaLanguageTag.all(filter),
                    StringDataInputDataOutput.output(data::append),
                    LocaleAwareAnnotationProcessor.comments(printer));
            printer.print(eol);
            printer.flush();
            printer.close();

            return "" + comments + eol + data;
        }
    }

    final String generateData(final String filter) throws IOException {
        final StringBuilder data = new StringBuilder();
        final LineEnding eol = LineEnding.NL;

        try (final Printer printer = Printers.sink()) {
            this.generate0(filter.isEmpty() ? Sets.empty() : WalkingkookaLanguageTag.all(filter),
                    StringDataInputDataOutput.output(data::append),
                    LocaleAwareAnnotationProcessor.comments(printer));
            printer.print(eol);
            printer.flush();
            printer.close();

            return "" + data;
        }
    }

    final Set<Locale> readLocales(final DataInput data) throws IOException {
        final Set<Locale> locales = Sets.ordered();
        final int count = data.readInt();

        for (int i = 0; i < count; i++) {
            locales.add(this.readLocale(data));
        }

        return locales;
    }

    final Locale readLocale(final DataInput data) throws IOException {
        final String tag = data.readUTF();
        final Locale locale = Locale.forLanguageTag(tag);
        final String language = locale.getLanguage();

        if (WalkingkookaLanguageTag.oldToNewLanguage(language).equals(language) && false == locale.toLanguageTag().equals("und")) {
            this.checkEquals(tag, locale.toLanguageTag(), () -> "invalid locale " + CharSequences.quoteAndEscape(tag));
        }
        return locale;
    }

    final List<String> readStrings(final DataInput data) throws IOException {
        final List<String> array = Lists.array();
        final int count = data.readInt();

        for (int i = 0; i < count; i++) {
            array.add(data.readUTF());
        }

        return array;
    }

    abstract void generate0(final Set<String> locales,
                            final DataOutput data,
                            final IndentingPrinter comments) throws IOException;

    @Override
    public final JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
