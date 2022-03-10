package com.springrestdocs.application;

import com.springrestdocs.domain.Product;
import com.springrestdocs.domain.ProductIdGenerator;

import java.util.List;

import static java.util.Arrays.asList;

public class ProductResourceFixture {
    public static ProductResource.ProductResourceBuilder aProductResource(){
        return ProductResource.builder()
                .description("description")
                .id(ProductIdGenerator.generate())
                .name("name")
                .price(3000L);
    }

    public static List<ProductResource> aList() {
        return asList(aProductResource().build(), aProductResource().build());
    }
}
