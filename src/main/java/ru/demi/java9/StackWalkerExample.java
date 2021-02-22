package ru.demi.java9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.stream.Collectors;

public class StackWalkerExample {
    private static final Logger log = LoggerFactory.getLogger(StackWalkerExample.class);

    private StackWalker stackWalker = StackWalker.getInstance();

    public static void main(String[] args) {
        var stackWalkerExample = new StackWalkerExample();
        var c = new C(stackWalkerExample);
        var b = new B(c);
        var a = new A(b);
        a.methodA();
    }

    void logMethodChain() {
        var res = stackWalker.walk(s -> {
            var list = s.collect(Collectors.toList());
            Collections.reverse(list);
            return list.stream()
                .map(stackFrame -> String.format("%s::%s", stackFrame.getClassName(), stackFrame.getMethodName()))
                .collect(Collectors.joining(" -> "));
        });
        log.info("Reversed Stacktrace: {}", res);
    }

    static class A {
        private B b;
        A(B b) {
            this.b = b;
        }
        void methodA() {
            b.methodB();
        }
    }
    static class B {
        private C c;
        B(C c) {
            this.c = c;
        }
        void methodB() {
            c.methodC();
        }
    }
    static class C {
        private StackWalkerExample stackWalkerExample;

        C(StackWalkerExample stackWalkerExample) {
            this.stackWalkerExample = stackWalkerExample;
        }

        void methodC() {
            stackWalkerExample.logMethodChain();
        }
    }
}
