package ru.demi.java15;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Modifier;

public class HiddenClassesExample {

    public static void main(String[] args) throws Throwable {
        var lookup = MethodHandles.lookup();
        var bytecode = generateTestClass();
        var clazz = lookup.defineHiddenClass(bytecode, true, MethodHandles.Lookup.ClassOption.NESTMATE).lookupClass();
        var constructor = lookup.findConstructor(clazz, MethodType.methodType(void.class));
        var obj = (Test) constructor.invoke();
        obj.print("Hola Mundo");
    }

    public interface Test {
        void print(String value);
    }

    private static byte[] generateTestClass() throws NoSuchMethodException {
        var unloadedType = new ByteBuddy()
            .subclass(Object.class)
            .name("ru.demi.java15.TestImpl")
            .implement(Test.class)
            .defineMethod("print", void.class, Modifier.PUBLIC).withParameters(String.class)
            .intercept(MethodDelegation.to(Delegate.class))
            .make();
        return unloadedType.getBytes();
    }

    static class Delegate {
        private static final Logger log = LoggerFactory.getLogger(Delegate.class);

        public static void print(String value) {
            log.info("got value: {}", value);
        }
    }
}
