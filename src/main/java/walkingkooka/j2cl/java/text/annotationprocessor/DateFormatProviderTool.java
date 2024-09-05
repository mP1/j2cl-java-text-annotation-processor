/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.j2cl.java.text.annotationprocessor;

import walkingkooka.ToStringBuilder;
import walkingkooka.collect.list.Lists;
import walkingkooka.collect.map.Maps;
import walkingkooka.collect.set.SortedSets;
import walkingkooka.j2cl.java.io.string.StringDataInputDataOutput;
import walkingkooka.j2cl.java.util.locale.support.LocaleSupport;
import walkingkooka.j2cl.locale.WalkingkookaLanguageTag;
import walkingkooka.j2cl.locale.annotationprocessor.LocaleAwareAnnotationProcessor;
import walkingkooka.j2cl.locale.annotationprocessor.LocaleAwareAnnotationProcessorTool;
import walkingkooka.text.CharSequences;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.Printer;
import walkingkooka.text.printer.Printers;

import java.io.DataOutput;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This tool prints a generated method to a {@link String} for inclusion in <code>DateFormatProvider.java.txt</code>
 */
public final class DateFormatProviderTool {

    public static void main(final String[] args) throws IOException {
        try (final Printer printer = Printers.sysOut()) {
            final StringBuilder data = new StringBuilder();
            generate("*",
                    WalkingkookaLanguageTag.locales("*"),
                    StringDataInputDataOutput.output(data::append),
                    LocaleAwareAnnotationProcessor.comments(printer));
            printer.print(CharSequences.quoteAndEscape(data));
            printer.flush();
        }
    }

    static String generate(final String filter,
                           final Set<Locale> locales,
                           final DataOutput data,
                           final IndentingPrinter comments) throws IOException {
        new DateFormatProviderTool(data, comments).generate0(locales);
        return LocaleAwareAnnotationProcessorTool.extractSummary(locales.size(),
                "Locale",
                filter);
    }

    private DateFormatProviderTool(final DataOutput data,
                                   final IndentingPrinter comments) {
        super();
        this.data = data;
        this.comments = comments;
    }

    private void generate0(final Set<Locale> locales) throws IOException {
        final Map<List<SimpleDateFormat>, Set<Locale>> formatsToLocales = Maps.sorted(DateFormatProviderTool::comparator);

        for (final Locale locale : locales) {
            final List<SimpleDateFormat> formats = Lists.array();

            formats.addAll(getDateInstance(locale));

            for (int dateStyle : styles()) {
                for (int timeStyle : styles()) {
                    formats.add((SimpleDateFormat) DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale));
                }
            }

            formats.addAll(getTimeInstance(locale));

            Set<Locale> formatLocales = formatsToLocales.get(formats);
            if (null == formatLocales) {
                formatLocales = SortedSets.tree(LocaleAwareAnnotationProcessorTool.LOCALE_COMPARATOR);
                formatsToLocales.put(formats, formatLocales);
            }
            formatLocales.add(locale);
        }

        final Map<Locale, List<SimpleDateFormat>> localeToFormats = Maps.sorted(LocaleAwareAnnotationProcessorTool.LOCALE_COMPARATOR);
        for (final Entry<List<SimpleDateFormat>, Set<Locale>> formatAndLocale : formatsToLocales.entrySet()) {
            localeToFormats.put(formatAndLocale.getValue().iterator().next(), formatAndLocale.getKey());
        }

        final DataOutput data = this.data;
        final IndentingPrinter comments = this.comments;
        data.writeInt(localeToFormats.size());

