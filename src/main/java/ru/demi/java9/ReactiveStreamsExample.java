package ru.demi.java9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class ReactiveStreamsExample {

    public static void main(String[] args) throws InterruptedException {
        var publisher = new SubmissionPublisher<String>();
        publisher.subscribe(new LogSubscriber());
        List.of("Pfizer", "Sputnik V", "Moderna").forEach(publisher::submit);

        TimeUnit.SECONDS.sleep(1);
        publisher.close();
    }

    static class LogSubscriber implements Flow.Subscriber<String> {
        private static final Logger log = LoggerFactory.getLogger(LogSubscriber.class);
        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            log.info("Got subscription");
            subscription.request(1);
        }

        @Override
        public void onNext(String item) {
            log.info("Got item of: {}", item);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            log.error("Error happened in process of subscription", throwable);
        }

        @Override
        public void onComplete() {
            log.info("Subscription event is processed");
        }
    }
}
