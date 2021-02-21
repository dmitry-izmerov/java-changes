package ru.demi.java9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MoreConcurrencyUpdates {
    private static final Logger log = LoggerFactory.getLogger(MoreConcurrencyUpdates.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // CompletableFuture
        var complFuture = new CompletableFuture<String>();
        complFuture.completeAsync(() -> "value", CompletableFuture.delayedExecutor(1, TimeUnit.MILLISECONDS))
            .orTimeout(1, TimeUnit.SECONDS)
            .completeOnTimeout("complete", 1, TimeUnit.SECONDS);
        log.info("completable future result: {}", complFuture.get());

        try {
            CompletableFuture.failedFuture(new RuntimeException("хана"))
                .get();
        } catch (Exception e) {
            log.error("error", e.getCause());
        }

        // atomic package
        var atomicBoolean = new AtomicBoolean(false);
        atomicBoolean.compareAndExchange(false, true);
        log.info("atomic boolean exchanged value: {}", atomicBoolean.getPlain());
        var atomicInt = new AtomicInteger(1);
        atomicInt.weakCompareAndSetVolatile(1, 2);
        log.info("atomic int set value: {}", atomicInt.getAcquire());
    }
}
