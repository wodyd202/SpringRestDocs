package com.springrestdocs.application;

import com.springrestdocs.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product mapFrom(CreateProductDto dto){
        return Product.of(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice()
        );
    }
}
