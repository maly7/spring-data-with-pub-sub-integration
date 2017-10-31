package com.github.maly7.publisher.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.maly7.publisher.event.BookEvent;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BookEventListener {
    private static final Logger LOG = LoggerFactory.getLogger(BookEventListener.class);

    private final JmsTemplate jmsTemplate;
    private final ActiveMQConnectionFactory activeMQConnectionFactory;
    private final ObjectMapper objectMapper;

    private static AtomicInteger counter;

    @Autowired
    public BookEventListener(JmsTemplate jmsTemplate, ActiveMQConnectionFactory jmsConnectionFactory, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.activeMQConnectionFactory = jmsConnectionFactory;
        this.objectMapper = objectMapper;

        counter = new AtomicInteger(0);
    }

    @EventListener
    public void handleBookEvent(BookEvent bookEvent) {
        LOG.info("Received Book Event: [{}]", bookEvent);
        jmsTemplate.convertAndSend("bookEvents", objectMapper.convertValue(bookEvent.getSource(), Map.class));
    }

    @JmsListener(destination = "bookEvents")
    public void receivedJmsMessage(Object book) {
        LOG.info("Received JMS Message: [{}]", book);
        counter.incrementAndGet();
    }

    public static AtomicInteger getCounter() {
        return counter;
    }
}
