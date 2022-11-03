package com.joeldavg.obrestdatajpa.controller;

import com.joeldavg.obrestdatajpa.model.entity.Laptop;
import com.joeldavg.obrestdatajpa.service.LaptopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/laptops")
public class LaptopController {

    private final Logger LOG = LoggerFactory.getLogger(LaptopController.class);

    @Autowired
    private LaptopService laptopService;

    @GetMapping
    public ResponseEntity<List<Laptop>> findAll() {
        return ResponseEntity.ok(laptopService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {
        return laptopService.findOneById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {

        if (laptop.getId() != null) {
            LOG.warn("Trying to create a laptop with Id");
            return ResponseEntity.badRequest().build();
        }

        laptop = laptopService.save(laptop);

        LOG.info("Laptop created successfully: " + laptop);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(laptop);
    }

    @PutMapping("{id}")
    public ResponseEntity<Laptop> update(@PathVariable Long id, @RequestBody Laptop laptop) {

        if (!laptopService.existsById(id)) {
            LOG.warn("Trying to update a non existent laptop");
            return ResponseEntity.notFound().build();
        }

        Optional<Laptop> laptopDB = laptopService.findOneById(id);
        laptopDB.ifPresent(lap -> {
            lap.setBrand(laptop.getBrand());
            lap.setModel(laptop.getModel());
        });

        LOG.info("Updating laptop: " + laptopDB.get());

        return ResponseEntity.ok(laptopService.update(laptopDB.get()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!laptopService.existsById(id)) {
            LOG.warn("Trying to delete a non existent laptop");
            return ResponseEntity.notFound().build();
        }

        laptopService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        LOG.info("REST request for deleting all laptops");
        laptopService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
