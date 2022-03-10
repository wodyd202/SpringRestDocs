package com.springrestdocs.application;

import com.springrestdocs.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResource {
    private long id;
    private String name;
    private String description;
    private long price;

    public static ProductResource from(Product product) {
        return new ProductResource(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
