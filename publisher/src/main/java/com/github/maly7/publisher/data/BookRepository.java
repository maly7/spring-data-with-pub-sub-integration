package com.github.maly7.publisher.data;

import com.github.maly7.publisher.domain.Book;
import com.github.maly7.publisher.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
    Set<Book> findAllByLabelsContaining(Label label);
}