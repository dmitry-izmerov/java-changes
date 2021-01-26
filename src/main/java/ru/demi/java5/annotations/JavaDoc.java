package ru.demi.java5.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JavaDoc {
    String description();
    Param[] params() default {};
    String returns() default "";
    String see() default "";
}
