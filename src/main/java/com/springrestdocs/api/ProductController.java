package com.springrestdocs.api;

import com.springrestdocs.api.exception.InvalidRequestException;
import com.springrestdocs.application.CreateProductDto;
import com.springrestdocs.application.ProductCondition;
import com.springrestdocs.application.ProductResource;
import com.springrestdocs.application.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResource>> getProducts(ProductConditionRequest conditionRequest) {
        ProductCondition condition = conditionRequest.toCondition();
        List<ProductResource> products = productService.getProducts(condition);
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResource> getProduct(@PathVariable long id){
        ProductResource product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestHeader("RegisterId") String userId,
                       @Valid @RequestBody CreateProductRequest request,
                       Errors errors){
        if(errors.hasErrors()){
            throw new InvalidRequestException(errors.getAllErrors());
        }
        CreateProductDto createProductDto = request.toDto();
        long productId = productService.create(createProductDto);
    }
}
