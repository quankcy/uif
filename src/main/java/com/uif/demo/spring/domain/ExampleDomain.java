package com.uif.demo.spring.domain;

import com.uif.demo.spring.repositories.db.ExampleDbService;
import lombok.RequiredArgsConstructor;

/* Agnostic domain */
@RequiredArgsConstructor
public class ExampleDomain {

    private final ExampleDbService service;

    public String sayHelloTo(String name) {
        return String.format("Hello, %s!", name);
    }

}
