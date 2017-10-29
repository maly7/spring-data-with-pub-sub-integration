package com.github.maly7.publisher.listener;

import com.github.maly7.publisher.event.BookEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

public class BookEventListener {
    private static final Logger LOG = LoggerFactory.getLogger(BookEventListener.class);

    @EventListener
    public BookEvent handleBookEvent(BookEvent bookEvent) {
        LOG.info("Received Book Event: [{}]", bookEvent);
        return bookEvent;
    }
}
