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

public final class DateFormatSymbolsProviderToolTest implements ClassTesting<DateFormatSymbolsProviderTool> {

    @Test
    public void testENAU() {
        final String expected = "  static void register(final java.util.function.Consumer<DateFormatSymbolsProvider> registry) {\n" +
                "    registry.accept(new DateFormatSymbolsProvider(\n" +
                "      \"EN-AU\", // locales\n" +
                "      \"am\\tpm\", // ampm\n" +
                "      \"BC\\tAD\", // eras\n" +
                "      \"January\\tFebruary\\tMarch\\tApril\\tMay\\tJune\\tJuly\\tAugust\\tSeptember\\tOctober\\tNovember\\tDecember\", // months\n" +
                "      \"Jan.\\tFeb.\\tMar.\\tApr.\\tMay\\tJun.\\tJul.\\tAug.\\tSep.\\tOct.\\tNov.\\tDec.\", // shortMonths\n" +
                "      \"Sun.\\tMon.\\tTue.\\tWed.\\tThu.\\tFri.\\tSat.\", // shortWeekdays\n" +
                "      \"Sunday\\tMonday\\tTuesday\\tWednesday\\tThursday\\tFriday\\tSaturday\" // weekdays\n" +
                "    ));\n" +
                "    \n" +
                "  }\n";
        assertEquals(expected, DateFormatSymbolsProviderTool.generateMethod(Sets.of("EN-AU")));
    }

    @Test
    public void testFRFR() {
        final String expected = "  static void register(final java.util.function.Consumer<DateFormatSymbolsProvider> registry) {\n" +
                "    registry.accept(new DateFormatSymbolsProvider(\n" +
                "      \"FR-FR\", // locales\n" +
                "      \"AM\\tPM\", // ampm\n" +
                "      \"av. J.-C.\\tap. J.-C.\", // eras\n" +
                "      \"janvier\\tfévrier\\tmars\\tavril\\tmai\\tjuin\\tjuillet\\taoût\\tseptembre\\toctobre\\tnovembre\\tdécembre\", // months\n" +
                "      \"janv.\\tfévr.\\tmars\\tavr.\\tmai\\tjuin\\tjuil.\\taoût\\tsept.\\toct.\\tnov.\\tdéc.\", // shortMonths\n" +
                "      \"dim.\\tlun.\\tmar.\\tmer.\\tjeu.\\tven.\\tsam.\", // shortWeekdays\n" +
                "      \"dimanche\\tlundi\\tmardi\\tmercredi\\tjeudi\\tvendredi\\tsamedi\" // weekdays\n" +
                "    ));\n" +
                "    \n" +
                "  }\n";
        assertEquals(expected, DateFormatSymbolsProviderTool.generateMethod(Sets.of("FR-FR")));
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<DateFormatSymbolsProviderTool> type() {
        return DateFormatSymbolsProviderTool.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
