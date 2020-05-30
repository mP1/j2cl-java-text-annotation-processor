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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import walkingkooka.NeverError;
import walkingkooka.j2cl.java.io.string.StringDataInputDataOutput;
import walkingkooka.j2cl.locale.annotationprocessor.LocaleAwareAnnotationProcessorTool;
import walkingkooka.text.printer.IndentingPrinter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;
import java.util.function.Supplier;

public final class DateFormatProviderToolTest extends ProviderToolTestCase<DateFormatProviderTool> {

    @Test
    public void testENAU() throws IOException {
        this.generateAndCheck("en-AU",
                "// locales=en-AU\n" +
                        "// \n" +
                        "//   DateInstance SHORT pattern=d/M/yy\n" +
                        "//   DateInstance MEDIUM pattern=d MMM y\n" +
                        "//   DateInstance LONG pattern=d MMMM y\n" +
                        "//   DateInstance FULL pattern=EEEE, d MMMM y\n" +
                        "//   DateTimeInstance SHORT, SHORT pattern=d/M/yy, h:mm a\n" +
                        "//   DateTimeInstance SHORT, MEDIUM pattern=d/M/yy, h:mm:ss a\n" +
                        "//   DateTimeInstance SHORT, LONG pattern=d/M/yy, h:mm:ss a z\n" +
                        "//   DateTimeInstance SHORT, FULL pattern=d/M/yy, h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance MEDIUM, SHORT pattern=d MMM y, h:mm a\n" +
                        "//   DateTimeInstance MEDIUM, MEDIUM pattern=d MMM y, h:mm:ss a\n" +
                        "//   DateTimeInstance MEDIUM, LONG pattern=d MMM y, h:mm:ss a z\n" +
                        "//   DateTimeInstance MEDIUM, FULL pattern=d MMM y, h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance LONG, SHORT pattern=d MMMM y, h:mm a\n" +
                        "//   DateTimeInstance LONG, MEDIUM pattern=d MMMM y, h:mm:ss a\n" +
                        "//   DateTimeInstance LONG, LONG pattern=d MMMM y 'at' h:mm:ss a z\n" +
                        "//   DateTimeInstance LONG, FULL pattern=d MMMM y 'at' h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance FULL, SHORT pattern=EEEE, d MMMM y, h:mm a\n" +
                        "//   DateTimeInstance FULL, MEDIUM pattern=EEEE, d MMMM y, h:mm:ss a\n" +
                        "//   DateTimeInstance FULL, LONG pattern=EEEE, d MMMM y 'at' h:mm:ss a z\n" +
                        "//   DateTimeInstance FULL, FULL pattern=EEEE, d MMMM y 'at' h:mm:ss a zzzz\n" +
                        "//   TimeInstance SHORT pattern=h:mm a\n" +
                        "//   TimeInstance MEDIUM pattern=h:mm:ss a\n" +
                        "//   TimeInstance LONG pattern=h:mm:ss a z\n" +
                        "//   TimeInstance FULL pattern=h:mm:ss a zzzz\n" +
                        "\n" +
                        "\n" +
                        "1,1,en-AU,d/M/yy,d MMM y,d MMMM y,EEEE\\, d MMMM y,d/M/yy\\, h:mm a,d/M/yy\\, h:mm:ss a,d/M/yy\\, h:mm:ss a z,d/M/yy\\, h:mm:ss a zzzz,d MMM y\\, h:mm a,d MMM y\\, h:mm:ss a,d MMM y\\, h:mm:ss a z,d MMM y\\, h:mm:ss a zzzz,d MMMM y\\, h:mm a,d MMMM y\\, h:mm:ss a,d MMMM y 'at' h:mm:ss a z,d MMMM y 'at' h:mm:ss a zzzz,EEEE\\, d MMMM y\\, h:mm a,EEEE\\, d MMMM y\\, h:mm:ss a,EEEE\\, d MMMM y 'at' h:mm:ss a z,EEEE\\, d MMMM y 'at' h:mm:ss a zzzz,h:mm a,h:mm:ss a,h:mm:ss a z,h:mm:ss a zzzz");
    }

