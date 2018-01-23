package com.github.maly7.publisher.service;

import com.github.maly7.publisher.web.BookController;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Helper class for building links in the payload expression, in reality this should
 * probably use {@link org.springframework.hateoas.EntityLinks}
 */
@Service
public class LinksHelper {
    public Link selfLinkToBook(Long id) {
        return linkTo(methodOn(BookController.class).get(id)).withRel(Link.REL_SELF);
    }
}
