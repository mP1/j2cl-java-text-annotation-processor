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
import walkingkooka.collect.list.Lists;
import walkingkooka.j2cl.java.io.string.StringDataInputDataOutput;
import walkingkooka.j2cl.locale.annotationprocessor.LocaleAwareAnnotationProcessorTool;
import walkingkooka.text.printer.IndentingPrinter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DateFormatSymbolsProviderToolTest extends ProviderToolTestCase<DateFormatSymbolsProviderTool> {

    @Test
    public void testENAU() throws IOException {
        this.generateAndCheck("en-AU",
                "// locales=en-AU\n" +
                        "//   ampm=am, pm\n" +
                        "//   eras=BC, AD\n" +
                        "//   months=January, February, March, April, May, June, July, August, September, October, November, December, \n" +
                        "//   shortMonths=Jan., Feb., Mar., Apr., May, Jun., Jul., Aug., Sep., Oct., Nov., Dec., \n" +
                        "//   shortWeekdays=Sun., Mon., Tue., Wed., Thu., Fri., Sat.\n" +
                        "//   weekdays=Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday\n" +
                        "// \n" +
                        "\n" +
                        "\n" +
                        "1,1,en-AU,2,am,pm,2,BC,AD,13,January,February,March,April,May,June,July,August,September,October,November,December,,13,Jan.,Feb.,Mar.,Apr.,May,Jun.,Jul.,Aug.,Sep.,Oct.,Nov.,Dec.,,7,Sun.,Mon.,Tue.,Wed.,Thu.,Fri.,Sat.,7,Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday");
    }

    @Test
    public void testFRFR() throws IOException {
        this.generateAndCheck("fr-FR",
                "// locales=fr-FR\n" +
                        "//   ampm=AM, PM\n" +
                        "//   eras=av. J.-C., ap. J.-C.\n" +
                        "//   months=janvier, février, mars, avril, mai, juin, juillet, août, septembre, octobre, novembre, décembre, \n" +
                        "//   shortMonths=janv., févr., mars, avr., mai, juin, juil., août, sept., oct., nov., déc., \n" +
                        "//   shortWeekdays=dim., lun., mar., mer., jeu., ven., sam.\n" +
                        "//   weekdays=dimanche, lundi, mardi, mercredi, jeudi, vendredi, samedi\n" +
                        "// \n" +
                        "\n" +
                        "\n" +
                        "1,1,fr-FR,2,AM,PM,2,av. J.-C.,ap. J.-C.,13,janvier,février,mars,avril,mai,juin,juillet,août,septembre,octobre,novembre,décembre,,13,janv.,févr.,mars,avr.,mai,juin,juil.,août,sept.,oct.,nov.,déc.,,7,dim.,lun.,mar.,mer.,jeu.,ven.,sam.,7,dimanche,lundi,mardi,mercredi,jeudi,vendredi,samedi");
    }

    @Test
    public void testENAUENNZ() throws IOException {
        this.generateAndCheck("en-AU,en-NZ",
                "// locales=en-AU\n" +
                        "//   ampm=am, pm\n" +
                        "//   eras=BC, AD\n" +
                        "//   months=January, February, March, April, May, June, July, August, September, October, November, December, \n" +
                        "//   shortMonths=Jan., Feb., Mar., Apr., May, Jun., Jul., Aug., Sep., Oct., Nov., Dec., \n" +
                        "//   shortWeekdays=Sun., Mon., Tue., Wed., Thu., Fri., Sat.\n" +
                        "//   weekdays=Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday\n" +
                        "// \n" +
                        "// locales=en-NZ\n" +
                        "//   ampm=AM, PM\n" +
                        "//   eras=BC, AD\n" +
                        "//   months=January, February, March, April, May, June, July, August, September, October, November, December, \n" +
                        "//   shortMonths=Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec, \n" +
                        "//   shortWeekdays=Sun, Mon, Tue, Wed, Thu, Fri, Sat\n" +
                        "//   weekdays=Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday\n" +
                        "// \n" +
                        "\n" +
                        "\n" +
                        "2,1,en-AU,2,am,pm,2,BC,AD,13,January,February,March,April,May,June,July,August,September,October,November,December,,13,Jan.,Feb.,Mar.,Apr.,May,Jun.,Jul.,Aug.,Sep.,Oct.,Nov.,Dec.,,7,Sun.,Mon.,Tue.,Wed.,Thu.,Fri.,Sat.,7,Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,1,en-NZ,2,AM,PM,2,BC,AD,13,January,February,March,April,May,June,July,August,September,October,November,December,,13,Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec,,7,Sun,Mon,Tue,Wed,Thu,Fri,Sat,7,Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday");
    }

    @Test
    public void testGenerateReadVerify() throws IOException {
        final String dataSource = this.generateData("*");
        final DataInput data = StringDataInputDataOutput.input(dataSource);
        final int count = data.readInt();

// locales=vai-Latn, vai-Latn-LR
// ampm=AM, PM
// eras=BCE, CE
// months=luukao kemã, ɓandaɓu, vɔɔ, fulu, goo, 6, 7, kɔnde, saah, galo, kenpkato ɓololɔ, luukao lɔma,
// shortMonths=luukao kemã, ɓandaɓu, vɔɔ, fulu, goo, 6, 7, kɔnde, saah, galo, kenpkato ɓololɔ, luukao lɔma,
// shortWeekdays=lahadi, tɛɛnɛɛ, talata, alaba, aimisa, aijima, siɓiti
// weekdays=lahadi, tɛɛnɛɛ, talata, alaba, aimisa, aijima, siɓiti
        for(int i = 0; i < count; i++) {
            final Set<Locale> locales = this.readLocales(data);
            final List<String> ampms = this.readStrings(data);
            final List<String> eras = this.readStrings(data);
            final List<String> months = this.readStrings(data);
            final List<String> shortMonths = this.readStrings(data);
            final List<String> shortWeekdays = this.readStrings(data);
            final List<String> weekdays = this.readStrings(data);

            shortWeekdays.add(0, "");
            weekdays.add(0, "");

            for(final Locale locale : locales) {
                final DateFormatSymbols symbols = DateFormatSymbols.getInstance(locale);
                assertEquals(ampms, Lists.of(symbols.getAmPmStrings()), () -> "ampms for " + locale);
                assertEquals(eras, Lists.of(symbols.getEras()), () -> "eras for " + locale);
                assertEquals(months, Lists.of(symbols.getMonths()), () -> "months for " + locale);
                assertEquals(shortMonths, Lists.of(symbols.getShortMonths()), () -> "shortMonths for " + locale);
                assertEquals(shortWeekdays, Lists.of(symbols.getShortWeekdays()), () -> "shortWeekdays for " + locale);
                assertEquals(weekdays, Lists.of(symbols.getWeekdays()), () -> "weekdays for " + locale);
            }
        }
    }
    // ClassTesting.....................................................................................................

    @Override
    public Class<DateFormatSymbolsProviderTool> type() {
        return DateFormatSymbolsProviderTool.class;
    }

    @Override
    void generate0(final Set<String> locales,
                   final DataOutput data,
                   final IndentingPrinter comments) throws IOException {
        DateFormatSymbolsProviderTool.generate("?",
                LocaleAwareAnnotationProcessorTool.toLocales(locales),
                data,
                comments);
    }
}
