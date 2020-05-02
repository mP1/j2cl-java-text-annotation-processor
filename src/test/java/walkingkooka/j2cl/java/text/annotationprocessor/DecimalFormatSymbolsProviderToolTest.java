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

import org.junit.jupiter.api.Test;
import walkingkooka.collect.set.Sets;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DecimalFormatSymbolsProviderToolTest implements ClassTesting<DecimalFormatSymbolsProviderTool> {

    @Test
    public void testENAU() {
        final String expected = "  static void register(final java.util.function.Consumer<DecimalFormatSymbolsProvider> registry) {\n" +
                "    registry.accept(new DecimalFormatSymbolsProvider(\n" +
                "      \"EN-AU\", // locales\n" +
                "      '.', // decimalSeparator\n" +
                "      '#', // digit\n" +
                "      \"e\", // exponentSeparator\n" +
                "      ',', // groupingSeparator\n" +
                "      \"∞\", // infinity\n" +
                "      \"AUD\", // internationalCurrencySymbol\n" +
                "      '-', // minusSign\n" +
                "      '.', // monetaryDecimalSeparator\n" +
                "      \"NaN\", // nan\n" +
                "      ';', // patternSeparator\n" +
                "      '%', // percent\n" +
                "      '‰', // perMill\n" +
                "      '0' // zeroDigit\n" +
                "    ));\n" +
                "    \n" +
                "  }\n";
        assertEquals(expected, DecimalFormatSymbolsProviderTool.generateMethod(Sets.of("EN-AU")));
    }

    @Test
    public void testFRFR() {
        final String expected = "  static void register(final java.util.function.Consumer<DecimalFormatSymbolsProvider> registry) {\n" +
                "    registry.accept(new DecimalFormatSymbolsProvider(\n" +
                "      \"FR-FR\", // locales\n" +
                "      ',', // decimalSeparator\n" +
                "      '#', // digit\n" +
                "      \"E\", // exponentSeparator\n" +
                "      ' ', // groupingSeparator\n" +
                "      \"∞\", // infinity\n" +
                "      \"EUR\", // internationalCurrencySymbol\n" +
                "      '-', // minusSign\n" +
                "      ',', // monetaryDecimalSeparator\n" +
                "      \"NaN\", // nan\n" +
                "      ';', // patternSeparator\n" +
                "      '%', // percent\n" +
                "      '‰', // perMill\n" +
                "      '0' // zeroDigit\n" +
                "    ));\n" +
                "    \n" +
                "  }\n";
        assertEquals(expected, DecimalFormatSymbolsProviderTool.generateMethod(Sets.of("FR-FR")));
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<DecimalFormatSymbolsProviderTool> type() {
        return DecimalFormatSymbolsProviderTool.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
