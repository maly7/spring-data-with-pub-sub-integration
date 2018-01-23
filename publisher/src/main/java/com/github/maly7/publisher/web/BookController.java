package com.github.maly7.publisher.web;

import com.github.maly7.publisher.data.BookRepository;
import com.github.maly7.publisher.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookResourceAssembler resourceAssembler;

    @RequestMapping(method = RequestMethod.POST, produces = MediaTypes.HAL_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceSupport create(@RequestBody Book newBook) {
        return resourceAssembler.toResource(bookRepository.save(newBook));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public ResourceSupport get(@PathVariable Long id) {
        return resourceAssembler.toResource(bookRepository.findOne(id));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaTypes.HAL_JSON_VALUE)
    public ResourceSupport update(@RequestBody Book updatedBook, @PathVariable Long id) {
        updatedBook.setId(id);
        return resourceAssembler.toResource(bookRepository.save(updatedBook));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        bookRepository.delete(id);
    }
}