    @Test
    public void testENAUENNZ() throws IOException {
        this.generateAndCheck("en-AU,en-NZ",
                "// locales=en-AU\n" +
                        "// \n" +
                        "//   DateInstance SHORT pattern=d/M/yy\n" +
                        "//   DateInstance MEDIUM pattern=d MMM y\n" +
                        "//   DateInstance LONG pattern=d MMMM y\n" +
                        "//   DateInstance FULL pattern=EEEE, d MMMM y\n" +
                        "//   DateTimeInstance SHORT, SHORT pattern=d/M/yy, h:mm a\n" +
                        "//   DateTimeInstance SHORT, MEDIUM pattern=d/M/yy, h:mm:ss a\n" +
                        "//   DateTimeInstance SHORT, LONG pattern=d/M/yy, h:mm:ss a z\n" +
                        "//   DateTimeInstance SHORT, FULL pattern=d/M/yy, h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance MEDIUM, SHORT pattern=d MMM y, h:mm a\n" +
                        "//   DateTimeInstance MEDIUM, MEDIUM pattern=d MMM y, h:mm:ss a\n" +
                        "//   DateTimeInstance MEDIUM, LONG pattern=d MMM y, h:mm:ss a z\n" +
                        "//   DateTimeInstance MEDIUM, FULL pattern=d MMM y, h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance LONG, SHORT pattern=d MMMM y, h:mm a\n" +
                        "//   DateTimeInstance LONG, MEDIUM pattern=d MMMM y, h:mm:ss a\n" +
                        "//   DateTimeInstance LONG, LONG pattern=d MMMM y 'at' h:mm:ss a z\n" +
                        "//   DateTimeInstance LONG, FULL pattern=d MMMM y 'at' h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance FULL, SHORT pattern=EEEE, d MMMM y, h:mm a\n" +
                        "//   DateTimeInstance FULL, MEDIUM pattern=EEEE, d MMMM y, h:mm:ss a\n" +
                        "//   DateTimeInstance FULL, LONG pattern=EEEE, d MMMM y 'at' h:mm:ss a z\n" +
                        "//   DateTimeInstance FULL, FULL pattern=EEEE, d MMMM y 'at' h:mm:ss a zzzz\n" +
                        "//   TimeInstance SHORT pattern=h:mm a\n" +
                        "//   TimeInstance MEDIUM pattern=h:mm:ss a\n" +
                        "//   TimeInstance LONG pattern=h:mm:ss a z\n" +
                        "//   TimeInstance FULL pattern=h:mm:ss a zzzz\n" +
                        "// locales=en-NZ\n" +
                        "// \n" +
                        "//   DateInstance SHORT pattern=d/MM/yy\n" +
                        "//   DateInstance MEDIUM pattern=d/MM/y\n" +
                        "//   DateInstance LONG pattern=d MMMM y\n" +
                        "//   DateInstance FULL pattern=EEEE, d MMMM y\n" +
                        "//   DateTimeInstance SHORT, SHORT pattern=d/MM/yy, h:mm a\n" +
                        "//   DateTimeInstance SHORT, MEDIUM pattern=d/MM/yy, h:mm:ss a\n" +
                        "//   DateTimeInstance SHORT, LONG pattern=d/MM/yy, h:mm:ss a z\n" +
                        "//   DateTimeInstance SHORT, FULL pattern=d/MM/yy, h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance MEDIUM, SHORT pattern=d/MM/y, h:mm a\n" +
                        "//   DateTimeInstance MEDIUM, MEDIUM pattern=d/MM/y, h:mm:ss a\n" +
                        "//   DateTimeInstance MEDIUM, LONG pattern=d/MM/y, h:mm:ss a z\n" +
                        "//   DateTimeInstance MEDIUM, FULL pattern=d/MM/y, h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance LONG, SHORT pattern=d MMMM y, h:mm a\n" +
                        "//   DateTimeInstance LONG, MEDIUM pattern=d MMMM y, h:mm:ss a\n" +
                        "//   DateTimeInstance LONG, LONG pattern=d MMMM y 'at' h:mm:ss a z\n" +
                        "//   DateTimeInstance LONG, FULL pattern=d MMMM y 'at' h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance FULL, SHORT pattern=EEEE, d MMMM y, h:mm a\n" +
                        "//   DateTimeInstance FULL, MEDIUM pattern=EEEE, d MMMM y, h:mm:ss a\n" +
                        "//   DateTimeInstance FULL, LONG pattern=EEEE, d MMMM y 'at' h:mm:ss a z\n" +
                        "//   DateTimeInstance FULL, FULL pattern=EEEE, d MMMM y 'at' h:mm:ss a zzzz\n" +
                        "//   TimeInstance SHORT pattern=h:mm a\n" +
                        "//   TimeInstance MEDIUM pattern=h:mm:ss a\n" +
                        "//   TimeInstance LONG pattern=h:mm:ss a z\n" +
                        "//   TimeInstance FULL pattern=h:mm:ss a zzzz\n" +
                        "\n" +
                        "\n" +
                        "2,1,en-AU,d/M/yy,d MMM y,d MMMM y,EEEE\\, d MMMM y,d/M/yy\\, h:mm a,d/M/yy\\, h:mm:ss a,d/M/yy\\, h:mm:ss a z,d/M/yy\\, h:mm:ss a zzzz,d MMM y\\, h:mm a,d MMM y\\, h:mm:ss a,d MMM y\\, h:mm:ss a z,d MMM y\\, h:mm:ss a zzzz,d MMMM y\\, h:mm a,d MMMM y\\, h:mm:ss a,d MMMM y 'at' h:mm:ss a z,d MMMM y 'at' h:mm:ss a zzzz,EEEE\\, d MMMM y\\, h:mm a,EEEE\\, d MMMM y\\, h:mm:ss a,EEEE\\, d MMMM y 'at' h:mm:ss a z,EEEE\\, d MMMM y 'at' h:mm:ss a zzzz,h:mm a,h:mm:ss a,h:mm:ss a z,h:mm:ss a zzzz,1,en-NZ,d/MM/yy,d/MM/y,d MMMM y,EEEE\\, d MMMM y,d/MM/yy\\, h:mm a,d/MM/yy\\, h:mm:ss a,d/MM/yy\\, h:mm:ss a z,d/MM/yy\\, h:mm:ss a zzzz,d/MM/y\\, h:mm a,d/MM/y\\, h:mm:ss a,d/MM/y\\, h:mm:ss a z,d/MM/y\\, h:mm:ss a zzzz,d MMMM y\\, h:mm a,d MMMM y\\, h:mm:ss a,d MMMM y 'at' h:mm:ss a z,d MMMM y 'at' h:mm:ss a zzzz,EEEE\\, d MMMM y\\, h:mm a,EEEE\\, d MMMM y\\, h:mm:ss a,EEEE\\, d MMMM y 'at' h:mm:ss a z,EEEE\\, d MMMM y 'at' h:mm:ss a zzzz,h:mm a,h:mm:ss a,h:mm:ss a z,h:mm:ss a zzzz");
    }

