package com.joeldavg.obrestdatajpa.service;

import com.joeldavg.obrestdatajpa.model.entity.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {
        // configuration de la prueba
        Book book = new Book(1L,
                "El señor de los anillos",
                "Joel",
                1000,
                49.99,
                LocalDate.now(),
                true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        // se ejecuta el comportamiento a testear
        double price = calculator.calculatePrice(book);
        System.out.println(price);
        //comprobacion asserciones
        assertTrue(price > 0);
        assertEquals(49.99+5+2.99, price);

    }
}