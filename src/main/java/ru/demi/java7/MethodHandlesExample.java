package ru.demi.java7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandlesExample {
    private static final Logger log = LoggerFactory.getLogger(MethodHandlesExample.class);

    private String field;
    private static String staticField = "static value";

    MethodHandlesExample() {}

    public static void main(String[] args) throws Throwable {
        var lookup = MethodHandles.lookup();

        var forConstructor = MethodType.methodType(void.class);
        var constructor = lookup.findConstructor(MethodHandlesExample.class, forConstructor);
        var obj = constructor.invoke();

        var setter = lookup.findSetter(MethodHandlesExample.class, "field", String.class);
        setter.bindTo(obj).invoke("value");

        var getter = lookup.findGetter(MethodHandlesExample.class, "field", String.class);
        log.info("field: {}", getter.bindTo(obj).invoke());

        var forStaticGetter = MethodType.methodType(String.class);
        var staticGetter = lookup.findStatic(MethodHandlesExample.class, "getStaticField", forStaticGetter);
        log.info("static field: {}", staticGetter.invoke());
    }

    public static String getStaticField() {
        return staticField;
    }

    String getField() {
        return field;
    }

    void setField(String field) {
        this.field = field;
    }
}
