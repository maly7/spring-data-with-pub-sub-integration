package com.github.maly7.publisher.listener

import com.github.maly7.publisher.data.BookRepository
import com.github.maly7.publisher.domain.Book
import com.github.maly7.publisher.event.BookEvent
import com.github.maly7.publisher.support.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.test.context.ContextConfiguration
import spock.mock.DetachedMockFactory

@ContextConfiguration(classes = Configuration)
class BookEventListenerSpec extends IntegrationSpec {

    @Autowired
    BookEventListener bookEventListener

    @Autowired
    BookRepository bookRepository

    void 'The listener should be called when creating a book'() {
        when: 'Creating a Book'
        bookRepository.save(new Book(title: 'A Storm of Swords'))

        then: 'The listener is notified'
        1 * bookEventListener.handleBookEvent(_ as BookEvent)
    }

    static class Configuration {
        def mockFactory = new DetachedMockFactory()

        @Bean
        @Primary
        BookEventListener bookEventListener() {
            mockFactory.Spy(BookEventListener)
        }
    }
}
