package com.joeldavg.obrestdatajpa.controller;

import com.joeldavg.obrestdatajpa.model.entity.Book;
import com.joeldavg.obrestdatajpa.service.BookService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/books")
public class BookController {

    private final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("{id}")
    @Operation(summary = "Find a Book by Id", description = "Find a book in the database by using the Id of the book")
    public ResponseEntity<Book> findById(@Parameter(description = "Primary key type date Long") @PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {

        if (book.getId() != null) {
            LOG.warn("Tryin to create a book with Id");
            System.out.println("Tryin to create a book with Id");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.save(book));
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {

        if (!bookService.existById(id)) {
            LOG.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        Optional<Book> bookDB = bookService.findById(id);
        bookDB.ifPresent(bookUpdate -> {
            bookUpdate.setAuthor(book.getAuthor());
            bookUpdate.setPrice(book.getPrice());
            bookUpdate.setPages(book.getPages());
            bookUpdate.setOnline(book.getOnline());
            bookUpdate.setTitle(book.getTitle());
            bookUpdate.setReleaseDate(book.getReleaseDate());
        });

        LOG.info("Updating book: " + bookDB.get());

        return ResponseEntity.ok(bookService.update(bookDB.get()));
    }

    @DeleteMapping("{id}")
//    @Operation(hidden = true)
    @Hidden // Ignore method for no showing in Rest documentation
    public ResponseEntity deleteById(@PathVariable Long id) {

        if (!bookService.existById(id)) {
            LOG.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }

        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Hidden
    public ResponseEntity deleteAll() {
        LOG.info("REST request for deleting all books");
        bookService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
