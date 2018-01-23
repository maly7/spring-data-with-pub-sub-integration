package com.github.maly7.publisher.listener

import com.github.maly7.publisher.data.BookRepository
import com.github.maly7.publisher.domain.Book
import com.github.maly7.publisher.service.LinksHelper
import com.github.maly7.publisher.support.IntegrationSpec
import com.github.maly7.publisher.support.MessageRecorder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Timeout

import java.util.concurrent.CountDownLatch

@Stepwise
@Timeout(value = 10)
class BookListenerSpec extends IntegrationSpec {

    @Autowired
    BookRepository bookRepository

    @Autowired
    MessageRecorder messageRecorder

    @Autowired
    LinksHelper linksHelper

    @Shared
    Book book

    void setup() {
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(new MockHttpServletRequest())
        RequestContextHolder.setRequestAttributes(requestAttributes)
    }

    void cleanup() {
        messageRecorder.countDownLatch = new CountDownLatch(1)
    }

    void 'The listener should be called when creating a book'() {
        when: 'Creating a Book'
        book = bookRepository.save(new Book(title: 'A Storm of Swords'))
        messageRecorder.countDownLatch.await()

        then: 'The message is received via jms'
        messageRecorder.updates.count { it == linksHelper.selfLinkToBook(book.id).href } >= 1
    }

    void 'The listener should then be called when updating the book'() {
        when: 'Updating a book'
        book.title = 'A Feast for Crows'
        book = bookRepository.save(book)
        messageRecorder.countDownLatch.await()

        then: 'The message is received via jms'
        messageRecorder.updates.count { it == linksHelper.selfLinkToBook(book.id).href } >= 2
    }

    void 'The listener should then be called when deleting the book'() {
        when: 'Deleting a book'
        bookRepository.delete(book.id)
        messageRecorder.countDownLatch.await()

        then: 'The message is received via jms'
        messageRecorder.deletes.count { it == book.id.toString() } == 1
    }
}
