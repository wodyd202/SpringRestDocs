package com.springrestdocs.domain;

import lombok.Getter;

@Getter
public class Product {
    private long id;
    private String name;
    private String description;
    private long price;

    private Product(String name, String description, long price) {
        this.id = ProductIdGenerator.generate();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Product of(String name, String description, long price){
        return new Product(name, description, price);
    }
}
