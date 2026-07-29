package com.uif.demo.spring.api;

import com.uif.demo.spring.domain.ExampleDomain;
import com.uif.demo.spring.repositories.db.ExampleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleRestController {

    private final ExampleDbService service;

    @PostMapping("/{name}")
    public ResponseEntity<String> hello(@PathVariable String name) {
        // Walidacja

        ExampleDomain exampleDomain = new ExampleDomain(service);
        String helloResponse = exampleDomain.sayHelloTo(name);

        return ResponseEntity.ok(helloResponse);
    }

}
