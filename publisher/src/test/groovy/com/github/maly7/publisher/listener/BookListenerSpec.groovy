package com.github.maly7.publisher.listener

import com.github.maly7.publisher.data.BookRepository
import com.github.maly7.publisher.domain.Book
import com.github.maly7.publisher.support.IntegrationSpec
import com.github.maly7.publisher.support.MessageRecorder
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared
import spock.lang.Stepwise

import java.util.concurrent.CountDownLatch

@Stepwise
class BookListenerSpec extends IntegrationSpec {

    @Autowired
    BookRepository bookRepository

    @Autowired
    MessageRecorder messageRecorder

    @Shared
    Book book

    void cleanup() {
        messageRecorder.countDownLatch = new CountDownLatch(1)
    }

    void 'The listener should be called when creating a book'() {
        when: 'Creating a Book'
        book = bookRepository.save(new Book(title: 'A Storm of Swords'))
        messageRecorder.countDownLatch.await()

        then: 'The message is received via jms'
        messageRecorder.updates.contains(book.id.toString())
    }

    void 'The listener should then be called when updating the book'() {
        when: 'Updating a book'
        book.title = 'A Feast for Crows'
        book = bookRepository.save(book)
        messageRecorder.countDownLatch.await()

        then: 'The message is received via jms'
        messageRecorder.updates.count { it == book.id.toString() } >= 2
    }
}
