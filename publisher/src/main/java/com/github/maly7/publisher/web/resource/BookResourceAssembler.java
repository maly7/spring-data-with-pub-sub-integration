package com.github.maly7.publisher.web.resource;

import com.github.maly7.publisher.domain.Book;
import com.github.maly7.publisher.web.BookController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BookResourceAssembler extends ResourceAssemblerSupport<Book, ResourceSupport> {

    public BookResourceAssembler() {
        super(BookController.class, ResourceSupport.class);
    }

    @Override
    public ResourceSupport toResource(Book entity) {
        HALResource resource = new HALResource();

        Map<String, String> identifiers = new HashMap<>();
        identifiers.put("title", entity.getTitle());

        resource.embed("identifiers", identifiers);

        resource.add(linkTo(methodOn(BookController.class).get(entity.getId())).withRel(Link.REL_SELF));

        return resource;
    }
}
