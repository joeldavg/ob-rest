package com.joeldavg.obrestdatajpa.service;

import com.joeldavg.obrestdatajpa.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book save(Book book);

    Book update(Book book);

    List<Book> findAll();

    Optional<Book> findById(Long id);

    void deleteById(Long id);

    boolean existById(Long id);

    void deleteAll();
}
