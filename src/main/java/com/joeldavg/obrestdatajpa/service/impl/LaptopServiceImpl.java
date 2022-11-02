package com.joeldavg.obrestdatajpa.service.impl;

import com.joeldavg.obrestdatajpa.model.entity.Laptop;
import com.joeldavg.obrestdatajpa.repository.LaptopRepository;
import com.joeldavg.obrestdatajpa.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Laptop> findAll() {
        return laptopRepository.findAll();
    }

    @Override
    @Transactional
    public Laptop save(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

}