    @Test
    public void testFRFR() throws IOException {
        this.generateAndCheck("fr-FR",
                "// locales=fr-FR\n" +
                        "// \n" +
                        "//   DateInstance SHORT pattern=dd/MM/y\n" +
                        "//   DateInstance MEDIUM pattern=d MMM y\n" +
                        "//   DateInstance LONG pattern=d MMMM y\n" +
                        "//   DateInstance FULL pattern=EEEE d MMMM y\n" +
                        "//   DateTimeInstance SHORT, SHORT pattern=dd/MM/y HH:mm\n" +
                        "//   DateTimeInstance SHORT, MEDIUM pattern=dd/MM/y HH:mm:ss\n" +
                        "//   DateTimeInstance SHORT, LONG pattern=dd/MM/y HH:mm:ss z\n" +
                        "//   DateTimeInstance SHORT, FULL pattern=dd/MM/y HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance MEDIUM, SHORT pattern=d MMM y HH:mm\n" +
                        "//   DateTimeInstance MEDIUM, MEDIUM pattern=d MMM y 'à' HH:mm:ss\n" +
                        "//   DateTimeInstance MEDIUM, LONG pattern=d MMM y 'à' HH:mm:ss z\n" +
                        "//   DateTimeInstance MEDIUM, FULL pattern=d MMM y 'à' HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance LONG, SHORT pattern=d MMMM y HH:mm\n" +
                        "//   DateTimeInstance LONG, MEDIUM pattern=d MMMM y 'à' HH:mm:ss\n" +
                        "//   DateTimeInstance LONG, LONG pattern=d MMMM y 'à' HH:mm:ss z\n" +
                        "//   DateTimeInstance LONG, FULL pattern=d MMMM y 'à' HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance FULL, SHORT pattern=EEEE d MMMM y HH:mm\n" +
                        "//   DateTimeInstance FULL, MEDIUM pattern=EEEE d MMMM y 'à' HH:mm:ss\n" +
                        "//   DateTimeInstance FULL, LONG pattern=EEEE d MMMM y 'à' HH:mm:ss z\n" +
                        "//   DateTimeInstance FULL, FULL pattern=EEEE d MMMM y 'à' HH:mm:ss zzzz\n" +
                        "//   TimeInstance SHORT pattern=HH:mm\n" +
                        "//   TimeInstance MEDIUM pattern=HH:mm:ss\n" +
                        "//   TimeInstance LONG pattern=HH:mm:ss z\n" +
                        "//   TimeInstance FULL pattern=HH:mm:ss zzzz\n" +
                        "\n" +
                        "\n" +
                        "1,1,fr-FR,dd/MM/y,d MMM y,d MMMM y,EEEE d MMMM y,dd/MM/y HH:mm,dd/MM/y HH:mm:ss,dd/MM/y HH:mm:ss z,dd/MM/y HH:mm:ss zzzz,d MMM y HH:mm,d MMM y 'à' HH:mm:ss,d MMM y 'à' HH:mm:ss z,d MMM y 'à' HH:mm:ss zzzz,d MMMM y HH:mm,d MMMM y 'à' HH:mm:ss,d MMMM y 'à' HH:mm:ss z,d MMMM y 'à' HH:mm:ss zzzz,EEEE d MMMM y HH:mm,EEEE d MMMM y 'à' HH:mm:ss,EEEE d MMMM y 'à' HH:mm:ss z,EEEE d MMMM y 'à' HH:mm:ss zzzz,HH:mm,HH:mm:ss,HH:mm:ss z,HH:mm:ss zzzz");
    }