        for (final List<SimpleDateFormat> formats : localeToFormats.values()) {
            LocaleSupport.generateLocales(formatsToLocales.get(formats),
                    data,
                    comments);

            comments.lineStart();
            comments.print(comments.lineEnding());

            comments.indent();
            {
                final Iterator<SimpleDateFormat> iterator = formats.iterator();

                generateDateFormat(iterator.next(), "DateInstance SHORT");
                generateDateFormat(iterator.next(), "DateInstance MEDIUM");
                generateDateFormat(iterator.next(), "DateInstance LONG");
                generateDateFormat(iterator.next(), "DateInstance FULL");

                generateDateFormat(iterator.next(), "DateTimeInstance SHORT, SHORT");
                generateDateFormat(iterator.next(), "DateTimeInstance SHORT, MEDIUM");
                generateDateFormat(iterator.next(), "DateTimeInstance SHORT, LONG");
                generateDateFormat(iterator.next(), "DateTimeInstance SHORT, FULL");

                generateDateFormat(iterator.next(), "DateTimeInstance MEDIUM, SHORT");
                generateDateFormat(iterator.next(), "DateTimeInstance MEDIUM, MEDIUM");
                generateDateFormat(iterator.next(), "DateTimeInstance MEDIUM, LONG");
                generateDateFormat(iterator.next(), "DateTimeInstance MEDIUM, FULL");

                generateDateFormat(iterator.next(), "DateTimeInstance LONG, SHORT");
                generateDateFormat(iterator.next(), "DateTimeInstance LONG, MEDIUM");
                generateDateFormat(iterator.next(), "DateTimeInstance LONG, LONG");
                generateDateFormat(iterator.next(), "DateTimeInstance LONG, FULL");

                generateDateFormat(iterator.next(), "DateTimeInstance FULL, SHORT");
                generateDateFormat(iterator.next(), "DateTimeInstance FULL, MEDIUM");
                generateDateFormat(iterator.next(), "DateTimeInstance FULL, LONG");
                generateDateFormat(iterator.next(), "DateTimeInstance FULL, FULL");

                generateDateFormat(iterator.next(), "TimeInstance SHORT");
                generateDateFormat(iterator.next(), "TimeInstance MEDIUM");
                generateDateFormat(iterator.next(), "TimeInstance LONG");
                generateDateFormat(iterator.next(), "TimeInstance FULL");
            }
            comments.outdent();

            comments.print(comments.lineEnding());
        }
    }

    private static int comparator(final List<SimpleDateFormat> left,
                                  final List<SimpleDateFormat> right) {
        return toString(left).compareTo(toString(right));
    }

    private static String toString(final List<SimpleDateFormat> format) {
        return format.stream()
                .map(DateFormatProviderTool::toString)
                .collect(Collectors.joining("\n"));
    }

    private static String toString(final SimpleDateFormat format) {
        return ToStringBuilder.empty()
                .label("pattern").value(format.toPattern())
                .build();
    }

    private static List<SimpleDateFormat> getDateInstance(final Locale locale) {
        return simpleDateFormat(locale, SimpleDateFormat::getDateInstance);
    }

    private static List<SimpleDateFormat> getTimeInstance(final Locale locale) {
        return simpleDateFormat(locale, SimpleDateFormat::getTimeInstance);
    }

    private static List<SimpleDateFormat> simpleDateFormat(final Locale locale,
                                                           final BiFunction<Integer, Locale, DateFormat> factory) {
        return IntStream.of(styles())
                .mapToObj(s -> simpleDateFormat(s, locale, factory))
                .collect(Collectors.toList());
    }

    private static int[] styles() {
        return new int[]{DateFormat.SHORT, DateFormat.MEDIUM, DateFormat.LONG, DateFormat.FULL};
    }

    private static SimpleDateFormat simpleDateFormat(final int style,
                                                     final Locale locale,
                                                     final BiFunction<Integer, Locale, DateFormat> factory) {
        return (SimpleDateFormat) factory.apply(style, locale);
    }

    private void generateDateFormat(final SimpleDateFormat format,
                                    final String label) throws IOException {
        this.field(format.toPattern(), label, "pattern");
    }

    private void field(final String value, final String type, final String property) throws IOException {
        this.comments.lineStart();
        this.comments.print(type + " " + property + "=" + value);

        this.data.writeUTF(value);
    }

    private final DataOutput data;
    private final IndentingPrinter comments;
}
