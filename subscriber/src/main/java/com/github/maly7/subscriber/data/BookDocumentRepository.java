package com.github.maly7.subscriber.data;

import com.github.maly7.subscriber.domain.BookDocument;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDocumentRepository extends SolrCrudRepository<BookDocument, String> {
}
