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
import walkingkooka.collect.list.Lists;
import walkingkooka.collect.map.Maps;
import walkingkooka.collect.set.SortedSets;
import walkingkooka.j2cl.java.io.string.StringDataInputDataOutput;
import walkingkooka.j2cl.java.util.locale.support.LocaleSupport;
import walkingkooka.j2cl.locale.WalkingkookaLanguageTag;
import walkingkooka.j2cl.locale.annotationprocessor.LocaleAwareAnnotationProcessor;
import walkingkooka.j2cl.locale.annotationprocessor.LocaleAwareAnnotationProcessorTool;
import walkingkooka.text.CharSequences;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.Printer;
import walkingkooka.text.printer.Printers;

import java.io.DataOutput;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This tool prints a generated method to a {@link String} for inclusion in <code>DecimalFormatProvider.java.txt</code>
 */
public final class DecimalFormatProviderTool {

    public static void main(final String[] args) throws IOException {
        try (final Printer printer = Printers.sysOut()) {
            final StringBuilder data = new StringBuilder();
            generate("*",
                    WalkingkookaLanguageTag.locales("*"),
                    StringDataInputDataOutput.output(data::append),
                    LocaleAwareAnnotationProcessor.comments(printer));
            printer.print(CharSequences.quoteAndEscape(data));
            printer.flush();
        }
    }

    static String generate(final String filter,
                           final Set<Locale> locales,
                           final DataOutput data,
                           final IndentingPrinter comments) throws IOException {
        new DecimalFormatProviderTool(data, comments).generate0(locales);
        return LocaleAwareAnnotationProcessorTool.extractSummary(locales.size(),
                "Locale",
                filter);
    }

    private DecimalFormatProviderTool(final DataOutput data,
                                      final IndentingPrinter comments) {
        super();
        this.data = data;
        this.comments = comments;
    }

    private void generate0(final Set<Locale> locales) throws IOException {
        final Map<List<DecimalFormat>, Set<Locale>> formatsToLocales = Maps.sorted(DecimalFormatProviderTool::comparator);

        for (final Locale locale : locales) {
            final DecimalFormat currency = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
            final DecimalFormat instance = (DecimalFormat) NumberFormat.getInstance(locale);
            final DecimalFormat integer = (DecimalFormat) NumberFormat.getIntegerInstance(locale);
            final DecimalFormat number = (DecimalFormat) NumberFormat.getNumberInstance(locale);
            final DecimalFormat percent = (DecimalFormat) NumberFormat.getPercentInstance(locale);

            final List<DecimalFormat> formats = Lists.of(currency, instance, integer, number, percent);

            Set<Locale> formatLocales = formatsToLocales.get(formats);
            if (null == formatLocales) {
                formatLocales = SortedSets.tree(LocaleAwareAnnotationProcessorTool.LOCALE_COMPARATOR);
                formatsToLocales.put(formats, formatLocales);
            }
            formatLocales.add(locale);
        }

        final Map<Locale, List<DecimalFormat>> localeToFormats = Maps.sorted(LocaleAwareAnnotationProcessorTool.LOCALE_COMPARATOR);
        for (final Entry<List<DecimalFormat>, Set<Locale>> formatAndLocale : formatsToLocales.entrySet()) {
            localeToFormats.put(formatAndLocale.getValue().iterator().next(), formatAndLocale.getKey());
        }

        final DataOutput data = this.data;
        final IndentingPrinter comments = this.comments;
        data.writeInt(localeToFormats.size());

        for (final List<DecimalFormat> formats : localeToFormats.values()) {
            LocaleSupport.generateLocales(formatsToLocales.get(formats),
                    data,
                    comments);
            comments.indent();
            {
                comments.lineStart();
                comments.print(comments.lineEnding());

                generateDecimalFormat(formats.get(0), "Currency");
                generateDecimalFormat(formats.get(1), "Instance");
                generateDecimalFormat(formats.get(2), "Integer");
                generateDecimalFormat(formats.get(3), "Number");
                generateDecimalFormat(formats.get(4), "Percent");
            }
            comments.outdent();
        }
    }

