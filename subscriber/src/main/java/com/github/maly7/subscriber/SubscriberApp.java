package com.github.maly7.subscriber;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SubscriberApp {
    private static final Logger LOG = LoggerFactory.getLogger(SubscriberApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SubscriberApp.class, args);
    }

    /*
     * Creates an embedded broker. This should be put behind an @Profile for only local development.
     * See http://activemq.apache.org/how-do-i-embed-a-broker-inside-a-connection.html for examples
     */
    @Bean
    public BrokerService brokerService(ActiveMQProperties properties) {
        try {
            BrokerService broker = new BrokerService();
            broker.addConnector(properties.getBrokerUrl());
            broker.setPersistent(false);
            broker.start();
            return broker;
        } catch (Exception e) {
            LOG.debug("Looks like something else is already bound to [{}}, we'll assume we can use that.", properties.getBrokerUrl(), e);
            return null;
        }
    }
}
