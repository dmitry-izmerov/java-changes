package ru.demi.java8;

import java.lang.annotation.Repeatable;

public class RepeatingAnnotationsExample {

    @RepeatableAnnotation
    @RepeatableAnnotation
    @RepeatableAnnotation
    void applyingOfRepeatableAnnotation() {}

    @Repeatable(ContainerAnnotation.class)
    @interface RepeatableAnnotation {}

    @interface ContainerAnnotation {
        RepeatableAnnotation[] value();
    }
}
