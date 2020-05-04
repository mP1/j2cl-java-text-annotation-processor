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
import walkingkooka.collect.set.Sets;
import walkingkooka.j2cl.locale.WalkingkookaLanguageTag;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.Printer;
import walkingkooka.text.printer.Printers;

import java.math.RoundingMode;
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
public final class DecimalFormatProviderTool extends LocaleProviderTool {

    public static void main(final String[] args) {
        final IndentingPrinter printer = Printers.sysOut().indenting(Indentation.with("  "));
        new DecimalFormatProviderTool(printer).print(WalkingkookaLanguageTag.all("EN-AU"));
        printer.flush();
    }

    static String generateMethod(final Set<String> languageTags) {
        final StringBuilder output = new StringBuilder();
        try (final Printer printer = Printers.stringBuilder(output, LineEnding.SYSTEM)) {
            new DecimalFormatProviderTool(printer.indenting(Indentation.with("  "))).print(languageTags);
        }

        return output.toString();
    }

    private DecimalFormatProviderTool(final IndentingPrinter printer) {
        super(printer);
    }

    @Override
    void print0(final Set<String> languageTags) {
        final Map<List<DecimalFormat>, Set<String>> formatsToLanguageTags = Maps.sorted(DecimalFormatProviderTool::comparator);

        for (final String languageTag : languageTags) {
            final Locale locale = Locale.forLanguageTag(languageTag);

            final DecimalFormat currency = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
            final DecimalFormat instance = (DecimalFormat) NumberFormat.getInstance(locale);
            final DecimalFormat integer = (DecimalFormat) NumberFormat.getIntegerInstance(locale);
            final DecimalFormat number = (DecimalFormat) NumberFormat.getNumberInstance(locale);
            final DecimalFormat percent = (DecimalFormat) NumberFormat.getPercentInstance(locale);

            final List<DecimalFormat> formats = Lists.of(currency, instance, integer, number, percent);

            Set<String> formatLocales = formatsToLanguageTags.get(formats);
            if (null == formatLocales) {
                formatLocales = Sets.sorted();
                formatsToLanguageTags.put(formats, formatLocales);
            }
            formatLocales.add(languageTag);
        }

        final Map<String, List<DecimalFormat>> languageTagToFormats = Maps.sorted();
        for(final Entry<List<DecimalFormat>, Set<String>> formatAndlanguageTags : formatsToLanguageTags.entrySet()) {
            languageTagToFormats.put(formatAndlanguageTags.getValue().iterator().next(), formatAndlanguageTags.getKey());
        }

        this.line("static void register(final java.util.function.Consumer<DecimalFormatProvider> registry) {");
        this.indent();
        {
            for (final List<DecimalFormat> formats : languageTagToFormats.values()) {
                final Set<String> formatLanguageTags = formatsToLanguageTags.get(formats);

                this.line("registry.accept( new DecimalFormatProvider(");
                this.indent();
                {
                    this.line(tabbed(formatLanguageTags) + ", // locales");

                    this.line(formats.get(0), "Currency", false);
                    this.line(formats.get(1), "Instance", false);
                    this.line(formats.get(2), "Integer", false);
                    this.line(formats.get(3), "Number", false);
                    this.line(formats.get(4), "Percent", true);
                }
                this.outdent();

                this.line("));");

                this.emptyLine();
            }
        }
        this.outdent();
        this.line("}");
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

    private void line(final DecimalFormat format,
                      final String type,
                      final boolean last) {
        int parse = 0;
        if (format.isParseIntegerOnly()) {
            parse |= PARSE_INTEGER_ONLY;
        }
        if (format.isParseBigDecimal()) {
            parse |= PARSE_BIG_DECIMAL;
        }

        this.line(format.isDecimalSeparatorAlwaysShown(), type, "decimalSeparatorAlwaysShown");
        this.line(format.getGroupingSize(), type, "groupingSize");
        this.line(format.isGroupingUsed(), type, "groupingUsed");
        this.line(format.getMaximumFractionDigits(), type, "maximumFractionDigits");
        this.line(format.getMinimumFractionDigits(), type, "minimumFractionDigits");
        this.line(format.getMaximumIntegerDigits(), type, "maximumIntegerDigits");
        this.line(format.getMinimumIntegerDigits(), type, "minimumIntegerDigits");
        this.line(format.getMultiplier(), type, "multiplier");
        this.line(format.getNegativePrefix(), type, "negativePrefix");
        this.line(format.getNegativeSuffix(), type, "negativeSuffix");
        this.line(parse, type, "parse");
        this.line(format.toPattern(), type, "pattern");
        this.line(format.getPositivePrefix(), type, "positivePrefix");
        this.line(format.getPositiveSuffix(), type, "positiveSuffix");

        this.line(type(RoundingMode.class) + "." + format.getRoundingMode().name() + (last ? "": ",") + " // " + type + " roundingMode"); // dont want the trailing comma
    }

    final static int PARSE_NONE = 0;
    final static int PARSE_INTEGER_ONLY = 1;
    final static int PARSE_BIG_DECIMAL = 2;

    private void line(final boolean value, final String type, final String property) {
        this.line(value + ", // " + type + " " + property);
    }

    private void line(final int value, final String type, final String property) {
        this.line(value + ", // " + type + " " + property);
    }

    private void line(final String value, final String type, final String property) {
        this.line(quote(value) + ", // " + type + " " + property);
    }
}
