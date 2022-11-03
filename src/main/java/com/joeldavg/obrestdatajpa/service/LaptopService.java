package com.joeldavg.obrestdatajpa.service;

import com.joeldavg.obrestdatajpa.model.entity.Laptop;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LaptopService {

    List<Laptop> findAll();

    Laptop save(Laptop laptop);

    Optional<Laptop> findOneById(Long id);

    boolean existsById(Long id);

    Laptop update(Laptop laptop);

    void deleteById(Long id);

    void deleteAll();
}
