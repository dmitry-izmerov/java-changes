package ru.demi.java5.annotations;

public @interface Param {
    String name();
    String description() default "";
}
