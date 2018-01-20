package com.github.maly7.publisher.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface BookGateway {

    @Gateway(requestChannel = "bookUpdates")
    String sendUpdate(String message);

    @Gateway(requestChannel = "bookDeletes")
    String sendDelete(String message);
}
