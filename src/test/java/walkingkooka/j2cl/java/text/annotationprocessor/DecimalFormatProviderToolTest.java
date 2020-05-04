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

public final class DecimalFormatProviderToolTest implements ClassTesting<DecimalFormatProviderTool> {

    @Test
    public void testENAU() {
        final String expected = "  static void register(final java.util.function.Consumer<DecimalFormatProvider> registry) {\n" +
                "    registry.accept( new DecimalFormatProvider(\n" +
                "      \"EN-AU\", // locales\n" +
                "      false, // Currency decimalSeparatorAlwaysShown\n" +
                "      3, // Currency groupingSize\n" +
                "      true, // Currency groupingUsed\n" +
                "      2, // Currency maximumFractionDigits\n" +
                "      2, // Currency minimumFractionDigits\n" +
                "      2147483647, // Currency maximumIntegerDigits\n" +
                "      1, // Currency minimumIntegerDigits\n" +
                "      1, // Currency multiplier\n" +
                "      \"-$\", // Currency negativePrefix\n" +
                "      \"\", // Currency negativeSuffix\n" +
                "      0, // Currency parse\n" +
                "      \"¤#,##0.00\", // Currency pattern\n" +
                "      \"$\", // Currency positivePrefix\n" +
                "      \"\", // Currency positiveSuffix\n" +
                "      java.math.RoundingMode.HALF_EVEN, // Currency roundingMode\n" +
                "      false, // Instance decimalSeparatorAlwaysShown\n" +
                "      3, // Instance groupingSize\n" +
                "      true, // Instance groupingUsed\n" +
                "      3, // Instance maximumFractionDigits\n" +
                "      0, // Instance minimumFractionDigits\n" +
                "      2147483647, // Instance maximumIntegerDigits\n" +
                "      1, // Instance minimumIntegerDigits\n" +
                "      1, // Instance multiplier\n" +
                "      \"-\", // Instance negativePrefix\n" +
                "      \"\", // Instance negativeSuffix\n" +
                "      0, // Instance parse\n" +
                "      \"#,##0.###\", // Instance pattern\n" +
                "      \"\", // Instance positivePrefix\n" +
                "      \"\", // Instance positiveSuffix\n" +
                "      java.math.RoundingMode.HALF_EVEN, // Instance roundingMode\n" +
                "      false, // Integer decimalSeparatorAlwaysShown\n" +
                "      3, // Integer groupingSize\n" +
                "      true, // Integer groupingUsed\n" +
                "      0, // Integer maximumFractionDigits\n" +
                "      0, // Integer minimumFractionDigits\n" +
                "      2147483647, // Integer maximumIntegerDigits\n" +
                "      1, // Integer minimumIntegerDigits\n" +
                "      1, // Integer multiplier\n" +
                "      \"-\", // Integer negativePrefix\n" +
                "      \"\", // Integer negativeSuffix\n" +
                "      1, // Integer parse\n" +
                "      \"#,##0\", // Integer pattern\n" +
                "      \"\", // Integer positivePrefix\n" +
                "      \"\", // Integer positiveSuffix\n" +
                "      java.math.RoundingMode.HALF_EVEN, // Integer roundingMode\n" +
                "      false, // Number decimalSeparatorAlwaysShown\n" +
                "      3, // Number groupingSize\n" +
                "      true, // Number groupingUsed\n" +
                "      3, // Number maximumFractionDigits\n" +
                "      0, // Number minimumFractionDigits\n" +
                "      2147483647, // Number maximumIntegerDigits\n" +
                "      1, // Number minimumIntegerDigits\n" +
                "      1, // Number multiplier\n" +
                "      \"-\", // Number negativePrefix\n" +
                "      \"\", // Number negativeSuffix\n" +
                "      0, // Number parse\n" +
                "      \"#,##0.###\", // Number pattern\n" +
                "      \"\", // Number positivePrefix\n" +
                "      \"\", // Number positiveSuffix\n" +
                "      java.math.RoundingMode.HALF_EVEN, // Number roundingMode\n" +
                "      false, // Percent decimalSeparatorAlwaysShown\n" +
                "      3, // Percent groupingSize\n" +
                "      true, // Percent groupingUsed\n" +
                "      0, // Percent maximumFractionDigits\n" +
                "      0, // Percent minimumFractionDigits\n" +
                "      2147483647, // Percent maximumIntegerDigits\n" +
                "      1, // Percent minimumIntegerDigits\n" +
                "      100, // Percent multiplier\n" +
                "      \"-\", // Percent negativePrefix\n" +
                "      \"%\", // Percent negativeSuffix\n" +
                "      0, // Percent parse\n" +
                "      \"#,##0%\", // Percent pattern\n" +
                "      \"\", // Percent positivePrefix\n" +
                "      \"%\", // Percent positiveSuffix\n" +
                "      java.math.RoundingMode.HALF_EVEN // Percent roundingMode\n" +
                "    ));\n" +
                "    \n" +
                "  }\n";
        assertEquals(expected, DecimalFormatProviderTool.generateMethod(Sets.of("EN-AU")));
    }

