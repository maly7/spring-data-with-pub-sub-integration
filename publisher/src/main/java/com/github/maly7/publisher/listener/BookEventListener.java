package com.github.maly7.publisher.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.integration.event.inbound.ApplicationEventListeningMessageProducer;

import java.util.concurrent.atomic.AtomicInteger;

public class BookEventListener extends ApplicationEventListeningMessageProducer {
    private static final Logger LOG = LoggerFactory.getLogger(BookEventListener.class);
    private static AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        LOG.info("Received Book Event: [{}]", event);
        counter.incrementAndGet();

        super.onApplicationEvent(event);
    }

    public int getCounter() {
        return counter.get();
    }
}
