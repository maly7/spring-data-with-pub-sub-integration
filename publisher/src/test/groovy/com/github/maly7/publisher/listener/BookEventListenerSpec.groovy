package com.github.maly7.publisher.listener

import com.github.maly7.publisher.data.BookRepository
import com.github.maly7.publisher.domain.Book
import com.github.maly7.publisher.support.IntegrationSpec
import com.github.maly7.publisher.support.MessageRecorder
import org.springframework.beans.factory.annotation.Autowired

class BookEventListenerSpec extends IntegrationSpec {

    @Autowired
    BookRepository bookRepository

    @Autowired
    MessageRecorder messageRecorder
    

    void 'The listener should be called when creating a book'() {
        when: 'Creating a Book'
        Book book = bookRepository.save(new Book(title: 'A Storm of Swords'))

        then: 'The message is received'
        messageRecorder.updates.contains(book.id)
    }
}
