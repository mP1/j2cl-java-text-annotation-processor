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

import walkingkooka.collect.map.Maps;
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
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This tool prints to sysout, that prints a <code></code>DateFormatSymbolsProvider#register()</code>
 */
public final class DateFormatSymbolsProviderTool {

    public static void main(final String[] args) throws IOException {
        try (final Printer printer = Printers.sysOut()) {
            final StringBuilder data = new StringBuilder();
            generate(WalkingkookaLanguageTag.locales("*"),
                    StringDataInputDataOutput.output(data::append),
                    LocaleAwareAnnotationProcessor.comments(printer));
            printer.print(CharSequences.quoteAndEscape(data));
            printer.flush();
        }
    }

    static void generate(final Set<Locale> locales,
                         final DataOutput data,
                         final IndentingPrinter comments) throws IOException {
        new DateFormatSymbolsProviderTool(data, comments).generate0(locales);
    }

    private DateFormatSymbolsProviderTool(final DataOutput data,
                                          final IndentingPrinter comments) {
        super();
        this.data = data;
        this.comments = comments;
    }

    private void generate0(final Set<Locale> locales) throws IOException {
        final Map<DateFormatSymbols, Set<Locale>> symbolToLocales = LocaleAwareAnnotationProcessorTool.buildMultiLocaleMap(
                DateFormatSymbolsProviderTool::dateFormatSymbolsComparator,
                DateFormatSymbols::getInstance,
                locales);

        final Map<Locale, DateFormatSymbols> localeToSymbols = Maps.sorted(LocaleAwareAnnotationProcessorTool.LOCALE_COMPARATOR);
        for (final Entry<DateFormatSymbols, Set<Locale>> languageTagAndSymbol : symbolToLocales.entrySet()) {
            localeToSymbols.put(languageTagAndSymbol.getValue().iterator().next(), languageTagAndSymbol.getKey());
        }

        final DataOutput data = this.data;
        final IndentingPrinter comments = this.comments;

        data.writeInt(localeToSymbols.size());

        for (final DateFormatSymbols symbols : localeToSymbols.values()) {
            LocaleSupport.generateLocales(symbolToLocales.get(symbols),
                    data,
                    comments);
            comments.indent();
            {
                this.field(symbols.getAmPmStrings(), 0, "ampm");
                this.field(symbols.getEras(), 0, "eras");
                this.field(symbols.getMonths(), 0, "months"); // add extra 13th
                this.field(symbols.getShortMonths(), 0, "shortMonths");
                this.field(symbols.getShortWeekdays(), 1, "shortWeekdays"); // add empty 1st.
                this.field(symbols.getWeekdays(), 1, "weekdays");
            }
            comments.outdent();
            comments.lineStart();
            comments.print(comments.lineEnding());
        }
    }

    private static int dateFormatSymbolsComparator(final DateFormatSymbols left,
                                                   final DateFormatSymbols right) {
        return toString(left).compareTo(toString(right));
    }

    private static String toString(final DateFormatSymbols symbols) {
        return Arrays.toString(symbols.getAmPmStrings()) + "," +
                Arrays.toString(symbols.getEras()) + "," +
                Arrays.toString(symbols.getMonths()) + "," +
                Arrays.toString(symbols.getShortMonths()) + "," +
                Arrays.toString(symbols.getShortWeekdays()) + "," +
                Arrays.toString(symbols.getWeekdays());
    }

    private void field(final String[] values,
                       final int offset,
                       final String label) throws IOException {
        this.comments.lineStart();
        this.comments.print(label + "=" + Arrays.stream(values).skip(offset).collect(Collectors.joining(", ")));

        this.data.writeInt(values.length - offset);
        for (int i = offset; i < values.length; i++) {
            this.data.writeUTF(values[i]);
        }
    }

    private final DataOutput data;
    private final IndentingPrinter comments;
}
