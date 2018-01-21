package com.github.maly7.publisher.support

import org.springframework.jms.annotation.JmsListener

class MessageRecorder {
    List<String> updates = []

    @JmsListener(destination = 'bookUpdates')
    void recordUpdate(String bookId) {
        updates << bookId
    }
}
