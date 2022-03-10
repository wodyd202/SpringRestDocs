package com.springrestdocs.application;

import com.springrestdocs.application.exception.ProductNotFoundException;
import com.springrestdocs.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResource getProduct(long id){
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public List<ProductResource> getProducts(ProductCondition condition) {
        return productRepository.findAll(condition);
    }

    public long create(CreateProductDto dto) {
        Product product = productMapper.mapFrom(dto);
        productRepository.save(product);
        return product.getId();
    }
}
