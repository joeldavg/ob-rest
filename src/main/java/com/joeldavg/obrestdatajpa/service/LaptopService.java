package com.joeldavg.obrestdatajpa.service;

import com.joeldavg.obrestdatajpa.model.entity.Laptop;

import java.util.List;

public interface LaptopService {

    List<Laptop> findAll();

    Laptop save(Laptop laptop);

}
