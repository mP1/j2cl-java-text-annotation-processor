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
import walkingkooka.collect.map.Maps;
import walkingkooka.collect.set.Sets;
import walkingkooka.j2cl.locale.WalkingkookaLanguageTag;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.Printer;
import walkingkooka.text.printer.Printers;

import java.text.DecimalFormatSymbols;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This tool prints to sysout, which will become the main body of <code>DecimalFormatSymbolProvider</code>
 */
public final class DecimalFormatSymbolsProviderTool extends LocaleProviderTool {

    public static void main(final String[] args) {
        final IndentingPrinter printer = Printers.sysOut().indenting(Indentation.with("  "));
        new DecimalFormatSymbolsProviderTool(printer).print(WalkingkookaLanguageTag.all("EN*"));
        printer.flush();
    }

    static String generateMethod(final Set<String> languageTags) {
        final StringBuilder output = new StringBuilder();
        try (final Printer printer = Printers.stringBuilder(output, LineEnding.SYSTEM)) {
            new DecimalFormatSymbolsProviderTool(printer.indenting(Indentation.with("  "))).print(languageTags);
        }

        return output.toString();
    }

    private DecimalFormatSymbolsProviderTool(final IndentingPrinter printer) {
        super(printer);
    }

    @Override
    void print0(final Set<String> languageTags) {
        final Map<DecimalFormatSymbols, Set<String>> symbolToLanguageTags = Maps.sorted(DecimalFormatSymbolsProviderTool::decimalFormatSymbolsComparator);

        for (final String languageTag : languageTags) {
            final DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(java.util.Locale.forLanguageTag(languageTag));

            Set<String> symbolLocales = symbolToLanguageTags.get(symbols);
            if (null == symbolLocales) {
                symbolLocales = Sets.sorted();
                symbolToLanguageTags.put(symbols, symbolLocales);
            }
            symbolLocales.add(languageTag);
        }

        final Map<String, DecimalFormatSymbols> languageTagToSymbols = Maps.sorted();
        for(final Entry<DecimalFormatSymbols, Set<String>> languageTagAndSymbol : symbolToLanguageTags.entrySet()) {
            languageTagToSymbols.put(languageTagAndSymbol.getValue().iterator().next(), languageTagAndSymbol.getKey());
        }

        this.line("static void register(final java.util.function.Consumer<DecimalFormatSymbolsProvider> registry) {");
        this.indent();
        {
            for (final DecimalFormatSymbols symbols : languageTagToSymbols.values()) {
                final Set<String> symbolLanguageTags = symbolToLanguageTags.get(symbols);

                this.line("registry.accept(new DecimalFormatSymbolsProvider(");
                this.indent();
                {
                    this.line(tabbed(symbolLanguageTags) + ", // locales");

                    this.line(quote(symbols.getDecimalSeparator()) + ", // decimalSeparator");
                    this.line(quote(symbols.getDigit()) + ", // digit");
                    this.line(quote(symbols.getExponentSeparator()) + ", // exponentSeparator");
                    this.line(quote(symbols.getGroupingSeparator()) + ", // groupingSeparator");
                    this.line(quote(symbols.getInfinity()) + ", // infinity");
                    this.line(quote(symbols.getInternationalCurrencySymbol()) + ", // internationalCurrencySymbol");
                    this.line(quote(symbols.getMinusSign()) + ", // minusSign");
                    this.line(quote(symbols.getMonetaryDecimalSeparator()) + ", // monetaryDecimalSeparator");
                    this.line(quote(symbols.getNaN()) + ", // nan");
                    this.line(quote(symbols.getPatternSeparator()) + ", // patternSeparator");
                    this.line(quote(symbols.getPercent()) + ", // percent");
                    this.line(quote(symbols.getPerMill()) + ", // perMill");
                    this.line(quote(symbols.getZeroDigit()) + " // zeroDigit");
                }
                this.outdent();

                this.line("));");

                this.emptyLine();
            }
        }
        this.outdent();
        this.line("}");
    }

    private static int decimalFormatSymbolsComparator(final DecimalFormatSymbols left,
                                                      final DecimalFormatSymbols right) {
        return toString(left).compareTo(toString(right));
    }

    // currency + currencySymbol are not written by generated code so they are not important to equality.
    private static String toString(final DecimalFormatSymbols symbols) {
        return ToStringBuilder.empty()
                .label("decimalSeparator").value(symbols.getDecimalSeparator())
                .label("digit").value(symbols.getDigit())
                .label("exponentSeparator").value(symbols.getExponentSeparator())
                .label("groupingSeparator").value(symbols.getGroupingSeparator())
                .label("infinity").value(symbols.getInfinity())
                .label("internationalCurrencySymbol").value(symbols.getInternationalCurrencySymbol())
                .label("minus").value(symbols.getMinusSign())
                .label("monetaryDecimalSeparator").value(symbols.getMonetaryDecimalSeparator())
                .label("nan").value(symbols.getNaN())
                .label("patternSeparator").value(symbols.getPatternSeparator())
                .label("percent").value(symbols.getPercent())
                .label("perMill").value(symbols.getPerMill())
                .label("zeroDigit").value(symbols.getZeroDigit())
                .build();
    }
}