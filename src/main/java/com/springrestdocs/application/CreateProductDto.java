package com.springrestdocs.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProductDto {
    private String name;
    private String description;
    private long price;

    public static CreateProductDto of(String name, String description, long price){
        return new CreateProductDto(name, description, price);
    }
}