    @Test
    public void testFRFR() {
        final String expected = "  static void register(final java.util.function.Consumer<DecimalFormatProvider> registry) {\n" +
                "    registry.accept( new DecimalFormatProvider(\n" +
                "      \"FR-FR\", // locales\n" +
                "      false, // Currency decimalSeparatorAlwaysShown\n" +
                "      3, // Currency groupingSize\n" +
                "      true, // Currency groupingUsed\n" +
                "      2, // Currency maximumFractionDigits\n" +
                "      2, // Currency minimumFractionDigits\n" +
                "      2147483647, // Currency maximumIntegerDigits\n" +
                "      1, // Currency minimumIntegerDigits\n" +
                "      1, // Currency multiplier\n" +
                "      \"-\", // Currency negativePrefix\n" +
                "      \" €\", // Currency negativeSuffix\n" +
                "      0, // Currency parse\n" +
                "      \"#,##0.00 ¤\", // Currency pattern\n" +
                "      \"\", // Currency positivePrefix\n" +
                "      \" €\", // Currency positiveSuffix\n" +
                "      java.math.RoundingMode.HALF_EVEN, // Currency roundingMode\n" +
                "      false, // Instance decimalSeparatorAlwaysShown\n" +
                "      3, // Instance groupingSize\n" +
                "      true, // Instance groupingUsed\n" +
                "      3, // Instance maximumFractionDigits\n" +
                "      0, // Instance minimumFractionDigits\n" +
                "      2147483647, // Instance maximumIntegerDigits\n" +
                "      1, // Instance minimumIntegerDigits\n" +
                "      1, // Instance multiplier\n" +
                "      \"-\", // Instance negativePrefix\n" +
                "      \"\", // Instance negativeSuffix\n" +
                "      0, // Instance parse\n" +
                "      \"#,##0.###\", // Instance pattern\n" +
                "      \"\", // Instance positivePrefix\n" +
                "      \"\", // Instance positiveSuffix\n" +
                "      java.math.RoundingMode.HALF_EVEN, // Instance roundingMode\n" +
                "      false, // Integer decimalSeparatorAlwaysShown\n" +
                "      3, // Integer groupingSize\n" +
                "      true, // Integer groupingUsed\n" +
                "      0, // Integer maximumFractionDigits\n" +
                "      0, // Integer minimumFractionDigits\n" +
                "      2147483647, // Integer maximumIntegerDigits\n" +
                "      1, // Integer minimumIntegerDigits\n" +
                "      1, // Integer multiplier\n" +
                "      \"-\", // Integer negativePrefix\n" +
                "      \"\", // Integer negativeSuffix\n" +
                "      1, // Integer parse\n" +
                "      \"#,##0\", // Integer pattern\n" +
                "      \"\", // Integer positivePrefix\n" +
                "      \"\", // Integer positiveSuffix\n" +
                "      java.math.RoundingMode.HALF_EVEN, // Integer roundingMode\n" +
                "      false, // Number decimalSeparatorAlwaysShown\n" +
                "      3, // Number groupingSize\n" +
                "      true, // Number groupingUsed\n" +
                "      3, // Number maximumFractionDigits\n" +
                "      0, // Number minimumFractionDigits\n" +
                "      2147483647, // Number maximumIntegerDigits\n" +
                "      1, // Number minimumIntegerDigits\n" +
                "      1, // Number multiplier\n" +
                "      \"-\", // Number negativePrefix\n" +
                "      \"\", // Number negativeSuffix\n" +
                "      0, // Number parse\n" +
                "      \"#,##0.###\", // Number pattern\n" +
                "      \"\", // Number positivePrefix\n" +
                "      \"\", // Number positiveSuffix\n" +
                "      java.math.RoundingMode.HALF_EVEN, // Number roundingMode\n" +
                "      false, // Percent decimalSeparatorAlwaysShown\n" +
                "      3, // Percent groupingSize\n" +
                "      true, // Percent groupingUsed\n" +
                "      0, // Percent maximumFractionDigits\n" +
                "      0, // Percent minimumFractionDigits\n" +
                "      2147483647, // Percent maximumIntegerDigits\n" +
                "      1, // Percent minimumIntegerDigits\n" +
                "      100, // Percent multiplier\n" +
                "      \"-\", // Percent negativePrefix\n" +
                "      \" %\", // Percent negativeSuffix\n" +
                "      0, // Percent parse\n" +
                "      \"#,##0 %\", // Percent pattern\n" +
                "      \"\", // Percent positivePrefix\n" +
                "      \" %\", // Percent positiveSuffix\n" +
                "      java.math.RoundingMode.HALF_EVEN // Percent roundingMode\n" +
                "    ));\n" +
                "    \n" +
                "  }\n";
        assertEquals(expected, DecimalFormatProviderTool.generateMethod(Sets.of("FR-FR")));
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<DecimalFormatProviderTool> type() {
        return DecimalFormatProviderTool.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
