package com.github.maly7.subscriber.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class BookIngestService {
    private static final Logger LOG = LoggerFactory.getLogger(BookIngestService.class);

    @ServiceActivator(inputChannel = "bookIngestChannel")
    public void ingestBook(String book) {
        LOG.info("Received Book: [{}]", book);

        // Fetch Book through Publisher API

        // Save in solr repository
    }
}