    @Test
    public void testFRFRFRCA() throws IOException {
        this.generateAndCheck("fr-FR,fr-CA",
                "// locales=fr-CA\n" +
                        "// \n" +
                        "//   DateInstance SHORT pattern=yy-MM-dd\n" +
                        "//   DateInstance MEDIUM pattern=d MMM y\n" +
                        "//   DateInstance LONG pattern=d MMMM y\n" +
                        "//   DateInstance FULL pattern=EEEE d MMMM y\n" +
                        "//   DateTimeInstance SHORT, SHORT pattern=yy-MM-dd HH:mm\n" +
                        "//   DateTimeInstance SHORT, MEDIUM pattern=yy-MM-dd HH:mm:ss\n" +
                        "//   DateTimeInstance SHORT, LONG pattern=yy-MM-dd HH:mm:ss z\n" +
                        "//   DateTimeInstance SHORT, FULL pattern=yy-MM-dd HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance MEDIUM, SHORT pattern=d MMM y HH:mm\n" +
                        "//   DateTimeInstance MEDIUM, MEDIUM pattern=d MMM y HH:mm:ss\n" +
                        "//   DateTimeInstance MEDIUM, LONG pattern=d MMM y HH:mm:ss z\n" +
                        "//   DateTimeInstance MEDIUM, FULL pattern=d MMM y HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance LONG, SHORT pattern=d MMMM y HH:mm\n" +
                        "//   DateTimeInstance LONG, MEDIUM pattern=d MMMM y HH:mm:ss\n" +
                        "//   DateTimeInstance LONG, LONG pattern=d MMMM y 'à' HH:mm:ss z\n" +
                        "//   DateTimeInstance LONG, FULL pattern=d MMMM y 'à' HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance FULL, SHORT pattern=EEEE d MMMM y HH:mm\n" +
                        "//   DateTimeInstance FULL, MEDIUM pattern=EEEE d MMMM y HH:mm:ss\n" +
                        "//   DateTimeInstance FULL, LONG pattern=EEEE d MMMM y 'à' HH:mm:ss z\n" +
                        "//   DateTimeInstance FULL, FULL pattern=EEEE d MMMM y 'à' HH:mm:ss zzzz\n" +
                        "//   TimeInstance SHORT pattern=HH:mm\n" +
                        "//   TimeInstance MEDIUM pattern=HH:mm:ss\n" +
                        "//   TimeInstance LONG pattern=HH:mm:ss z\n" +
                        "//   TimeInstance FULL pattern=HH:mm:ss zzzz\n" +
                        "// locales=fr-FR\n" +
                        "// \n" +
                        "//   DateInstance SHORT pattern=dd/MM/y\n" +
                        "//   DateInstance MEDIUM pattern=d MMM y\n" +
                        "//   DateInstance LONG pattern=d MMMM y\n" +
                        "//   DateInstance FULL pattern=EEEE d MMMM y\n" +
                        "//   DateTimeInstance SHORT, SHORT pattern=dd/MM/y HH:mm\n" +
                        "//   DateTimeInstance SHORT, MEDIUM pattern=dd/MM/y HH:mm:ss\n" +
                        "//   DateTimeInstance SHORT, LONG pattern=dd/MM/y HH:mm:ss z\n" +
                        "//   DateTimeInstance SHORT, FULL pattern=dd/MM/y HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance MEDIUM, SHORT pattern=d MMM y HH:mm\n" +
                        "//   DateTimeInstance MEDIUM, MEDIUM pattern=d MMM y 'à' HH:mm:ss\n" +
                        "//   DateTimeInstance MEDIUM, LONG pattern=d MMM y 'à' HH:mm:ss z\n" +
                        "//   DateTimeInstance MEDIUM, FULL pattern=d MMM y 'à' HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance LONG, SHORT pattern=d MMMM y HH:mm\n" +
                        "//   DateTimeInstance LONG, MEDIUM pattern=d MMMM y 'à' HH:mm:ss\n" +
                        "//   DateTimeInstance LONG, LONG pattern=d MMMM y 'à' HH:mm:ss z\n" +
                        "//   DateTimeInstance LONG, FULL pattern=d MMMM y 'à' HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance FULL, SHORT pattern=EEEE d MMMM y HH:mm\n" +
                        "//   DateTimeInstance FULL, MEDIUM pattern=EEEE d MMMM y 'à' HH:mm:ss\n" +
                        "//   DateTimeInstance FULL, LONG pattern=EEEE d MMMM y 'à' HH:mm:ss z\n" +
                        "//   DateTimeInstance FULL, FULL pattern=EEEE d MMMM y 'à' HH:mm:ss zzzz\n" +
                        "//   TimeInstance SHORT pattern=HH:mm\n" +
                        "//   TimeInstance MEDIUM pattern=HH:mm:ss\n" +
                        "//   TimeInstance LONG pattern=HH:mm:ss z\n" +
                        "//   TimeInstance FULL pattern=HH:mm:ss zzzz\n" +
                        "\n" +
                        "\n" +
                        "2,1,fr-CA,yy-MM-dd,d MMM y,d MMMM y,EEEE d MMMM y,yy-MM-dd HH:mm,yy-MM-dd HH:mm:ss,yy-MM-dd HH:mm:ss z,yy-MM-dd HH:mm:ss zzzz,d MMM y HH:mm,d MMM y HH:mm:ss,d MMM y HH:mm:ss z,d MMM y HH:mm:ss zzzz,d MMMM y HH:mm,d MMMM y HH:mm:ss,d MMMM y 'à' HH:mm:ss z,d MMMM y 'à' HH:mm:ss zzzz,EEEE d MMMM y HH:mm,EEEE d MMMM y HH:mm:ss,EEEE d MMMM y 'à' HH:mm:ss z,EEEE d MMMM y 'à' HH:mm:ss zzzz,HH:mm,HH:mm:ss,HH:mm:ss z,HH:mm:ss zzzz,1,fr-FR,dd/MM/y,d MMM y,d MMMM y,EEEE d MMMM y,dd/MM/y HH:mm,dd/MM/y HH:mm:ss,dd/MM/y HH:mm:ss z,dd/MM/y HH:mm:ss zzzz,d MMM y HH:mm,d MMM y 'à' HH:mm:ss,d MMM y 'à' HH:mm:ss z,d MMM y 'à' HH:mm:ss zzzz,d MMMM y HH:mm,d MMMM y 'à' HH:mm:ss,d MMMM y 'à' HH:mm:ss z,d MMMM y 'à' HH:mm:ss zzzz,EEEE d MMMM y HH:mm,EEEE d MMMM y 'à' HH:mm:ss,EEEE d MMMM y 'à' HH:mm:ss z,EEEE d MMMM y 'à' HH:mm:ss zzzz,HH:mm,HH:mm:ss,HH:mm:ss z,HH:mm:ss zzzz");
    }

