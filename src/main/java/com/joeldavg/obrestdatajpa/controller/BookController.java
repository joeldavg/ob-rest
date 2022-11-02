package com.joeldavg.obrestdatajpa.controller;

import com.joeldavg.obrestdatajpa.model.entity.Book;
import com.joeldavg.obrestdatajpa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.save(book));
    }

    /*@PutMapping("{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> bookDB = bookService.findById(id);

        return ;
    }*/

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {

        if (!bookService.existById(id)) return ResponseEntity.notFound().build();

        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
