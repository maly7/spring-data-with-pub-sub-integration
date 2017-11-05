package com.github.maly7.publisher.config;

import com.github.maly7.publisher.event.BookEvent;
import com.github.maly7.publisher.listener.BookEventListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.messaging.MessageChannel;

@Configuration
public class MessagingConfig {

    @Bean
    public MessageChannel bookEventsChannel() {
        return MessageChannels.direct("bookEventsChannel").get();
    }

    @Bean
    public IntegrationFlow bookEventFlow(ActiveMQConnectionFactory jmsConnectionFactory) {
        return IntegrationFlows.from(bookEventsChannel())
                .handle(Jms.outboundAdapter(jmsConnectionFactory).destination("bookEvents"))
                .get();
    }

    @Bean
    public BookEventListener bookEventListener() {
        BookEventListener listener = new BookEventListener();
        listener.setEventTypes(BookEvent.class);
        listener.setOutputChannel(bookEventsChannel());
        return listener;
    }

}