    @Test
    public void testENAUFRFR() throws IOException {
        this.generateAndCheck("en-AU,fr-FR",
                "// locales=en-AU\n" +
                        "// \n" +
                        "//   DateInstance SHORT pattern=d/M/yy\n" +
                        "//   DateInstance MEDIUM pattern=d MMM y\n" +
                        "//   DateInstance LONG pattern=d MMMM y\n" +
                        "//   DateInstance FULL pattern=EEEE, d MMMM y\n" +
                        "//   DateTimeInstance SHORT, SHORT pattern=d/M/yy, h:mm a\n" +
                        "//   DateTimeInstance SHORT, MEDIUM pattern=d/M/yy, h:mm:ss a\n" +
                        "//   DateTimeInstance SHORT, LONG pattern=d/M/yy, h:mm:ss a z\n" +
                        "//   DateTimeInstance SHORT, FULL pattern=d/M/yy, h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance MEDIUM, SHORT pattern=d MMM y, h:mm a\n" +
                        "//   DateTimeInstance MEDIUM, MEDIUM pattern=d MMM y, h:mm:ss a\n" +
                        "//   DateTimeInstance MEDIUM, LONG pattern=d MMM y, h:mm:ss a z\n" +
                        "//   DateTimeInstance MEDIUM, FULL pattern=d MMM y, h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance LONG, SHORT pattern=d MMMM y, h:mm a\n" +
                        "//   DateTimeInstance LONG, MEDIUM pattern=d MMMM y, h:mm:ss a\n" +
                        "//   DateTimeInstance LONG, LONG pattern=d MMMM y 'at' h:mm:ss a z\n" +
                        "//   DateTimeInstance LONG, FULL pattern=d MMMM y 'at' h:mm:ss a zzzz\n" +
                        "//   DateTimeInstance FULL, SHORT pattern=EEEE, d MMMM y, h:mm a\n" +
                        "//   DateTimeInstance FULL, MEDIUM pattern=EEEE, d MMMM y, h:mm:ss a\n" +
                        "//   DateTimeInstance FULL, LONG pattern=EEEE, d MMMM y 'at' h:mm:ss a z\n" +
                        "//   DateTimeInstance FULL, FULL pattern=EEEE, d MMMM y 'at' h:mm:ss a zzzz\n" +
                        "//   TimeInstance SHORT pattern=h:mm a\n" +
                        "//   TimeInstance MEDIUM pattern=h:mm:ss a\n" +
                        "//   TimeInstance LONG pattern=h:mm:ss a z\n" +
                        "//   TimeInstance FULL pattern=h:mm:ss a zzzz\n" +
                        "// locales=fr-FR\n" +
                        "// \n" +
                        "//   DateInstance SHORT pattern=dd/MM/y\n" +
                        "//   DateInstance MEDIUM pattern=d MMM y\n" +
                        "//   DateInstance LONG pattern=d MMMM y\n" +
                        "//   DateInstance FULL pattern=EEEE d MMMM y\n" +
                        "//   DateTimeInstance SHORT, SHORT pattern=dd/MM/y HH:mm\n" +
                        "//   DateTimeInstance SHORT, MEDIUM pattern=dd/MM/y HH:mm:ss\n" +
                        "//   DateTimeInstance SHORT, LONG pattern=dd/MM/y HH:mm:ss z\n" +
                        "//   DateTimeInstance SHORT, FULL pattern=dd/MM/y HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance MEDIUM, SHORT pattern=d MMM y HH:mm\n" +
                        "//   DateTimeInstance MEDIUM, MEDIUM pattern=d MMM y 'à' HH:mm:ss\n" +
                        "//   DateTimeInstance MEDIUM, LONG pattern=d MMM y 'à' HH:mm:ss z\n" +
                        "//   DateTimeInstance MEDIUM, FULL pattern=d MMM y 'à' HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance LONG, SHORT pattern=d MMMM y HH:mm\n" +
                        "//   DateTimeInstance LONG, MEDIUM pattern=d MMMM y 'à' HH:mm:ss\n" +
                        "//   DateTimeInstance LONG, LONG pattern=d MMMM y 'à' HH:mm:ss z\n" +
                        "//   DateTimeInstance LONG, FULL pattern=d MMMM y 'à' HH:mm:ss zzzz\n" +
                        "//   DateTimeInstance FULL, SHORT pattern=EEEE d MMMM y HH:mm\n" +
                        "//   DateTimeInstance FULL, MEDIUM pattern=EEEE d MMMM y 'à' HH:mm:ss\n" +
                        "//   DateTimeInstance FULL, LONG pattern=EEEE d MMMM y 'à' HH:mm:ss z\n" +
                        "//   DateTimeInstance FULL, FULL pattern=EEEE d MMMM y 'à' HH:mm:ss zzzz\n" +
                        "//   TimeInstance SHORT pattern=HH:mm\n" +
                        "//   TimeInstance MEDIUM pattern=HH:mm:ss\n" +
                        "//   TimeInstance LONG pattern=HH:mm:ss z\n" +
                        "//   TimeInstance FULL pattern=HH:mm:ss zzzz\n" +
                        "\n" +
                        "\n" +
                        "2,1,en-AU,d/M/yy,d MMM y,d MMMM y,EEEE\\, d MMMM y,d/M/yy\\, h:mm a,d/M/yy\\, h:mm:ss a,d/M/yy\\, h:mm:ss a z,d/M/yy\\, h:mm:ss a zzzz,d MMM y\\, h:mm a,d MMM y\\, h:mm:ss a,d MMM y\\, h:mm:ss a z,d MMM y\\, h:mm:ss a zzzz,d MMMM y\\, h:mm a,d MMMM y\\, h:mm:ss a,d MMMM y 'at' h:mm:ss a z,d MMMM y 'at' h:mm:ss a zzzz,EEEE\\, d MMMM y\\, h:mm a,EEEE\\, d MMMM y\\, h:mm:ss a,EEEE\\, d MMMM y 'at' h:mm:ss a z,EEEE\\, d MMMM y 'at' h:mm:ss a zzzz,h:mm a,h:mm:ss a,h:mm:ss a z,h:mm:ss a zzzz,1,fr-FR,dd/MM/y,d MMM y,d MMMM y,EEEE d MMMM y,dd/MM/y HH:mm,dd/MM/y HH:mm:ss,dd/MM/y HH:mm:ss z,dd/MM/y HH:mm:ss zzzz,d MMM y HH:mm,d MMM y 'à' HH:mm:ss,d MMM y 'à' HH:mm:ss z,d MMM y 'à' HH:mm:ss zzzz,d MMMM y HH:mm,d MMMM y 'à' HH:mm:ss,d MMMM y 'à' HH:mm:ss z,d MMMM y 'à' HH:mm:ss zzzz,EEEE d MMMM y HH:mm,EEEE d MMMM y 'à' HH:mm:ss,EEEE d MMMM y 'à' HH:mm:ss z,EEEE d MMMM y 'à' HH:mm:ss zzzz,HH:mm,HH:mm:ss,HH:mm:ss z,HH:mm:ss zzzz");
    }

