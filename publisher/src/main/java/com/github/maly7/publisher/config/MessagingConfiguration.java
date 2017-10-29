package com.github.maly7.publisher.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

//@Configuration
public class MessagingConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(MessagingConfiguration.class);

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(500).get();
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public IntegrationFlow jmsInboundFlow(ActiveMQConnectionFactory jmsConnectionFactory) {
        return IntegrationFlows
                .from(Jms.inboundAdapter(jmsConnectionFactory)
                        .configureJmsTemplate(t ->
                                t.deliveryPersistent(true).jmsMessageConverter(jacksonJmsMessageConverter()))
                        .destination("jmsInbound"))
                .transform(source -> {
                    LOG.info("Received Message [{}]", source);
                    return source;
                })
                .get();
    }

    @Bean
    public IntegrationFlow jmsOutboundGatewayFlow(ActiveMQConnectionFactory jmsConnectionFactory) {
        return IntegrationFlows.from("jmsOutbound")
                .handle(Jms.outboundGateway(jmsConnectionFactory)
                        .replyContainer(c -> c.concurrentConsumers(3).sessionTransacted(true)).requestDestination("jmsPipelineTest"))
                .get();
    }
}
