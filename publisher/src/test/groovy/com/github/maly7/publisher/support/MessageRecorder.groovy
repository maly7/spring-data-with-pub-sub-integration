package com.github.maly7.publisher.support

import org.springframework.integration.annotation.ServiceActivator

class MessageRecorder {
    List<String> updates = []

    @ServiceActivator(inputChannel = "bookIngestChannel")
    boolean recordUpdate(String bookId) {
        updates << bookId
        true
    }
}
