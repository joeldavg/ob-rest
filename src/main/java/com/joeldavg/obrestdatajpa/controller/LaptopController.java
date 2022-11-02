package com.joeldavg.obrestdatajpa.controller;

import com.joeldavg.obrestdatajpa.model.entity.Laptop;
import com.joeldavg.obrestdatajpa.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/laptops")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @GetMapping
    public ResponseEntity<List<Laptop>> findAll() {
        return ResponseEntity.ok(laptopService.findAll());
    }

    @PostMapping
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(laptopService.save(laptop));
    }

}
