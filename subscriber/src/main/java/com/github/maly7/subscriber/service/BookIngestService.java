package com.github.maly7.subscriber.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookIngestService {
    private static final Logger LOG = LoggerFactory.getLogger(BookIngestService.class);

    @Autowired
    private RestTemplate restTemplate;

    @ServiceActivator(inputChannel = "bookIngestChannel")
    public void ingestBook(String bookHref) {
        LOG.info("Received Book href: [{}]", bookHref);

        ResponseEntity<String> bookJson = restTemplate.getForEntity(bookHref, String.class);
        LOG.info("Fetched book: [{}] from publisher.", bookJson);

        // Save in solr repository
    }
}
