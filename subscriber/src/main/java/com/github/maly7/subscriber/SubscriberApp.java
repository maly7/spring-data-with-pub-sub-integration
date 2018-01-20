package com.github.maly7.subscriber;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SubscriberApp {
    public static void main(String[] args) {
        SpringApplication.run(SubscriberApp.class, args);
    }

    /*
     * Creates an embedded broker. This should be put behind an @Profile for only local development.
     * See http://activemq.apache.org/how-do-i-embed-a-broker-inside-a-connection.html for examples
     */
    @Bean
    public BrokerService brokerService(ActiveMQProperties properties) throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector(properties.getBrokerUrl());
        broker.setPersistent(false);
        broker.start();
        return broker;
    }
}
