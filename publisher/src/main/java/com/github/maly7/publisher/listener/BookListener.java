package com.github.maly7.publisher.listener;

import com.github.maly7.publisher.domain.Book;
import com.github.maly7.publisher.messaging.BookGateway;
import com.github.maly7.publisher.service.BeanFetcher;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

public class BookListener {

    @PostUpdate
    @PostPersist
    public void sendUpdateMessage(Book book) {
        assertActiveTransaction();
        BeanFetcher.getBean(BookGateway.class).sendUpdate(String.valueOf(book.getId()));
    }

    @PostRemove
    public void sendDeleteMessage(Book book) {
        assertActiveTransaction();
        BeanFetcher.getBean(BookGateway.class).sendDelete(String.valueOf(book.getId()));
    }

    private void assertActiveTransaction() {
        assert TransactionSynchronizationManager.isActualTransactionActive();
    }
}
