package com.springrestdocs.application.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(){
        super("not found product");
    }
}