    @Test
    public void testGenerateReadVerify() throws IOException {
        final String dataSource = this.generateData("*");
        final DataInput data = StringDataInputDataOutput.input(dataSource);
        final int count = data.readInt();

        for (int i = 0; i < count; i++) {
            final Set<Locale> locales = this.readLocales(data);

            for (int dateStyle : styles()) {
                final String pattern = data.readUTF();
                for (final Locale locale : locales) {
                    assertEquals(DateFormat.getDateInstance(dateStyle, locale),
                            new SimpleDateFormat(pattern, locale),
                            () -> "getDateInstance " + styleToString(dateStyle) + " " + locale);
                }
            }

            for (int dateStyle : styles()) {
                for (int timeStyle : styles()) {
                    final String pattern = data.readUTF();
                    for (final Locale locale : locales) {
                        assertEquals(DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale),
                                new SimpleDateFormat(pattern, locale),
                                () -> "getDateTimeInstance " + styleToString(timeStyle) + " " + styleToString(timeStyle) + " " + locale);
                    }
                }
            }

            for (int timeStyle : styles()) {
                final String pattern = data.readUTF();
                for (final Locale locale : locales) {
                    assertEquals(DateFormat.getTimeInstance(timeStyle, locale),
                            new SimpleDateFormat(pattern, locale),
                            () -> "getTimeInstance " + styleToString(timeStyle) + " " + locale);
                }
            }
        }
    }

    private static int[] styles() {
        return new int[]{DateFormat.SHORT, DateFormat.MEDIUM, DateFormat.LONG, DateFormat.FULL};
    }

    private static void assertEquals(final DateFormat expected,
                                     final SimpleDateFormat actual,
                                     final Supplier<String> message) {
        assertEquals((SimpleDateFormat) expected,
                actual,
                message);
    }

    private static void assertEquals(final SimpleDateFormat expected,
                                     final SimpleDateFormat actual,
                                     final Supplier<String> message) {
        Assertions.assertEquals(expected.toPattern(),
                actual.toPattern(),
                message);
        Assertions.assertEquals(expected.getCalendar().getTimeZone(),
                actual.getCalendar().getTimeZone(),
                () -> message.get() + " calendar timezone");
    }

    private static String styleToString(final int style) {
        final String toString;

        switch (style) {
            case DateFormat.SHORT:
                toString = "SHORT";
                break;
            case DateFormat.MEDIUM:
                toString = "MEDIUM";
                break;
            case DateFormat.LONG:
                toString = "LONG";
                break;
            case DateFormat.FULL:
                toString = "FULL";
                break;
            default:
                NeverError.unhandledCase(style, DateFormat.SHORT, DateFormat.MEDIUM, DateFormat.LONG, DateFormat.FULL);
                toString = null;
        }

        return toString;
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<DateFormatProviderTool> type() {
        return DateFormatProviderTool.class;
    }

    @Override
    void generate0(final Set<String> locales,
                   final DataOutput data,
                   final IndentingPrinter comments) throws IOException {
        DateFormatProviderTool.generate("?",
                LocaleAwareAnnotationProcessorTool.toLocales(locales),
                data,
                comments);
    }
}
