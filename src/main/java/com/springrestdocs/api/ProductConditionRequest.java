package com.springrestdocs.api;

import com.springrestdocs.application.ProductCondition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductConditionRequest {
    private int page, size;

    public ProductCondition toCondition() {
        return ProductCondition.of(page, size);
    }
}
