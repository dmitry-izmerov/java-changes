package ru.demi.java5.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AnnotationsExample {

    private static final Logger log = LoggerFactory.getLogger(AnnotationsExample.class);

    public static void main(String[] args) {
        var some = new SomeImpl();
        some.apply("value");

        var annotationProcessor = new AnnotationProcessor();
        annotationProcessor.process(some.getClass());
    }

    interface BaseInterface {
        void apply(String param);
    }

    @JavaDoc(description = "Implementation of BaseInterface")
    static class SomeImpl implements BaseInterface {

        @JavaDoc(description = "Apply method", params = {@Param(name = "param")}, returns = "void")
        @Override
        public void apply(String param) {
            log.info("do something");
        }

        @Deprecated(since = "2.0", forRemoval = true)
        void oldMethod() {
            log.info("method is obsolete and should not be used");
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        void getUncheckedWarningForRawType() {
            List list = new ArrayList();
            list.add("something");
        }
    }
}
