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
import walkingkooka.collect.set.Sets;
import walkingkooka.j2cl.locale.WalkingkookaLanguageTag;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.Printer;
import walkingkooka.text.printer.Printers;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This tool prints to sysout, that prints a <code></code>DateFormatSymbolsProvider#register()</code>
 */
public final class DateFormatSymbolsProviderTool extends LocaleProviderTool {

    public static void main(final String[] args) {
        final IndentingPrinter printer = Printers.sysOut().indenting(Indentation.with("  "));
        new DateFormatSymbolsProviderTool(printer).print(WalkingkookaLanguageTag.all("EN*"));
        printer.flush();
    }

    static String generateMethod(final Set<String> languageTags) {
        final StringBuilder output = new StringBuilder();
        try (final Printer printer = Printers.stringBuilder(output, LineEnding.SYSTEM)) {
            new DateFormatSymbolsProviderTool(printer.indenting(Indentation.with("  "))).print(languageTags);
        }

        return output.toString();
    }

    private DateFormatSymbolsProviderTool(final IndentingPrinter printer) {
        super(printer);
    }

    @Override
    void print0(final Set<String> languageTags) {
        final Map<DateFormatSymbols, Set<String>> symbolToLanguageTags = Maps.sorted(DateFormatSymbolsProviderTool::dateFormatSymbolsComparator);

        for (final String languageTag : languageTags) {
            final DateFormatSymbols symbols = DateFormatSymbols.getInstance(java.util.Locale.forLanguageTag(languageTag));

            Set<String> symbolLocales = symbolToLanguageTags.get(symbols);
            if (null == symbolLocales) {
                symbolLocales = Sets.sorted();
                symbolToLanguageTags.put(symbols, symbolLocales);
            }
            symbolLocales.add(languageTag);
        }

        final Map<String, DateFormatSymbols> languageTagToSymbols = Maps.sorted();
        for(final Entry<DateFormatSymbols, Set<String>> languageTagAndSymbol : symbolToLanguageTags.entrySet()) {
            languageTagToSymbols.put(languageTagAndSymbol.getValue().iterator().next(), languageTagAndSymbol.getKey());
        }

        this.line("static void register(final java.util.function.Consumer<DateFormatSymbolsProvider> registry) {");
        this.indent();
        {
            for (final DateFormatSymbols symbols : languageTagToSymbols.values()) {
                final Set<String> symbolLanguageTags = symbolToLanguageTags.get(symbols);

                this.line("registry.accept(new DateFormatSymbolsProvider(");
                this.indent();
                {
                    this.line(tabbed(symbolLanguageTags) + ", // locales");

                    this.line(tabbed(symbols.getAmPmStrings()) + ", // ampm");
                    this.line(tabbed(symbols.getEras()) + ", // eras");
                    this.line(months(symbols.getMonths()) + ", // months"); // add extra 13th
                    this.line(months(symbols.getShortMonths()) + ", // shortMonths");
                    this.line(weekdays(symbols.getShortWeekdays()) + ", // shortWeekdays"); // add empty 1st.
                    this.line(weekdays(symbols.getWeekdays()) + " // weekdays");
                }
                this.outdent();

                this.line("));");

                this.emptyLine();
            }
        }
        this.outdent();
        this.line("}");
    }

    private static int dateFormatSymbolsComparator(final DateFormatSymbols left,
                                                   final DateFormatSymbols right) {
        return toString(left).compareTo(toString(right));
    }

    final static CharSequence months(final String[] values) {
        final int last = values.length - 1;

        return tabbed(values[last].isEmpty() ?
                Arrays.copyOf(values, values.length - 1) :
                values);
    }

    /**
     * Remove the first empty String.
     */
    final static CharSequence weekdays(final String[] values) {
        final String[] without = new String[values.length - 1];
        System.arraycopy(values, 1, without, 0, values.length - 1);
        return tabbed(without);
    }

    private static String toString(final DateFormatSymbols symbols) {
        return toString(symbols.getAmPmStrings()) + "," +
                toString(symbols.getEras()) + "," +
                toString(symbols.getMonths()) + "," +
                toString(symbols.getShortMonths()) + "," +
                toString(symbols.getShortWeekdays()) + "," +
                toString(symbols.getWeekdays());
    }
}
