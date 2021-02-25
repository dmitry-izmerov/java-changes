package ru.demi.java14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The feature can be toggled with the new boolean command-line option -XX:{+|-}ShowCodeDetailsInExceptionMessages.
 * The option will first have default 'false' so that the message is not printed.
 * It is intended to enable code details in exception messages by default in a later release.
 */
public class HelpfulNPEExample {
    private static final Logger log = LoggerFactory.getLogger(HelpfulNPEExample.class);

    A fieldA = new A(new B());

    public static void main(String[] args) {
        var obj = new HelpfulNPEExample();
        log.info("value: {}", obj.fieldA.fieldB.fieldC.value);
    }

    static class A {
        B fieldB;
        A(B b) {
            fieldB = b;
        }
    }
    static class B {
        C fieldC;
    }
    static class C {
        int value = 1;
    }
}
