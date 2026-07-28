package com.uif.demo.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService service;

    @PostMapping("/{name}")
    public ResponseEntity<String> hello(String name) {
        // Walidacja

        ExampleDomain exampleDomain = new ExampleDomain(service);
        String helloResponse = exampleDomain.sayHelloTo(name);

        return ResponseEntity.ok(helloResponse);
    }

}
