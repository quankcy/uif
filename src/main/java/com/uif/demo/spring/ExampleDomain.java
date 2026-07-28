package com.uif.demo.spring;

import lombok.RequiredArgsConstructor;

/* Agnostic domain */
@RequiredArgsConstructor
public class ExampleDomain {

    private final ExampleService service;

    public String sayHelloTo(String name) {
        return String.format("Hello, %s!", name);
    }

}
