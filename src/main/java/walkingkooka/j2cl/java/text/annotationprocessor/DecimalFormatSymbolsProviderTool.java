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
import walkingkooka.j2cl.java.io.string.StringDataInputDataOutput;
import walkingkooka.j2cl.locale.WalkingkookaLanguageTag;
import walkingkooka.j2cl.locale.annotationprocessor.LocaleAwareAnnotationProcessor;
import walkingkooka.text.LineEnding;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.Printer;
import walkingkooka.text.printer.Printers;

import java.io.DataOutput;
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This tool prints to sysout, which will become the main body of <code>DecimalFormatSymbolProvider</code>
 */
public final class DecimalFormatSymbolsProviderTool {

    public static void main(final String[] args) throws IOException {
        try (final Printer printer = Printers.sysOut()) {
            final StringBuilder data = new StringBuilder();
            generate(WalkingkookaLanguageTag.all("*"),
                    StringDataInputDataOutput.output(data::append),
                    LocaleAwareAnnotationProcessor.comments(printer));
            printer.print(data);
            printer.flush();
        }
    }

    static void generate(final Set<String> languageTags,
                         final DataOutput data,
                         final IndentingPrinter comments) throws IOException {
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
        for (final Entry<DecimalFormatSymbols, Set<String>> languageTagAndSymbol : symbolToLanguageTags.entrySet()) {
            languageTagToSymbols.put(languageTagAndSymbol.getValue().iterator().next(), languageTagAndSymbol.getKey());
        }

        data.writeInt(languageTagToSymbols.size());

        for (final DecimalFormatSymbols symbols : languageTagToSymbols.values()) {
            final Set<String> symbolLanguageTags = symbolToLanguageTags.get(symbols);

            {
                comments.lineStart();
                comments.print("locales=" + symbolLanguageTags.stream().collect(Collectors.joining(", ")));
                data.writeInt(symbolLanguageTags.size());
                for (final String languageTag : symbolLanguageTags) {
                    data.writeUTF(languageTag);
                }
            }
            {
                final char decimalSeparator = symbols.getDecimalSeparator();
                comments.lineStart();
                comments.print("decimalSeparator=" + decimalSeparator);
                data.writeChar(decimalSeparator);
            }
            {
                final char digit = symbols.getDigit();
                comments.lineStart();
                comments.print("digit=" + digit);
                data.writeChar(digit);
            }
            {
                final String exponentSeparator = symbols.getExponentSeparator();
                comments.lineStart();
                comments.print("exponentSeparator=" + exponentSeparator);
                data.writeUTF(exponentSeparator);
            }
            {
                final char groupingSeparator = symbols.getGroupingSeparator();
                comments.lineStart();
                comments.print("groupingSeparator=" + groupingSeparator);
                data.writeChar(groupingSeparator);
            }
            {
                final String infinity = symbols.getInfinity();
                comments.lineStart();
                comments.print("infinity=" + infinity);
                data.writeUTF(infinity);
            }
            {
                final String internationalCurrencySymbol = symbols.getInternationalCurrencySymbol();
                comments.lineStart();
                comments.print("internationalCurrencySymbol=" + internationalCurrencySymbol);
                data.writeUTF(internationalCurrencySymbol);
            }
            {
                final char minusSign = symbols.getMinusSign();
                comments.lineStart();
                comments.print("minusSign=" + minusSign);
                data.writeChar(minusSign);
            }
            {
                final char monetaryDecimalSeparator = symbols.getMonetaryDecimalSeparator();
                comments.lineStart();
                comments.print("monetaryDecimalSeparator=" + monetaryDecimalSeparator);
                data.writeChar(monetaryDecimalSeparator);
            }
            {
                final String nan = symbols.getNaN();
                comments.lineStart();
                comments.print("nan=" + nan);
                data.writeUTF(nan);
            }
            {
                final char patternSeparator = symbols.getPatternSeparator();
                comments.lineStart();
                comments.print("patternSeparator=" + patternSeparator);
                data.writeChar(patternSeparator);
            }
            {
                final char percent = symbols.getPercent();
                comments.lineStart();
                comments.print("percent=" + percent);
                data.writeChar(percent);
            }
            {
                final char perMill = symbols.getPerMill();
                comments.lineStart();
                comments.print("perMill=" + perMill);
                data.writeChar(perMill);
            }
            {
                final char zeroDigit = symbols.getZeroDigit();
                comments.lineStart();
                comments.print("zeroDigit=" + zeroDigit);
                data.writeChar(zeroDigit);
            }

            comments.lineStart();
            comments.print(LineEnding.SYSTEM);
        }
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

    private DecimalFormatSymbolsProviderTool() {
        throw new UnsupportedOperationException();
    }
}
