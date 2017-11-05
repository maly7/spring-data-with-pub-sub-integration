package com.github.maly7.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;

@SpringBootApplication
public class SubscriberApp {
    private static final Logger LOG = LoggerFactory.getLogger(SubscriberApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SubscriberApp.class, args);
    }

    @JmsListener(destination = "bookEvents")
    public void receivedJmsMessage(Object book) {
        LOG.info("Received JMS Message: [{}]", book);
    }
}
