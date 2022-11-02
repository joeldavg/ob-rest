package com.joeldavg.obrestdatajpa.service;

import com.joeldavg.obrestdatajpa.model.entity.Book;

public class BookPriceCalculator {

    public double calculatePrice(Book book) {

        double price = book.getPrice();

        if (book.getPages() > 300) {
            price += 5;
        }

        // price + shipping fee
        price += 2.99;

        return price;
    }

}
