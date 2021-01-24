package ru.demi.java5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericsExample {
    private static final Logger log = LoggerFactory.getLogger(GenericsExample.class);

    public static void main(String[] args) {
        // you cannot use primitive types:
        // FieldWrapper<int> wrapper = new FieldWrapper<int>(1);

        // you can cast to interface/subtype saving the same type parameter
        Wrapper<String> wrapper = new FieldWrapper<String>("string");
        // but cannot do with another value of type parameter even if it is casted within one hierarchy:
        // Wrapper<Number> wrapper = new FieldWrapper<Integer>(5);

        // wildcard with upper bound
        FieldWrapper<Number> fieldWrapperExtends = new FieldWrapper<Number>(5);
        fieldWrapperExtends.set(new FieldWrapper<Integer>(1));

        // wildcard with lower bound
        FieldWrapper<Integer> fieldWrapperSuper = new FieldWrapper<Integer>(5);
        fieldWrapperSuper.copyValueTo(new FieldWrapper<Number>(1));

        // type erasure
        if (wrapper.getClass().equals(fieldWrapperExtends.getClass())) {
            log.info("type is not saved in runtime");
        }
    }

    interface Wrapper<T> {
        T get();
        void set(T value);
    }

    static class FieldWrapper<T> implements Wrapper<T> {
        private T field;

        FieldWrapper(T parameter) {
            field = parameter;
        }

        @Override
        public T get() {
            return field;
        }

        @Override
        public void set(T value) {
            field = value;
        }

        public void set(Wrapper<? extends T> wrapper) {
            field = wrapper.get();
        }

        public void copyValueTo(Wrapper<? super T> wrapper) {
            wrapper.set(field);
        }
    }
}
