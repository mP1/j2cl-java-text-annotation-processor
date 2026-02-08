[![Build Status](https://github.com/mP1/j2cl-java-text-annotation-processor/actions/workflows/build.yaml/badge.svg)](https://github.com/mP1/j2cl-java-text-annotation-processor/actions/workflows/build.yaml/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/mP1/j2cl-java-text-annotation-processor/badge.svg)](https://coveralls.io/github/mP1/j2cl-java-text-annotation-processor)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/j2cl-java-text-annotation-processor.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-text-annotation-processor/context:java)
![](https://tokei.rs/b1/github/mP1/j2cl-java-text-annotation-processor)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/j2cl-java-text-annotation-processor.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-text-annotation-processor/alerts/)

# j2cl java-text-annotation-processor

Contains several annotation processor that each generate a provider for a JRE class. All processors only generate
for the selected Locales.

- [DateFormatProviderAnnotationProcessor](https://github.com/mP1/j2cl-java-text-annotation-processor/blob/master/src/main/java/walkingkooka/j2cl/java/text/annotationprocessor/DateFormatProviderAnnotationProcessor.java)
  generates a `DateFormatProvider` used by
  [DateFormat](https://github.com/mP1/j2cl-java-text/blob/master/src/main/java/walkingkooka/j2cl/java/text/DateFormat.java). Holds all selected patterns for selected locales.
- [DateFormatSymbolsProviderAnnotationProcessor](https://github.com/mP1/j2cl-java-text-annotation-processor/blob/master/src/main/java/walkingkooka/j2cl/java/text/annotationprocessor/DateFormatSymbolsProviderAnnotationProcessor.java)
  generates a `DateFormatSymbolsProvider` used by
  [DateFormatSymbols](https://github.com/mP1/j2cl-java-text/blob/master/src/main/java/walkingkooka/j2cl/java/text/DateFormatSymbols.java). Holds all selected symbol data.
- [DecimalFormatProviderAnnotationProcessor](https://github.com/mP1/j2cl-java-text-annotation-processor/blob/master/src/main/java/walkingkooka/j2cl/java/text/annotationprocessor/DecimalFormatProviderAnnotationProcessor.java)
  generates a `DeciamlFormatProvider` used by
  [DecimalFormat](https://github.com/mP1/j2cl-java-text/blob/master/src/main/java/walkingkooka/j2cl/java/text/DecimalFormat.java). Holds patterns and other format data like decimal separator etc. 
- [DecimalFormatSymbolsProviderAnnotationProcessor](https://github.com/mP1/j2cl-java-text-annotation-processor/blob/master/src/main/java/walkingkooka/j2cl/java/text/annotationprocessor/DecimalFormatSymbolsProviderAnnotationProcessor.java)
  generates a `DecimalFormatSymbolsProvider` used by
  [DecimalFormatSymbols](https://github.com/mP1/j2cl-java-text/blob/master/src/main/java/walkingkooka/j2cl/java/text/DecimalFormatSymbols.java). Holds all selected format patterns. 

To select which locales are included set the `walkingkooka.j2cl.java.util.Locale` annotation processor argument.

```xml
-Awalkingkooka.j2cl.java.util.Locale=EN*
```

This selects all locales starting with `EN`.

For more details [click here](https://github.com/mP1/j2cl-locale)



