package com.github.maly7.publisher.support

import org.apache.activemq.ActiveMQConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.channel.MessageChannels
import org.springframework.integration.dsl.core.Pollers
import org.springframework.integration.dsl.jms.Jms
import org.springframework.integration.scheduling.PollerMetadata
import org.springframework.messaging.MessageChannel

class TestConfig {

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    PollerMetadata poller() {
        return Pollers.fixedRate(500).get();
    }

    @Bean
    IntegrationFlow testInboundUpdateFlow(ActiveMQConnectionFactory jmsConnectionFactory) {
        IntegrationFlows.from(
                Jms.inboundAdapter(jmsConnectionFactory).destination("bookUpdates"))
                .channel(bookIngestChannel())
                .get()
    }

    @Bean
    MessageChannel bookIngestChannel() {
        MessageChannels.direct("bookIngestChannel").get()
    }

    @Bean
    MessageRecorder messageRecorder() {
        new MessageRecorder()
    }
}
