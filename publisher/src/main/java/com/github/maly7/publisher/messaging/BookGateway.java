package com.github.maly7.publisher.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface BookGateway {

    @Gateway(requestChannel = "bookUpdates")
    void sendUpdate(String message);

    @Gateway(requestChannel = "bookDeletes")
    void sendDelete(String message);
}
