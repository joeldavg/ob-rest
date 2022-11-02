package com.joeldavg.obrestdatajpa.repository;

import com.joeldavg.obrestdatajpa.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
