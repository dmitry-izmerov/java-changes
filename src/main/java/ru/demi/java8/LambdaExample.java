package ru.demi.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LambdaExample {
    private static final Logger log = LoggerFactory.getLogger(LambdaExample.class);

    public static void main(String[] args) throws InterruptedException {
        // before Java 8
        var thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("Hello from {}", Thread.currentThread().getName());
            }
        });
        thread1.start();
        thread1.join();

        // after Java 8
        Runnable runnable = () -> log.info("Hola from {}", Thread.currentThread().getName()); // lambda
        var thread2 = new Thread(runnable);
        thread2.start();
        thread2.join();
    }
}
