package ru.demi.java5.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationProcessor {

    private static final Logger log = LoggerFactory.getLogger(AnnotationProcessor.class);

    public void process(AnnotatedElement element) {
        List<AnnotatedElement> elements = new ArrayList<>();
        elements.add(element);
        if (element instanceof Class) {
            var clazz = (Class<?>) element;
            elements.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        }

        for (var e : elements) {
            if (e.isAnnotationPresent(JavaDoc.class)) {
                var annotation = e.getAnnotation(JavaDoc.class);
                log.info("Javadoc for element: {}", e.toString());
                log.info("description: {}", annotation.description());
                log.info("params:");
                Arrays.stream(annotation.params()).forEach(p -> log.info("\t{} = {}", p.name(), p.description()));
                log.info("returns: {}", annotation.returns());
                log.info("see: {}", annotation.see());
            }
        }
    }
}
