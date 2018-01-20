package com.github.maly7.queue;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmsQueueRunner {
    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61617");
        broker.setPersistent(false);
        broker.start();
        SpringApplication.run(JmsQueueRunner.class, args);
    }
}
