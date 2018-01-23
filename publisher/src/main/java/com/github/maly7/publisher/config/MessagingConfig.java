package com.github.maly7.publisher.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageChannel;

@Configuration
public class MessagingConfig {

    @Bean
    public MessageChannel bookUpdates() {
        return MessageChannels.direct("bookUpdates").get();
    }

    @Bean
    public MessageChannel bookDeletes() {
        return MessageChannels.direct("bookDeletes").get();
    }

    @Bean
    public IntegrationFlow bookUpdatesFlow(ActiveMQConnectionFactory jmsConnectionFactory) {
        return IntegrationFlows.from(bookUpdates())
                .log(LoggingHandler.Level.INFO)
                .handle(Jms.outboundAdapter(jmsConnectionFactory)
                        .destination("bookUpdates"))
                .get();
    }

    @Bean
    public IntegrationFlow bookDeletesFlow(ActiveMQConnectionFactory jmsConnectionFactory) {
        return IntegrationFlows.from(bookDeletes())
                .log(LoggingHandler.Level.INFO)
                .handle(Jms.outboundAdapter(jmsConnectionFactory)
                        .destination("bookDeletes"))
                .get();
    }
}
