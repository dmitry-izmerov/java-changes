package ru.demi.java9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SpinWaitHintsExample {
    private static final Logger log = LoggerFactory.getLogger(SpinWaitHintsExample.class);

    private static volatile boolean isNotified = false;

    public static void main(String[] args) throws InterruptedException {

        var thread = new Thread(() -> {
            // spin-wait loop
            while (!isNotified) {
                Thread.onSpinWait();
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                log.error("error", e);
            }
        });
        thread.start();
        log.info("thread with loop started");

        // imitation of working in another thread
        TimeUnit.SECONDS.sleep(1);
        isNotified = true;
        log.info("main thread notified child thread");
        thread.join();
    }
}
