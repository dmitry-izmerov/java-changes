package ru.demi.java6;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * To execute additional annotation processor you should run compiler with 'processor' option:
 * javac -processor <name of .class file> ...
 *
 * To run example manually:
 * javac ./ru/demi/java6/CustomAnnotationProcessor.java
 * javac -processor ru.demi.java6.CustomAnnotationProcessor ./ru/demi/java6/PluggableAnnotationProcessingApiExample.java
 */
@CustomAnnotation
public class PluggableAnnotationProcessingApiExample {

    @CustomAnnotation
    private final String testField;

    @CustomAnnotation
    PluggableAnnotationProcessingApiExample(@CustomAnnotation String param) {
        this.testField = param;
    }

    @CustomAnnotation
    public String getTestField() {
        return testField;
    }
}

@Retention(RetentionPolicy.SOURCE)
@interface CustomAnnotation {
}
