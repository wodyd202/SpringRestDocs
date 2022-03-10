package com.springrestdocs.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductCondition {
    private final int page;
    private final int size;

    public static ProductCondition of(int page, int size) {
        return new ProductCondition(page, size);
    }
}
