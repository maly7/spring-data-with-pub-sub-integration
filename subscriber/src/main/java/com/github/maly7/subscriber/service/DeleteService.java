package com.github.maly7.subscriber.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteService.class);

    @ServiceActivator(inputChannel = "deleteChannel")
    public void delete(String book) {
        LOG.info("Deleting Book with id: [{}]", book);
        // Call delete on the solr repository
    }
}
