package com.springrestdocs.application;

import com.springrestdocs.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<ProductResource> findById(long id);
    List<ProductResource> findAll(ProductCondition condition);

    void save(Product product);
}