    private static int comparator(final List<DecimalFormat> left,
                                  final List<DecimalFormat> right) {
        return toString(left).compareTo(toString(right));
    }

    private static String toString(final List<DecimalFormat> format) {
        return format.stream()
                .map(DecimalFormatProviderTool::toString)
                .collect(Collectors.joining("\n"));
    }

    private static String toString(final DecimalFormat format) {
        return ToStringBuilder.empty()
                .label("decimalSeparatorAlwaysShown").value(format.isDecimalSeparatorAlwaysShown())
                .label("groupingSize").value(format.getGroupingSize())
                .label("groupingUsed").value(format.isGroupingUsed())
                .label("maximumFractionDigits").value(format.getMaximumFractionDigits())
                .label("minimumFractionDigits").value(format.getMinimumFractionDigits())
                .label("maximumIntegerDigits").value(format.getMaximumIntegerDigits())
                .label("minimumIntegerDigits").value(format.getMinimumIntegerDigits())
                .label("multiplier").value(format.getMultiplier())
                .label("negativePrefix").value(format.getNegativePrefix())
                .label("negativeSuffix").value(format.getNegativeSuffix())
                .label("parseBigDecimal").value(format.isParseBigDecimal())
                .label("parseIntegerOnly").value(format.isParseIntegerOnly())
                .label("pattern").value(format.toPattern())
                .label("positivePrefix").value(format.getPositivePrefix())
                .label("positiveSuffix").value(format.getPositiveSuffix())
                .label("roundingMode").value(format.getRoundingMode())
                .build();
    }

    private void generateDecimalFormat(final DecimalFormat format,
                                       final String type) throws IOException {
        int parse = PARSE_NONE;
        if (format.isParseIntegerOnly()) {
            parse |= PARSE_INTEGER_ONLY;
        }
        if (format.isParseBigDecimal()) {
            parse |= PARSE_BIG_DECIMAL;
        }

        this.field(format.isDecimalSeparatorAlwaysShown(), type, "decimalSeparatorAlwaysShown");
        this.field(format.getGroupingSize(), type, "groupingSize");
        this.field(format.isGroupingUsed(), type, "groupingUsed");
        this.field(format.getMaximumFractionDigits(), type, "maximumFractionDigits");
        this.field(format.getMinimumFractionDigits(), type, "minimumFractionDigits");
        this.field(format.getMaximumIntegerDigits(), type, "maximumIntegerDigits");
        this.field(format.getMinimumIntegerDigits(), type, "minimumIntegerDigits");
        this.field(format.getMultiplier(), type, "multiplier");
        this.field(format.getNegativePrefix(), type, "negativePrefix");
        this.field(format.getNegativeSuffix(), type, "negativeSuffix");
        this.field(parse, type, "parse");
        this.field(format.toPattern(), type, "pattern");
        this.field(format.getPositivePrefix(), type, "positivePrefix");
        this.field(format.getPositiveSuffix(), type, "positiveSuffix");
        this.field(format.getRoundingMode().name(), type, "roundingMode");

        this.comments.lineStart();
        this.comments.print(this.comments.lineEnding());
    }

    final static int PARSE_NONE = 0;
    final static int PARSE_INTEGER_ONLY = 1;
    final static int PARSE_BIG_DECIMAL = 2;

    private void field(final boolean value, final String type, final String property) throws IOException {
        this.comments.lineStart();
        this.comments.print(type + " " + property + "=" + value);

        this.data.writeBoolean(value);
    }

    private void field(final int value, final String type, final String property) throws IOException {
        this.comments.lineStart();
        this.comments.print(type + " " + property + "=" + value);

        this.data.writeInt(value);
    }

    private void field(final String value, final String type, final String property) throws IOException {
        this.comments.lineStart();
        this.comments.print(type + " " + property + "=" + value);

        this.data.writeUTF(value);
    }

    private final DataOutput data;
    private final IndentingPrinter comments;
}
