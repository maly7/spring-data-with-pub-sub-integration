package com.github.maly7.subscriber.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingConfig {

    @Bean
    public MessageChannel bookIngestChannel() {
        return MessageChannels.direct("bookIngestChannel").get();
    }

    @Bean
    public MessageChannel deleteChannel() {
        return MessageChannels.direct("deleteChannel").get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(500).get();
    }

    @Bean
    public IntegrationFlow inboundBookFlow(ActiveMQConnectionFactory jmsConnectionFactory) {
        return IntegrationFlows.from(
                Jms.inboundAdapter(jmsConnectionFactory).destination("bookUpdates"))
                .channel("bookIngestChannel")
                .get();
    }

    @Bean //TODO: re-enable this once we're using topics
    public HeaderValueRouter ingestRouter() {
        HeaderValueRouter router = new HeaderValueRouter("EventType");
        Map<String, String> channelMappings = new HashMap<>();
        channelMappings.put("UPDATE", "bookIngestChannel");
        channelMappings.put("DELETE", "deleteChannel");

        router.setChannelMappings(channelMappings);
        router.setResolutionRequired(false);
        router.setDefaultOutputChannel(bookIngestChannel());
        return router;
    }

    // For Error Routing See: https://jira.spring.io/browse/INT-4265
}
