package ru.demi.java9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrivateInInterfacesExample implements InterfaceWithPrivateMethod {
    private static final Logger log = LoggerFactory.getLogger(PrivateInInterfacesExample.class);

    public static void main(String[] args) {
        var obj = new PrivateInInterfacesExample();
        obj.defaultMethod1(log);
    }
}

interface InterfaceWithPrivateMethod {
    default void defaultMethod1(Logger logger) {
        privateMethodInInterfaces(logger);
    }

    default void defaultMethod2(Logger logger) {
        privateMethodInInterfaces(logger);
    }

    private void privateMethodInInterfaces(Logger logger) {
        logger.info("hello from private method");
    }
}
