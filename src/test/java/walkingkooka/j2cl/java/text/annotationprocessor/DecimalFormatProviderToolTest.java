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
import walkingkooka.j2cl.java.io.string.StringDataInputDataOutput;
import walkingkooka.j2cl.locale.annotationprocessor.LocaleAwareAnnotationProcessorTool;
import walkingkooka.text.printer.IndentingPrinter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DecimalFormatProviderToolTest extends ProviderToolTestCase<DecimalFormatProviderTool> {

    @Test
    public void testENAU() throws IOException {
        this.generateAndCheck("en-AU",
                "// locales=en-AU\n" +
                        "//   \n" +
                        "//   Currency decimalSeparatorAlwaysShown=false\n" +
                        "//   Currency groupingSize=3\n" +
                        "//   Currency groupingUsed=true\n" +
                        "//   Currency maximumFractionDigits=2\n" +
                        "//   Currency minimumFractionDigits=2\n" +
                        "//   Currency maximumIntegerDigits=2147483647\n" +
                        "//   Currency minimumIntegerDigits=1\n" +
                        "//   Currency multiplier=1\n" +
                        "//   Currency negativePrefix=-$\n" +
                        "//   Currency negativeSuffix=\n" +
                        "//   Currency parse=0\n" +
                        "//   Currency pattern=¤#,##0.00\n" +
                        "//   Currency positivePrefix=$\n" +
                        "//   Currency positiveSuffix=\n" +
                        "//   Currency roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Instance decimalSeparatorAlwaysShown=false\n" +
                        "//   Instance groupingSize=3\n" +
                        "//   Instance groupingUsed=true\n" +
                        "//   Instance maximumFractionDigits=3\n" +
                        "//   Instance minimumFractionDigits=0\n" +
                        "//   Instance maximumIntegerDigits=2147483647\n" +
                        "//   Instance minimumIntegerDigits=1\n" +
                        "//   Instance multiplier=1\n" +
                        "//   Instance negativePrefix=-\n" +
                        "//   Instance negativeSuffix=\n" +
                        "//   Instance parse=0\n" +
                        "//   Instance pattern=#,##0.###\n" +
                        "//   Instance positivePrefix=\n" +
                        "//   Instance positiveSuffix=\n" +
                        "//   Instance roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Integer decimalSeparatorAlwaysShown=false\n" +
                        "//   Integer groupingSize=3\n" +
                        "//   Integer groupingUsed=true\n" +
                        "//   Integer maximumFractionDigits=0\n" +
                        "//   Integer minimumFractionDigits=0\n" +
                        "//   Integer maximumIntegerDigits=2147483647\n" +
                        "//   Integer minimumIntegerDigits=1\n" +
                        "//   Integer multiplier=1\n" +
                        "//   Integer negativePrefix=-\n" +
                        "//   Integer negativeSuffix=\n" +
                        "//   Integer parse=1\n" +
                        "//   Integer pattern=#,##0\n" +
                        "//   Integer positivePrefix=\n" +
                        "//   Integer positiveSuffix=\n" +
                        "//   Integer roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Number decimalSeparatorAlwaysShown=false\n" +
                        "//   Number groupingSize=3\n" +
                        "//   Number groupingUsed=true\n" +
                        "//   Number maximumFractionDigits=3\n" +
                        "//   Number minimumFractionDigits=0\n" +
                        "//   Number maximumIntegerDigits=2147483647\n" +
                        "//   Number minimumIntegerDigits=1\n" +
                        "//   Number multiplier=1\n" +
                        "//   Number negativePrefix=-\n" +
                        "//   Number negativeSuffix=\n" +
                        "//   Number parse=0\n" +
                        "//   Number pattern=#,##0.###\n" +
                        "//   Number positivePrefix=\n" +
                        "//   Number positiveSuffix=\n" +
                        "//   Number roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Percent decimalSeparatorAlwaysShown=false\n" +
                        "//   Percent groupingSize=3\n" +
                        "//   Percent groupingUsed=true\n" +
                        "//   Percent maximumFractionDigits=0\n" +
                        "//   Percent minimumFractionDigits=0\n" +
                        "//   Percent maximumIntegerDigits=2147483647\n" +
                        "//   Percent minimumIntegerDigits=1\n" +
                        "//   Percent multiplier=100\n" +
                        "//   Percent negativePrefix=-\n" +
                        "//   Percent negativeSuffix=%\n" +
                        "//   Percent parse=0\n" +
                        "//   Percent pattern=#,##0%\n" +
                        "//   Percent positivePrefix=\n" +
                        "//   Percent positiveSuffix=%\n" +
                        "//   Percent roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "\n" +
                        "\n" +
                        "1,1,en-AU,03,12,2,2147483647,1,1,-$,,0,¤#\\,##0.00,$,,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,1,-,,1,#\\,##0,,,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,100,-,%,0,#\\,##0%,,%,HALF_EVEN");
    }

    @Test
    public void testENAUENNZ() throws IOException {
        this.generateAndCheck("en-AU,en-NZ",
                "// locales=en-AU, en-NZ\n" +
                        "//   \n" +
                        "//   Currency decimalSeparatorAlwaysShown=false\n" +
                        "//   Currency groupingSize=3\n" +
                        "//   Currency groupingUsed=true\n" +
                        "//   Currency maximumFractionDigits=2\n" +
                        "//   Currency minimumFractionDigits=2\n" +
                        "//   Currency maximumIntegerDigits=2147483647\n" +
                        "//   Currency minimumIntegerDigits=1\n" +
                        "//   Currency multiplier=1\n" +
                        "//   Currency negativePrefix=-$\n" +
                        "//   Currency negativeSuffix=\n" +
                        "//   Currency parse=0\n" +
                        "//   Currency pattern=¤#,##0.00\n" +
                        "//   Currency positivePrefix=$\n" +
                        "//   Currency positiveSuffix=\n" +
                        "//   Currency roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Instance decimalSeparatorAlwaysShown=false\n" +
                        "//   Instance groupingSize=3\n" +
                        "//   Instance groupingUsed=true\n" +
                        "//   Instance maximumFractionDigits=3\n" +
                        "//   Instance minimumFractionDigits=0\n" +
                        "//   Instance maximumIntegerDigits=2147483647\n" +
                        "//   Instance minimumIntegerDigits=1\n" +
                        "//   Instance multiplier=1\n" +
                        "//   Instance negativePrefix=-\n" +
                        "//   Instance negativeSuffix=\n" +
                        "//   Instance parse=0\n" +
                        "//   Instance pattern=#,##0.###\n" +
                        "//   Instance positivePrefix=\n" +
                        "//   Instance positiveSuffix=\n" +
                        "//   Instance roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Integer decimalSeparatorAlwaysShown=false\n" +
                        "//   Integer groupingSize=3\n" +
                        "//   Integer groupingUsed=true\n" +
                        "//   Integer maximumFractionDigits=0\n" +
                        "//   Integer minimumFractionDigits=0\n" +
                        "//   Integer maximumIntegerDigits=2147483647\n" +
                        "//   Integer minimumIntegerDigits=1\n" +
                        "//   Integer multiplier=1\n" +
                        "//   Integer negativePrefix=-\n" +
                        "//   Integer negativeSuffix=\n" +
                        "//   Integer parse=1\n" +
                        "//   Integer pattern=#,##0\n" +
                        "//   Integer positivePrefix=\n" +
                        "//   Integer positiveSuffix=\n" +
                        "//   Integer roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Number decimalSeparatorAlwaysShown=false\n" +
                        "//   Number groupingSize=3\n" +
                        "//   Number groupingUsed=true\n" +
                        "//   Number maximumFractionDigits=3\n" +
                        "//   Number minimumFractionDigits=0\n" +
                        "//   Number maximumIntegerDigits=2147483647\n" +
                        "//   Number minimumIntegerDigits=1\n" +
                        "//   Number multiplier=1\n" +
                        "//   Number negativePrefix=-\n" +
                        "//   Number negativeSuffix=\n" +
                        "//   Number parse=0\n" +
                        "//   Number pattern=#,##0.###\n" +
                        "//   Number positivePrefix=\n" +
                        "//   Number positiveSuffix=\n" +
                        "//   Number roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Percent decimalSeparatorAlwaysShown=false\n" +
                        "//   Percent groupingSize=3\n" +
                        "//   Percent groupingUsed=true\n" +
                        "//   Percent maximumFractionDigits=0\n" +
                        "//   Percent minimumFractionDigits=0\n" +
                        "//   Percent maximumIntegerDigits=2147483647\n" +
                        "//   Percent minimumIntegerDigits=1\n" +
                        "//   Percent multiplier=100\n" +
                        "//   Percent negativePrefix=-\n" +
                        "//   Percent negativeSuffix=%\n" +
                        "//   Percent parse=0\n" +
                        "//   Percent pattern=#,##0%\n" +
                        "//   Percent positivePrefix=\n" +
                        "//   Percent positiveSuffix=%\n" +
                        "//   Percent roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "\n" +
                        "\n" +
                        "1,2,en-AU,en-NZ,03,12,2,2147483647,1,1,-$,,0,¤#\\,##0.00,$,,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,1,-,,1,#\\,##0,,,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,100,-,%,0,#\\,##0%,,%,HALF_EVEN");
    }

    @Test
    public void testFRFR() throws IOException {
        this.generateAndCheck("fr-FR",
                "// locales=fr-FR\n" +
                        "//   \n" +
                        "//   Currency decimalSeparatorAlwaysShown=false\n" +
                        "//   Currency groupingSize=3\n" +
                        "//   Currency groupingUsed=true\n" +
                        "//   Currency maximumFractionDigits=2\n" +
                        "//   Currency minimumFractionDigits=2\n" +
                        "//   Currency maximumIntegerDigits=2147483647\n" +
                        "//   Currency minimumIntegerDigits=1\n" +
                        "//   Currency multiplier=1\n" +
                        "//   Currency negativePrefix=-\n" +
                        "//   Currency negativeSuffix= €\n" +
                        "//   Currency parse=0\n" +
                        "//   Currency pattern=#,##0.00 ¤\n" +
                        "//   Currency positivePrefix=\n" +
                        "//   Currency positiveSuffix= €\n" +
                        "//   Currency roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Instance decimalSeparatorAlwaysShown=false\n" +
                        "//   Instance groupingSize=3\n" +
                        "//   Instance groupingUsed=true\n" +
                        "//   Instance maximumFractionDigits=3\n" +
                        "//   Instance minimumFractionDigits=0\n" +
                        "//   Instance maximumIntegerDigits=2147483647\n" +
                        "//   Instance minimumIntegerDigits=1\n" +
                        "//   Instance multiplier=1\n" +
                        "//   Instance negativePrefix=-\n" +
                        "//   Instance negativeSuffix=\n" +
                        "//   Instance parse=0\n" +
                        "//   Instance pattern=#,##0.###\n" +
                        "//   Instance positivePrefix=\n" +
                        "//   Instance positiveSuffix=\n" +
                        "//   Instance roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Integer decimalSeparatorAlwaysShown=false\n" +
                        "//   Integer groupingSize=3\n" +
                        "//   Integer groupingUsed=true\n" +
                        "//   Integer maximumFractionDigits=0\n" +
                        "//   Integer minimumFractionDigits=0\n" +
                        "//   Integer maximumIntegerDigits=2147483647\n" +
                        "//   Integer minimumIntegerDigits=1\n" +
                        "//   Integer multiplier=1\n" +
                        "//   Integer negativePrefix=-\n" +
                        "//   Integer negativeSuffix=\n" +
                        "//   Integer parse=1\n" +
                        "//   Integer pattern=#,##0\n" +
                        "//   Integer positivePrefix=\n" +
                        "//   Integer positiveSuffix=\n" +
                        "//   Integer roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Number decimalSeparatorAlwaysShown=false\n" +
                        "//   Number groupingSize=3\n" +
                        "//   Number groupingUsed=true\n" +
                        "//   Number maximumFractionDigits=3\n" +
                        "//   Number minimumFractionDigits=0\n" +
                        "//   Number maximumIntegerDigits=2147483647\n" +
                        "//   Number minimumIntegerDigits=1\n" +
                        "//   Number multiplier=1\n" +
                        "//   Number negativePrefix=-\n" +
                        "//   Number negativeSuffix=\n" +
                        "//   Number parse=0\n" +
                        "//   Number pattern=#,##0.###\n" +
                        "//   Number positivePrefix=\n" +
                        "//   Number positiveSuffix=\n" +
                        "//   Number roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Percent decimalSeparatorAlwaysShown=false\n" +
                        "//   Percent groupingSize=3\n" +
                        "//   Percent groupingUsed=true\n" +
                        "//   Percent maximumFractionDigits=0\n" +
                        "//   Percent minimumFractionDigits=0\n" +
                        "//   Percent maximumIntegerDigits=2147483647\n" +
                        "//   Percent minimumIntegerDigits=1\n" +
                        "//   Percent multiplier=100\n" +
                        "//   Percent negativePrefix=-\n" +
                        "//   Percent negativeSuffix= %\n" +
                        "//   Percent parse=0\n" +
                        "//   Percent pattern=#,##0 %\n" +
                        "//   Percent positivePrefix=\n" +
                        "//   Percent positiveSuffix= %\n" +
                        "//   Percent roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "\n" +
                        "\n" +
                        "1,1,fr-FR,03,12,2,2147483647,1,1,-, €,0,#\\,##0.00 ¤,, €,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,1,-,,1,#\\,##0,,,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,100,-, %,0,#\\,##0 %,, %,HALF_EVEN");
    }

    @Test
    public void testFRFRFRCA() throws IOException {
        this.generateAndCheck("fr-FR,fr-CA",
                "// locales=fr-CA\n" +
                        "//   \n" +
                        "//   Currency decimalSeparatorAlwaysShown=false\n" +
                        "//   Currency groupingSize=3\n" +
                        "//   Currency groupingUsed=true\n" +
                        "//   Currency maximumFractionDigits=2\n" +
                        "//   Currency minimumFractionDigits=2\n" +
                        "//   Currency maximumIntegerDigits=2147483647\n" +
                        "//   Currency minimumIntegerDigits=1\n" +
                        "//   Currency multiplier=1\n" +
                        "//   Currency negativePrefix=-\n" +
                        "//   Currency negativeSuffix= $\n" +
                        "//   Currency parse=0\n" +
                        "//   Currency pattern=#,##0.00 ¤\n" +
                        "//   Currency positivePrefix=\n" +
                        "//   Currency positiveSuffix= $\n" +
                        "//   Currency roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Instance decimalSeparatorAlwaysShown=false\n" +
                        "//   Instance groupingSize=3\n" +
                        "//   Instance groupingUsed=true\n" +
                        "//   Instance maximumFractionDigits=3\n" +
                        "//   Instance minimumFractionDigits=0\n" +
                        "//   Instance maximumIntegerDigits=2147483647\n" +
                        "//   Instance minimumIntegerDigits=1\n" +
                        "//   Instance multiplier=1\n" +
                        "//   Instance negativePrefix=-\n" +
                        "//   Instance negativeSuffix=\n" +
                        "//   Instance parse=0\n" +
                        "//   Instance pattern=#,##0.###\n" +
                        "//   Instance positivePrefix=\n" +
                        "//   Instance positiveSuffix=\n" +
                        "//   Instance roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Integer decimalSeparatorAlwaysShown=false\n" +
                        "//   Integer groupingSize=3\n" +
                        "//   Integer groupingUsed=true\n" +
                        "//   Integer maximumFractionDigits=0\n" +
                        "//   Integer minimumFractionDigits=0\n" +
                        "//   Integer maximumIntegerDigits=2147483647\n" +
                        "//   Integer minimumIntegerDigits=1\n" +
                        "//   Integer multiplier=1\n" +
                        "//   Integer negativePrefix=-\n" +
                        "//   Integer negativeSuffix=\n" +
                        "//   Integer parse=1\n" +
                        "//   Integer pattern=#,##0\n" +
                        "//   Integer positivePrefix=\n" +
                        "//   Integer positiveSuffix=\n" +
                        "//   Integer roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Number decimalSeparatorAlwaysShown=false\n" +
                        "//   Number groupingSize=3\n" +
                        "//   Number groupingUsed=true\n" +
                        "//   Number maximumFractionDigits=3\n" +
                        "//   Number minimumFractionDigits=0\n" +
                        "//   Number maximumIntegerDigits=2147483647\n" +
                        "//   Number minimumIntegerDigits=1\n" +
                        "//   Number multiplier=1\n" +
                        "//   Number negativePrefix=-\n" +
                        "//   Number negativeSuffix=\n" +
                        "//   Number parse=0\n" +
                        "//   Number pattern=#,##0.###\n" +
                        "//   Number positivePrefix=\n" +
                        "//   Number positiveSuffix=\n" +
                        "//   Number roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Percent decimalSeparatorAlwaysShown=false\n" +
                        "//   Percent groupingSize=3\n" +
                        "//   Percent groupingUsed=true\n" +
                        "//   Percent maximumFractionDigits=0\n" +
                        "//   Percent minimumFractionDigits=0\n" +
                        "//   Percent maximumIntegerDigits=2147483647\n" +
                        "//   Percent minimumIntegerDigits=1\n" +
                        "//   Percent multiplier=100\n" +
                        "//   Percent negativePrefix=-\n" +
                        "//   Percent negativeSuffix= %\n" +
                        "//   Percent parse=0\n" +
                        "//   Percent pattern=#,##0 %\n" +
                        "//   Percent positivePrefix=\n" +
                        "//   Percent positiveSuffix= %\n" +
                        "//   Percent roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "// locales=fr-FR\n" +
                        "//   \n" +
                        "//   Currency decimalSeparatorAlwaysShown=false\n" +
                        "//   Currency groupingSize=3\n" +
                        "//   Currency groupingUsed=true\n" +
                        "//   Currency maximumFractionDigits=2\n" +
                        "//   Currency minimumFractionDigits=2\n" +
                        "//   Currency maximumIntegerDigits=2147483647\n" +
                        "//   Currency minimumIntegerDigits=1\n" +
                        "//   Currency multiplier=1\n" +
                        "//   Currency negativePrefix=-\n" +
                        "//   Currency negativeSuffix= €\n" +
                        "//   Currency parse=0\n" +
                        "//   Currency pattern=#,##0.00 ¤\n" +
                        "//   Currency positivePrefix=\n" +
                        "//   Currency positiveSuffix= €\n" +
                        "//   Currency roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Instance decimalSeparatorAlwaysShown=false\n" +
                        "//   Instance groupingSize=3\n" +
                        "//   Instance groupingUsed=true\n" +
                        "//   Instance maximumFractionDigits=3\n" +
                        "//   Instance minimumFractionDigits=0\n" +
                        "//   Instance maximumIntegerDigits=2147483647\n" +
                        "//   Instance minimumIntegerDigits=1\n" +
                        "//   Instance multiplier=1\n" +
                        "//   Instance negativePrefix=-\n" +
                        "//   Instance negativeSuffix=\n" +
                        "//   Instance parse=0\n" +
                        "//   Instance pattern=#,##0.###\n" +
                        "//   Instance positivePrefix=\n" +
                        "//   Instance positiveSuffix=\n" +
                        "//   Instance roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Integer decimalSeparatorAlwaysShown=false\n" +
                        "//   Integer groupingSize=3\n" +
                        "//   Integer groupingUsed=true\n" +
                        "//   Integer maximumFractionDigits=0\n" +
                        "//   Integer minimumFractionDigits=0\n" +
                        "//   Integer maximumIntegerDigits=2147483647\n" +
                        "//   Integer minimumIntegerDigits=1\n" +
                        "//   Integer multiplier=1\n" +
                        "//   Integer negativePrefix=-\n" +
                        "//   Integer negativeSuffix=\n" +
                        "//   Integer parse=1\n" +
                        "//   Integer pattern=#,##0\n" +
                        "//   Integer positivePrefix=\n" +
                        "//   Integer positiveSuffix=\n" +
                        "//   Integer roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Number decimalSeparatorAlwaysShown=false\n" +
                        "//   Number groupingSize=3\n" +
                        "//   Number groupingUsed=true\n" +
                        "//   Number maximumFractionDigits=3\n" +
                        "//   Number minimumFractionDigits=0\n" +
                        "//   Number maximumIntegerDigits=2147483647\n" +
                        "//   Number minimumIntegerDigits=1\n" +
                        "//   Number multiplier=1\n" +
                        "//   Number negativePrefix=-\n" +
                        "//   Number negativeSuffix=\n" +
                        "//   Number parse=0\n" +
                        "//   Number pattern=#,##0.###\n" +
                        "//   Number positivePrefix=\n" +
                        "//   Number positiveSuffix=\n" +
                        "//   Number roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Percent decimalSeparatorAlwaysShown=false\n" +
                        "//   Percent groupingSize=3\n" +
                        "//   Percent groupingUsed=true\n" +
                        "//   Percent maximumFractionDigits=0\n" +
                        "//   Percent minimumFractionDigits=0\n" +
                        "//   Percent maximumIntegerDigits=2147483647\n" +
                        "//   Percent minimumIntegerDigits=1\n" +
                        "//   Percent multiplier=100\n" +
                        "//   Percent negativePrefix=-\n" +
                        "//   Percent negativeSuffix= %\n" +
                        "//   Percent parse=0\n" +
                        "//   Percent pattern=#,##0 %\n" +
                        "//   Percent positivePrefix=\n" +
                        "//   Percent positiveSuffix= %\n" +
                        "//   Percent roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "\n" +
                        "\n" +
                        "2,1,fr-CA,03,12,2,2147483647,1,1,-, $,0,#\\,##0.00 ¤,, $,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,1,-,,1,#\\,##0,,,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,100,-, %,0,#\\,##0 %,, %,HALF_EVEN,1,fr-FR,03,12,2,2147483647,1,1,-, €,0,#\\,##0.00 ¤,, €,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,1,-,,1,#\\,##0,,,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,100,-, %,0,#\\,##0 %,, %,HALF_EVEN");
    }

    @Test
    public void testENAUFRFR() throws IOException {
        this.generateAndCheck("en-AU,fr-FR",
                "// locales=en-AU\n" +
                        "//   \n" +
                        "//   Currency decimalSeparatorAlwaysShown=false\n" +
                        "//   Currency groupingSize=3\n" +
                        "//   Currency groupingUsed=true\n" +
                        "//   Currency maximumFractionDigits=2\n" +
                        "//   Currency minimumFractionDigits=2\n" +
                        "//   Currency maximumIntegerDigits=2147483647\n" +
                        "//   Currency minimumIntegerDigits=1\n" +
                        "//   Currency multiplier=1\n" +
                        "//   Currency negativePrefix=-$\n" +
                        "//   Currency negativeSuffix=\n" +
                        "//   Currency parse=0\n" +
                        "//   Currency pattern=¤#,##0.00\n" +
                        "//   Currency positivePrefix=$\n" +
                        "//   Currency positiveSuffix=\n" +
                        "//   Currency roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Instance decimalSeparatorAlwaysShown=false\n" +
                        "//   Instance groupingSize=3\n" +
                        "//   Instance groupingUsed=true\n" +
                        "//   Instance maximumFractionDigits=3\n" +
                        "//   Instance minimumFractionDigits=0\n" +
                        "//   Instance maximumIntegerDigits=2147483647\n" +
                        "//   Instance minimumIntegerDigits=1\n" +
                        "//   Instance multiplier=1\n" +
                        "//   Instance negativePrefix=-\n" +
                        "//   Instance negativeSuffix=\n" +
                        "//   Instance parse=0\n" +
                        "//   Instance pattern=#,##0.###\n" +
                        "//   Instance positivePrefix=\n" +
                        "//   Instance positiveSuffix=\n" +
                        "//   Instance roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Integer decimalSeparatorAlwaysShown=false\n" +
                        "//   Integer groupingSize=3\n" +
                        "//   Integer groupingUsed=true\n" +
                        "//   Integer maximumFractionDigits=0\n" +
                        "//   Integer minimumFractionDigits=0\n" +
                        "//   Integer maximumIntegerDigits=2147483647\n" +
                        "//   Integer minimumIntegerDigits=1\n" +
                        "//   Integer multiplier=1\n" +
                        "//   Integer negativePrefix=-\n" +
                        "//   Integer negativeSuffix=\n" +
                        "//   Integer parse=1\n" +
                        "//   Integer pattern=#,##0\n" +
                        "//   Integer positivePrefix=\n" +
                        "//   Integer positiveSuffix=\n" +
                        "//   Integer roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Number decimalSeparatorAlwaysShown=false\n" +
                        "//   Number groupingSize=3\n" +
                        "//   Number groupingUsed=true\n" +
                        "//   Number maximumFractionDigits=3\n" +
                        "//   Number minimumFractionDigits=0\n" +
                        "//   Number maximumIntegerDigits=2147483647\n" +
                        "//   Number minimumIntegerDigits=1\n" +
                        "//   Number multiplier=1\n" +
                        "//   Number negativePrefix=-\n" +
                        "//   Number negativeSuffix=\n" +
                        "//   Number parse=0\n" +
                        "//   Number pattern=#,##0.###\n" +
                        "//   Number positivePrefix=\n" +
                        "//   Number positiveSuffix=\n" +
                        "//   Number roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Percent decimalSeparatorAlwaysShown=false\n" +
                        "//   Percent groupingSize=3\n" +
                        "//   Percent groupingUsed=true\n" +
                        "//   Percent maximumFractionDigits=0\n" +
                        "//   Percent minimumFractionDigits=0\n" +
                        "//   Percent maximumIntegerDigits=2147483647\n" +
                        "//   Percent minimumIntegerDigits=1\n" +
                        "//   Percent multiplier=100\n" +
                        "//   Percent negativePrefix=-\n" +
                        "//   Percent negativeSuffix=%\n" +
                        "//   Percent parse=0\n" +
                        "//   Percent pattern=#,##0%\n" +
                        "//   Percent positivePrefix=\n" +
                        "//   Percent positiveSuffix=%\n" +
                        "//   Percent roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "// locales=fr-FR\n" +
                        "//   \n" +
                        "//   Currency decimalSeparatorAlwaysShown=false\n" +
                        "//   Currency groupingSize=3\n" +
                        "//   Currency groupingUsed=true\n" +
                        "//   Currency maximumFractionDigits=2\n" +
                        "//   Currency minimumFractionDigits=2\n" +
                        "//   Currency maximumIntegerDigits=2147483647\n" +
                        "//   Currency minimumIntegerDigits=1\n" +
                        "//   Currency multiplier=1\n" +
                        "//   Currency negativePrefix=-\n" +
                        "//   Currency negativeSuffix= €\n" +
                        "//   Currency parse=0\n" +
                        "//   Currency pattern=#,##0.00 ¤\n" +
                        "//   Currency positivePrefix=\n" +
                        "//   Currency positiveSuffix= €\n" +
                        "//   Currency roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Instance decimalSeparatorAlwaysShown=false\n" +
                        "//   Instance groupingSize=3\n" +
                        "//   Instance groupingUsed=true\n" +
                        "//   Instance maximumFractionDigits=3\n" +
                        "//   Instance minimumFractionDigits=0\n" +
                        "//   Instance maximumIntegerDigits=2147483647\n" +
                        "//   Instance minimumIntegerDigits=1\n" +
                        "//   Instance multiplier=1\n" +
                        "//   Instance negativePrefix=-\n" +
                        "//   Instance negativeSuffix=\n" +
                        "//   Instance parse=0\n" +
                        "//   Instance pattern=#,##0.###\n" +
                        "//   Instance positivePrefix=\n" +
                        "//   Instance positiveSuffix=\n" +
                        "//   Instance roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Integer decimalSeparatorAlwaysShown=false\n" +
                        "//   Integer groupingSize=3\n" +
                        "//   Integer groupingUsed=true\n" +
                        "//   Integer maximumFractionDigits=0\n" +
                        "//   Integer minimumFractionDigits=0\n" +
                        "//   Integer maximumIntegerDigits=2147483647\n" +
                        "//   Integer minimumIntegerDigits=1\n" +
                        "//   Integer multiplier=1\n" +
                        "//   Integer negativePrefix=-\n" +
                        "//   Integer negativeSuffix=\n" +
                        "//   Integer parse=1\n" +
                        "//   Integer pattern=#,##0\n" +
                        "//   Integer positivePrefix=\n" +
                        "//   Integer positiveSuffix=\n" +
                        "//   Integer roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Number decimalSeparatorAlwaysShown=false\n" +
                        "//   Number groupingSize=3\n" +
                        "//   Number groupingUsed=true\n" +
                        "//   Number maximumFractionDigits=3\n" +
                        "//   Number minimumFractionDigits=0\n" +
                        "//   Number maximumIntegerDigits=2147483647\n" +
                        "//   Number minimumIntegerDigits=1\n" +
                        "//   Number multiplier=1\n" +
                        "//   Number negativePrefix=-\n" +
                        "//   Number negativeSuffix=\n" +
                        "//   Number parse=0\n" +
                        "//   Number pattern=#,##0.###\n" +
                        "//   Number positivePrefix=\n" +
                        "//   Number positiveSuffix=\n" +
                        "//   Number roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "//   Percent decimalSeparatorAlwaysShown=false\n" +
                        "//   Percent groupingSize=3\n" +
                        "//   Percent groupingUsed=true\n" +
                        "//   Percent maximumFractionDigits=0\n" +
                        "//   Percent minimumFractionDigits=0\n" +
                        "//   Percent maximumIntegerDigits=2147483647\n" +
                        "//   Percent minimumIntegerDigits=1\n" +
                        "//   Percent multiplier=100\n" +
                        "//   Percent negativePrefix=-\n" +
                        "//   Percent negativeSuffix= %\n" +
                        "//   Percent parse=0\n" +
                        "//   Percent pattern=#,##0 %\n" +
                        "//   Percent positivePrefix=\n" +
                        "//   Percent positiveSuffix= %\n" +
                        "//   Percent roundingMode=HALF_EVEN\n" +
                        "//   \n" +
                        "\n" +
                        "\n" +
                        "2,1,en-AU,03,12,2,2147483647,1,1,-$,,0,¤#\\,##0.00,$,,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,1,-,,1,#\\,##0,,,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,100,-,%,0,#\\,##0%,,%,HALF_EVEN,1,fr-FR,03,12,2,2147483647,1,1,-, €,0,#\\,##0.00 ¤,, €,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,1,-,,1,#\\,##0,,,HALF_EVEN,03,13,0,2147483647,1,1,-,,0,#\\,##0.###,,,HALF_EVEN,03,10,0,2147483647,1,100,-, %,0,#\\,##0 %,, %,HALF_EVEN");
    }

    // locales=uz-Cyrl-UZ
//
// Currency decimalSeparatorAlwaysShown=false
// Currency groupingSize=3
// Currency groupingUsed=true
// Currency maximumFractionDigits=2
// Currency minimumFractionDigits=2
// Currency maximumIntegerDigits=2147483647
// Currency minimumIntegerDigits=1
// Currency multiplier=1
// Currency negativePrefix=-сўм 
// Currency negativeSuffix=
// Currency parse=0
// Currency pattern=¤ #,##0.00
// Currency positivePrefix=сўм 
// Currency positiveSuffix=
// Currency roundingMode=HALF_EVEN
//
// Instance decimalSeparatorAlwaysShown=false
// Instance groupingSize=3
// Instance groupingUsed=true
// Instance maximumFractionDigits=3
// Instance minimumFractionDigits=0
// Instance maximumIntegerDigits=2147483647
// Instance minimumIntegerDigits=1
// Instance multiplier=1
// Instance negativePrefix=-
// Instance negativeSuffix=
// Instance parse=0
// Instance pattern=#,##0.###
// Instance positivePrefix=
// Instance positiveSuffix=
// Instance roundingMode=HALF_EVEN
//
// Integer decimalSeparatorAlwaysShown=false
// Integer groupingSize=3
// Integer groupingUsed=true
// Integer maximumFractionDigits=0
// Integer minimumFractionDigits=0
// Integer maximumIntegerDigits=2147483647
// Integer minimumIntegerDigits=1
// Integer multiplier=1
// Integer negativePrefix=-
// Integer negativeSuffix=
// Integer parse=1
// Integer pattern=#,##0
// Integer positivePrefix=
// Integer positiveSuffix=
// Integer roundingMode=HALF_EVEN
//
// Number decimalSeparatorAlwaysShown=false
// Number groupingSize=3
// Number groupingUsed=true
// Number maximumFractionDigits=3
// Number minimumFractionDigits=0
// Number maximumIntegerDigits=2147483647
// Number minimumIntegerDigits=1
// Number multiplier=1
// Number negativePrefix=-
// Number negativeSuffix=
// Number parse=0
// Number pattern=#,##0.###
// Number positivePrefix=
// Number positiveSuffix=
// Number roundingMode=HALF_EVEN
//
// Percent decimalSeparatorAlwaysShown=false
// Percent groupingSize=3
// Percent groupingUsed=true
// Percent maximumFractionDigits=0
// Percent minimumFractionDigits=0
// Percent maximumIntegerDigits=2147483647
// Percent minimumIntegerDigits=1
// Percent multiplier=100
// Percent negativePrefix=-
// Percent negativeSuffix=٪
// Percent parse=0
// Percent pattern=#,##0%
// Percent positivePrefix=
// Percent positiveSuffix=٪
// Percent roundingMode=HALF_EVEN

    @Test
    public void testGenerateReadVerify() throws IOException {
        final String dataSource = this.generateData("*");
        final DataInput data = StringDataInputDataOutput.input(dataSource);
        final int count = data.readInt();

        for (int i = 0; i < count; i++) {
            final Set<Locale> locales = this.readLocales(data);

            // currency
            {
                final boolean decimalSeparatorAlwaysShown = data.readBoolean();
                final int groupingSize = data.readInt();
                final boolean groupingUsed = data.readBoolean();
                final int maximumFractionDigits = data.readInt();
                final int minimumFractionDigits = data.readInt();
                final int maximumIntegerDigits = data.readInt();
                final int minimumIntegerDigits = data.readInt();
                final int multiplier = data.readInt();
                final String negativePrefix = data.readUTF();
                final String negativeSuffix = data.readUTF();
                final int parse = data.readInt();
                final String pattern = data.readUTF();
                final String positivePrefix = data.readUTF();
                final String positiveSuffix = data.readUTF();
                final RoundingMode roundingMode = RoundingMode.valueOf(data.readUTF());

                for (final Locale locale : locales) {
                    final DecimalFormat symbols = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);

                    assertEquals(symbols.isDecimalSeparatorAlwaysShown(), decimalSeparatorAlwaysShown, () -> "decimalSeparator for currency " + locale);
                    assertEquals(symbols.getGroupingSize(), groupingSize, () -> "groupingSize for currency " + locale);
                    assertEquals(symbols.isGroupingUsed(), groupingUsed, () -> "groupingUsed for currency " + locale);
                    assertEquals(symbols.getMaximumFractionDigits(), maximumFractionDigits, () -> "maximumFractionDigits for currency " + locale);
                    assertEquals(symbols.getMinimumFractionDigits(), minimumFractionDigits, () -> "minimumFractionDigits for currency " + locale);
                    assertEquals(symbols.getMaximumIntegerDigits(), maximumIntegerDigits, () -> "maximumIntegerDigits for currency " + locale);
                    assertEquals(symbols.getMinimumIntegerDigits(), minimumIntegerDigits, () -> "minimumIntegerDigits for currency " + locale);
                    assertEquals(symbols.getMultiplier(), multiplier, () -> "multiplier for currency " + locale);
                    assertEquals(symbols.getNegativePrefix(), negativePrefix, () -> "negativePrefix for currency " + locale);
                    assertEquals(symbols.getNegativeSuffix(), negativeSuffix, () -> "negativeSuffix for currency " + locale);
                    assertEquals(symbols.toPattern(), pattern, () -> "pattern for currency " + locale);
                    assertEquals(symbols.getPositivePrefix(), positivePrefix, () -> "positivePrefix for currency " + locale);
                    assertEquals(symbols.getPositiveSuffix(), positiveSuffix, () -> "positiveSuffix for currency " + locale);
                    assertEquals(symbols.getRoundingMode(), roundingMode, () -> "roundingMode for currency " + locale);
                }
            }

            // instance
            {
                final boolean decimalSeparatorAlwaysShown = data.readBoolean();
                final int groupingSize = data.readInt();
                final boolean groupingUsed = data.readBoolean();
                final int maximumFractionDigits = data.readInt();
                final int minimumFractionDigits = data.readInt();
                final int maximumIntegerDigits = data.readInt();
                final int minimumIntegerDigits = data.readInt();
                final int multiplier = data.readInt();
                final String negativePrefix = data.readUTF();
                final String negativeSuffix = data.readUTF();
                final int parse = data.readInt();
                final String pattern = data.readUTF();
                final String positivePrefix = data.readUTF();
                final String positiveSuffix = data.readUTF();
                final RoundingMode roundingMode = RoundingMode.valueOf(data.readUTF());

                for (final Locale locale : locales) {
                    final DecimalFormat symbols = (DecimalFormat) DecimalFormat.getInstance(locale);

                    assertEquals(symbols.isDecimalSeparatorAlwaysShown(), decimalSeparatorAlwaysShown, () -> "decimalSeparator for instance " + locale);
                    assertEquals(symbols.getGroupingSize(), groupingSize, () -> "groupingSize for instance " + locale);
                    assertEquals(symbols.isGroupingUsed(), groupingUsed, () -> "groupingUsed for instance " + locale);
                    assertEquals(symbols.getMaximumFractionDigits(), maximumFractionDigits, () -> "maximumFractionDigits for instance " + locale);
                    assertEquals(symbols.getMinimumFractionDigits(), minimumFractionDigits, () -> "minimumFractionDigits for instance " + locale);
                    assertEquals(symbols.getMaximumIntegerDigits(), maximumIntegerDigits, () -> "maximumIntegerDigits for instance " + locale);
                    assertEquals(symbols.getMinimumIntegerDigits(), minimumIntegerDigits, () -> "minimumIntegerDigits for instance " + locale);
                    assertEquals(symbols.getMultiplier(), multiplier, () -> "multiplier for instance " + locale);
                    assertEquals(symbols.getNegativePrefix(), negativePrefix, () -> "negativePrefix for instance " + locale);
                    assertEquals(symbols.getNegativeSuffix(), negativeSuffix, () -> "negativeSuffix for instance " + locale);
                    assertEquals(symbols.toPattern(), pattern, () -> "pattern for instance " + locale);
                    assertEquals(symbols.getPositivePrefix(), positivePrefix, () -> "positivePrefix for instance " + locale);
                    assertEquals(symbols.getPositiveSuffix(), positiveSuffix, () -> "positiveSuffix for instance " + locale);
                    assertEquals(symbols.getRoundingMode(), roundingMode, () -> "roundingMode for instance " + locale);
                }
            }

            // integer
            {
                final boolean decimalSeparatorAlwaysShown = data.readBoolean();
                final int groupingSize = data.readInt();
                final boolean groupingUsed = data.readBoolean();
                final int maximumFractionDigits = data.readInt();
                final int minimumFractionDigits = data.readInt();
                final int maximumIntegerDigits = data.readInt();
                final int minimumIntegerDigits = data.readInt();
                final int multiplier = data.readInt();
                final String negativePrefix = data.readUTF();
                final String negativeSuffix = data.readUTF();
                final int parse = data.readInt();
                final String pattern = data.readUTF();
                final String positivePrefix = data.readUTF();
                final String positiveSuffix = data.readUTF();
                final RoundingMode roundingMode = RoundingMode.valueOf(data.readUTF());

                for (final Locale locale : locales) {
                    final DecimalFormat symbols = (DecimalFormat) DecimalFormat.getIntegerInstance(locale);

                    assertEquals(symbols.isDecimalSeparatorAlwaysShown(), decimalSeparatorAlwaysShown, () -> "decimalSeparator for integer " + locale);
                    assertEquals(symbols.getGroupingSize(), groupingSize, () -> "groupingSize for integer " + locale);
                    assertEquals(symbols.isGroupingUsed(), groupingUsed, () -> "groupingUsed for integer " + locale);
                    assertEquals(symbols.getMaximumFractionDigits(), maximumFractionDigits, () -> "maximumFractionDigits for integer " + locale);
                    assertEquals(symbols.getMinimumFractionDigits(), minimumFractionDigits, () -> "minimumFractionDigits for integer " + locale);
                    assertEquals(symbols.getMaximumIntegerDigits(), maximumIntegerDigits, () -> "maximumIntegerDigits for integer " + locale);
                    assertEquals(symbols.getMinimumIntegerDigits(), minimumIntegerDigits, () -> "minimumIntegerDigits for integer " + locale);
                    assertEquals(symbols.getMultiplier(), multiplier, () -> "multiplier for integer " + locale);
                    assertEquals(symbols.getNegativePrefix(), negativePrefix, () -> "negativePrefix for integer " + locale);
                    assertEquals(symbols.getNegativeSuffix(), negativeSuffix, () -> "negativeSuffix for integer " + locale);
                    assertEquals(symbols.toPattern(), pattern, () -> "pattern for integer " + locale);
                    assertEquals(symbols.getPositivePrefix(), positivePrefix, () -> "positivePrefix for integer " + locale);
                    assertEquals(symbols.getPositiveSuffix(), positiveSuffix, () -> "positiveSuffix for integer " + locale);
                    assertEquals(symbols.getRoundingMode(), roundingMode, () -> "roundingMode for integer " + locale);
                }
            }

            // number
            {
                final boolean decimalSeparatorAlwaysShown = data.readBoolean();
                final int groupingSize = data.readInt();
                final boolean groupingUsed = data.readBoolean();
                final int maximumFractionDigits = data.readInt();
                final int minimumFractionDigits = data.readInt();
                final int maximumIntegerDigits = data.readInt();
                final int minimumIntegerDigits = data.readInt();
                final int multiplier = data.readInt();
                final String negativePrefix = data.readUTF();
                final String negativeSuffix = data.readUTF();
                final int parse = data.readInt();
                final String pattern = data.readUTF();
                final String positivePrefix = data.readUTF();
                final String positiveSuffix = data.readUTF();
                final RoundingMode roundingMode = RoundingMode.valueOf(data.readUTF());

                for (final Locale locale : locales) {
                    final DecimalFormat symbols = (DecimalFormat) DecimalFormat.getNumberInstance(locale);

                    assertEquals(symbols.isDecimalSeparatorAlwaysShown(), decimalSeparatorAlwaysShown, () -> "decimalSeparator for number " + locale);
                    assertEquals(symbols.getGroupingSize(), groupingSize, () -> "groupingSize for number " + locale);
                    assertEquals(symbols.isGroupingUsed(), groupingUsed, () -> "groupingUsed for number " + locale);
                    assertEquals(symbols.getMaximumFractionDigits(), maximumFractionDigits, () -> "maximumFractionDigits for number " + locale);
                    assertEquals(symbols.getMinimumFractionDigits(), minimumFractionDigits, () -> "minimumFractionDigits for number " + locale);
                    assertEquals(symbols.getMaximumIntegerDigits(), maximumIntegerDigits, () -> "maximumIntegerDigits for number " + locale);
                    assertEquals(symbols.getMinimumIntegerDigits(), minimumIntegerDigits, () -> "minimumIntegerDigits for number " + locale);
                    assertEquals(symbols.getMultiplier(), multiplier, () -> "multiplier for number " + locale);
                    assertEquals(symbols.getNegativePrefix(), negativePrefix, () -> "negativePrefix for number " + locale);
                    assertEquals(symbols.getNegativeSuffix(), negativeSuffix, () -> "negativeSuffix for number " + locale);
                    assertEquals(symbols.toPattern(), pattern, () -> "pattern for number " + locale);
                    assertEquals(symbols.getPositivePrefix(), positivePrefix, () -> "positivePrefix for number " + locale);
                    assertEquals(symbols.getPositiveSuffix(), positiveSuffix, () -> "positiveSuffix for number " + locale);
                    assertEquals(symbols.getRoundingMode(), roundingMode, () -> "roundingMode for number " + locale);
                }
            }

            // percent
            {
                final boolean decimalSeparatorAlwaysShown = data.readBoolean();
                final int groupingSize = data.readInt();
                final boolean groupingUsed = data.readBoolean();
                final int maximumFractionDigits = data.readInt();
                final int minimumFractionDigits = data.readInt();
                final int maximumIntegerDigits = data.readInt();
                final int minimumIntegerDigits = data.readInt();
                final int multiplier = data.readInt();
                final String negativePrefix = data.readUTF();
                final String negativeSuffix = data.readUTF();
                final int parse = data.readInt();
                final String pattern = data.readUTF();
                final String positivePrefix = data.readUTF();
                final String positiveSuffix = data.readUTF();
                final RoundingMode roundingMode = RoundingMode.valueOf(data.readUTF());

                for (final Locale locale : locales) {
                    final DecimalFormat symbols = (DecimalFormat) DecimalFormat.getPercentInstance(locale);

                    assertEquals(symbols.isDecimalSeparatorAlwaysShown(), decimalSeparatorAlwaysShown, () -> "decimalSeparator for percent " + locale);
                    assertEquals(symbols.getGroupingSize(), groupingSize, () -> "groupingSize for percent " + locale);
                    assertEquals(symbols.isGroupingUsed(), groupingUsed, () -> "groupingUsed for percent " + locale);
                    assertEquals(symbols.getMaximumFractionDigits(), maximumFractionDigits, () -> "maximumFractionDigits for percent " + locale);
                    assertEquals(symbols.getMinimumFractionDigits(), minimumFractionDigits, () -> "minimumFractionDigits for percent " + locale);
                    assertEquals(symbols.getMaximumIntegerDigits(), maximumIntegerDigits, () -> "maximumIntegerDigits for percent " + locale);
                    assertEquals(symbols.getMinimumIntegerDigits(), minimumIntegerDigits, () -> "minimumIntegerDigits for percent " + locale);
                    assertEquals(symbols.getMultiplier(), multiplier, () -> "multiplier for percent " + locale);
                    assertEquals(symbols.getNegativePrefix(), negativePrefix, () -> "negativePrefix for percent " + locale);
                    assertEquals(symbols.getNegativeSuffix(), negativeSuffix, () -> "negativeSuffix for percent " + locale);
                    assertEquals(symbols.toPattern(), pattern, () -> "pattern for percent " + locale);
                    assertEquals(symbols.getPositivePrefix(), positivePrefix, () -> "positivePrefix for percent " + locale);
                    assertEquals(symbols.getPositiveSuffix(), positiveSuffix, () -> "positiveSuffix for percent " + locale);
                    assertEquals(symbols.getRoundingMode(), roundingMode, () -> "roundingMode for percent " + locale);
                }
            }
        }
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<DecimalFormatProviderTool> type() {
        return DecimalFormatProviderTool.class;
    }

    @Override
    void generate0(final Set<String> locales,
                   final DataOutput data,
                   final IndentingPrinter comments) throws IOException {
        DecimalFormatProviderTool.generate(LocaleAwareAnnotationProcessorTool.toLocales(locales),
                data,
                comments);
    }
}
