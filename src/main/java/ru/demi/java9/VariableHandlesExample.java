package ru.demi.java9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class VariableHandlesExample {
    private static final Logger log = LoggerFactory.getLogger(VariableHandlesExample.class);

    private String field;
    private static int[] array = {0, 1};

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        var varHandle = MethodHandles.lookup().in(VariableHandlesExample.class)
            .findVarHandle(VariableHandlesExample.class, "field", String.class);
        var object = new VariableHandlesExample();
        varHandle.setVolatile(object, "value");
        log.info("field: {}", varHandle.getVolatile(object));

        var arrayVarHandle = MethodHandles.arrayElementVarHandle(int[].class);
        arrayVarHandle.set(array, 0, 2);
        log.info("array 1st element: {}", arrayVarHandle.get(array, 0));
    }
}
