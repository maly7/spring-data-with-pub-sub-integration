package com.github.maly7.publisher.support

import org.springframework.jms.annotation.JmsListener

import java.util.concurrent.CountDownLatch

class MessageRecorder {
    List<String> updates = []

    CountDownLatch countDownLatch = new CountDownLatch(1)

    @JmsListener(destination = 'bookUpdates', subscription = "bookUpdates")
    void recordUpdate(String bookId) {
        println "Received Message for book with id ${bookId}"
        countDownLatch.countDown()
        updates << bookId
    }
}
