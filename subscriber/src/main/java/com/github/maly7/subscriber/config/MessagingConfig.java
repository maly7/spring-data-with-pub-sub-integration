package com.github.maly7.subscriber.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;

@Configuration
public class MessagingConfig {

    @Bean
    public MessageChannel bookIngestChannel() {
        return MessageChannels.direct("bookIngestChannel").get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(500).get();
    }

    @Bean
    public IntegrationFlow inboundBookFlow(ActiveMQConnectionFactory jmsConnectionFactory) {
        return IntegrationFlows.from(
                Jms.inboundAdapter(jmsConnectionFactory).destination("bookEvents"))
                .channel(bookIngestChannel())
                .get();
    }
}
