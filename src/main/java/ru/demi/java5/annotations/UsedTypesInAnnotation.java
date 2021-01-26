package ru.demi.java5.annotations;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UsedTypesInAnnotation {
    int primitive() default 0;
    String string();
    Class clazz();
    EnumExample enumUsing();
    Target annotation();
    EnumExample[] arrayOfAboveTypes();
}

enum EnumExample {
    VALUE
}
