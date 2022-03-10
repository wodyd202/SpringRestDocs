package com.springrestdocs.domain;

import java.util.concurrent.atomic.AtomicLong;

public class ProductIdGenerator {
    private static AtomicLong ATOMIC_LONG = new AtomicLong();

    public static long generate(){
        return ATOMIC_LONG.incrementAndGet();
    }
}
