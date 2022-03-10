package com.springrestdocs.infrastructure;

import com.springrestdocs.application.ProductCondition;
import com.springrestdocs.application.ProductRepository;
import com.springrestdocs.application.ProductResource;
import com.springrestdocs.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InmemoryProductRepository implements ProductRepository {
    private final Map<Long, Product> mapRepo = new ConcurrentHashMap<>();
    private final List<Product> listRepo = new ArrayList<>();

    @Override
    public Optional<ProductResource> findById(long id) {
        Product product = mapRepo.get(id);
        if(product == null){
            return Optional.empty();
        }
        return Optional.of(ProductResource.from(product));
    }

    @Override
    public List<ProductResource> findAll(ProductCondition condition) {
        List<ProductResource> productResources = new ArrayList<>();
        for(int i =condition.getPage() * condition.getSize();i< condition.getPage() * condition.getSize() + condition.getSize();i++){
            if(i >= listRepo.size()){
                break;
            }
            productResources.add(ProductResource.from(listRepo.get(i)));
        }
        return productResources;
    }

    @Override
    public void save(Product product) {
        mapRepo.put(product.getId(), product);
        listRepo.add(product);
    }
}
