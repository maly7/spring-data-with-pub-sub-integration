package com.github.maly7.publisher.support

import org.springframework.jms.annotation.JmsListener

class MessageRecorder {
    List<String> updates = []

    @JmsListener(destination = 'bookUpdates', subscription = 'bookUpdates')
    void recordUpdate(String bookId) {
        println "Received Message for book with id ${bookId}"
        updates << bookId
    }
}
