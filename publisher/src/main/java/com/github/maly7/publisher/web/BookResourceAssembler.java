package com.github.maly7.publisher.web;

import com.github.maly7.publisher.domain.Book;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BookResourceAssembler extends ResourceAssemblerSupport<Book, ResourceSupport> {

    public BookResourceAssembler() {
        super(BookController.class, ResourceSupport.class);
    }

    @Override
    public ResourceSupport toResource(Book entity) {
        ResourceSupport resource = new ResourceSupport();
        // TODO: embed props here
        resource.add(linkTo(methodOn(BookController.class).get(entity.getId())).withRel(Link.REL_SELF));

        return resource;
    }
}
