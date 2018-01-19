package com.github.maly7.subscriber.data

import com.github.maly7.subscriber.domain.BookDocument
import com.github.maly7.subscriber.support.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.solr.core.SolrTemplate

class SolrDocumentSavingSpec extends IntegrationSpec {

    @Autowired
    BookDocumentRepository bookDocumentRepository

    @Autowired
    SolrTemplate solrTemplate

    void 'A Book should be persisted'() {
        when:
        String bookId = UUID.randomUUID().toString()
        BookDocument book = new BookDocument()
        book.id = bookId
        book.title = 'oathbringer'

        bookDocumentRepository.save(doc)

        then:
        bookDocumentRepository.findOne(bookId)
//
//        cleanup:
//        bookDocumentRepository.delete(bookId)
    }
}
