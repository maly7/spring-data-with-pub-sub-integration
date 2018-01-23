package com.github.maly7.publisher.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface BookGateway {

    /*
     * TODO: use a default request channel for the entire class, but specific header values for
     * the various methods. ie:
     * @MessagingGateway(defaultRequestChannel = "books")
     *
     * with the following on the methods:
     *
     * @Gateway(headers = @GatewayHeader(name = "EventType", value = "write"))
     *
     * @Gateway(headers = @GatewayHeader(name = "EventType", value = "delete"))
     */

    @Gateway(requestChannel = "bookUpdates", payloadExpression = "@linksHelper.selfLinkToBook(#args[0]).href")
    void sendUpdate(String bookId);

    @Gateway(requestChannel = "bookDeletes", payloadExpression = "@linksHelper.selfLinkToBook(#args[0]).href")
    void sendDelete(String bookId);
}
