package com.github.maly7.publisher;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class PublisherApp {
    private static final Logger LOG = LoggerFactory.getLogger(PublisherApp.class);

    public static void main(String[] args) {
        SpringApplication.run(PublisherApp.class, args);
    }

    @Bean
    @Profile("local")
    public BrokerService brokerService(ActiveMQProperties properties) {
        try {
            LOG.info("Starting ActiveMQ Broker on {}", properties.getBrokerUrl());
            BrokerService broker = new BrokerService();
            broker.addConnector(properties.getBrokerUrl());
            broker.setPersistent(false);
            broker.setUseShutdownHook(false);
            broker.start();
            return broker;
        } catch (Exception e) {
            LOG.info("Looks like something else is already bound to [{}}, we'll assume we can use that.", properties.getBrokerUrl(), e);
            return null;
        }
    }
}
