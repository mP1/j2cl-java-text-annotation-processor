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
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Set;

public final class DecimalFormatSymbolsProviderToolTest extends ProviderToolTestCase<DecimalFormatSymbolsProviderTool> {

    @Test
    public void testENAU() throws IOException {
        this.generateAndCheck("en-AU",
                "// locales=en-AU\n" +
                        "//   decimalSeparator=.\n" +
                        "//   digit=#\n" +
                        "//   exponentSeparator=e\n" +
                        "//   groupingSeparator=,\n" +
                        "//   infinity=∞\n" +
                        "//   internationalCurrencySymbol=AUD\n" +
                        "//   minusSign=-\n" +
                        "//   monetaryDecimalSeparator=.\n" +
                        "//   nan=NaN\n" +
                        "//   patternSeparator=;\n" +
                        "//   percent=%\n" +
                        "//   perMill=‰\n" +
                        "//   zeroDigit=0\n" +
                        "// \n" +
                        "\n" +
                        "\n" +
                        "1,1,en-AU,.#e,,∞,AUD,-.NaN,;%‰0");
    }

    @Test
    public void testFRFR() throws IOException {
        this.generateAndCheck("fr-FR",
                "// locales=fr-FR\n" +
                        "//   decimalSeparator=,\n" +
                        "//   digit=#\n" +
                        "//   exponentSeparator=E\n" +
                        "//   groupingSeparator= \n" +
                        "//   infinity=∞\n" +
                        "//   internationalCurrencySymbol=EUR\n" +
                        "//   minusSign=-\n" +
                        "//   monetaryDecimalSeparator=,\n" +
                        "//   nan=NaN\n" +
                        "//   patternSeparator=;\n" +
                        "//   percent=%\n" +
                        "//   perMill=‰\n" +
                        "//   zeroDigit=0\n" +
                        "// \n" +
                        "\n" +
                        "\n" +
                        "1,1,fr-FR,,#E, ∞,EUR,-,NaN,;%‰0");
    }

    @Test
    public void testENAUFRFR() throws IOException {
        this.generateAndCheck("en-AU,fr-FR",
                "// locales=en-AU\n" +
                        "//   decimalSeparator=.\n" +
                        "//   digit=#\n" +
                        "//   exponentSeparator=e\n" +
                        "//   groupingSeparator=,\n" +
                        "//   infinity=∞\n" +
                        "//   internationalCurrencySymbol=AUD\n" +
                        "//   minusSign=-\n" +
                        "//   monetaryDecimalSeparator=.\n" +
                        "//   nan=NaN\n" +
                        "//   patternSeparator=;\n" +
                        "//   percent=%\n" +
                        "//   perMill=‰\n" +
                        "//   zeroDigit=0\n" +
                        "// \n" +
                        "// locales=fr-FR\n" +
                        "//   decimalSeparator=,\n" +
                        "//   digit=#\n" +
                        "//   exponentSeparator=E\n" +
                        "//   groupingSeparator= \n" +
                        "//   infinity=∞\n" +
                        "//   internationalCurrencySymbol=EUR\n" +
                        "//   minusSign=-\n" +
                        "//   monetaryDecimalSeparator=,\n" +
                        "//   nan=NaN\n" +
                        "//   patternSeparator=;\n" +
                        "//   percent=%\n" +
                        "//   perMill=‰\n" +
                        "//   zeroDigit=0\n" +
                        "// \n" +
                        "\n" +
                        "\n" +
                        "2,1,en-AU,.#e,,∞,AUD,-.NaN,;%‰01,fr-FR,,#E, ∞,EUR,-,NaN,;%‰0");
    }

// locales=, ak, am, asa, bem, bez, bm, bo, brx, cgg, chr, cu, cy, dav, ebu, en, en-001, en-DG, es-419, fil, ga, gd, gu, guz, gv, ha, haw, hi, ig, ii, ja, ji, ji-001, jmc, kam, kde, ki, kln, kn, ko, kok, ksb, kw, lag, lg, lkt, luo, luy, mas, mer, mg, mgo, ml, mn, ms, mt, naq, nd, nus, nyn, om, or, pa, pa-Guru, prg, prg-001, qu, rof, rwk, sah, saq, sbp, si, sn, so, sw, ta, te, teo, th, ti, ug, und, vai, vai-Latn, vai-Vaii, vo, vo-001, vun, xog, yi, yi-001, yo, zh, zh-Hans, zu
// decimalSeparator=.
// digit=#
// exponentSeparator=E
// groupingSeparator=E
// infinity=E
// internationalCurrencySymbol=E
// minusSign=E
// monetaryDecimalSeparator=.
// nan=NaN
// patternSeparator=;
// percent=%
// perMill=‰
// zeroDigit=0

    @Test
    public void testGenerateReadVerify() throws IOException {
        final String dataSource = this.generateData("*");
        final DataInput data = StringDataInputDataOutput.input(dataSource);
        final int count = data.readInt();

        for (int i = 0; i < count; i++) {
            final Set<Locale> locales = this.readLocales(data);

            final char decimalSeparator = data.readChar();
            final char digit = data.readChar();
            final String exponentSeparator = data.readUTF();
            final char groupingSeparator = data.readChar();
            final String infinity = data.readUTF();
            final String internationalCurrencySymbol = data.readUTF();
            final char minusSign = data.readChar();
            final char monetaryDecimalSeparator = data.readChar();
            final String nan = data.readUTF();
            final char patternSeparator = data.readChar();
            final char percent = data.readChar();
            final char perMill = data.readChar();
            final char zeroDigit = data.readChar();

            for (final Locale locale : locales) {
                final DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(locale);

                this.checkEquals(symbols.getDecimalSeparator(), decimalSeparator, () -> "decimalSeparator for " + locale);
                this.checkEquals(symbols.getDigit(), digit, () -> "digit for " + locale);
                this.checkEquals(symbols.getExponentSeparator(), exponentSeparator, () -> "exponentSeparator for " + locale);
                this.checkEquals(symbols.getGroupingSeparator(), groupingSeparator, () -> "groupingSeparator for " + locale);
                this.checkEquals(symbols.getInfinity(), infinity, () -> "infinity for " + locale);
                this.checkEquals(symbols.getInternationalCurrencySymbol(), internationalCurrencySymbol, () -> "internationalCurrencySymbol for " + locale);
                this.checkEquals(symbols.getMinusSign(), minusSign, () -> "minusSign for " + locale);
                this.checkEquals(symbols.getMonetaryDecimalSeparator(), monetaryDecimalSeparator, () -> "monetaryDecimalSeparator for " + locale);
                this.checkEquals(symbols.getNaN(), nan, () -> "nan for " + locale);
                this.checkEquals(symbols.getPatternSeparator(), patternSeparator, () -> "patternSeparator for " + locale);
                this.checkEquals(symbols.getPercent(), percent, () -> "percent for " + locale);
                this.checkEquals(symbols.getPerMill(), perMill, () -> "perMill for " + locale);
                this.checkEquals(symbols.getZeroDigit(), zeroDigit, () -> "zeroDigit for " + locale);
            }
        }
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<DecimalFormatSymbolsProviderTool> type() {
        return DecimalFormatSymbolsProviderTool.class;
    }

    @Override
    void generate0(final Set<String> locales,
                   final DataOutput data,
                   final IndentingPrinter comments) throws IOException {
        DecimalFormatSymbolsProviderTool.generate("?",
                LocaleAwareAnnotationProcessorTool.toLocales(locales),
                data,
                comments);
    }
}
