package com.github.maly7.publisher.listener

import com.github.maly7.publisher.data.BookRepository
import com.github.maly7.publisher.domain.Book
import com.github.maly7.publisher.support.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class BookEventListenerSpec extends IntegrationSpec {

    @Autowired
    BookEventListener bookEventListener

    @Autowired
    BookRepository bookRepository

    void 'The listener should be called when creating a book'() {
        when: 'Creating a Book'
        bookRepository.save(new Book(title: 'A Storm of Swords'))
        Thread.sleep(10000)

        then: 'The listener is notified'
        bookEventListener.getCounter().get() > 0
    }
}
