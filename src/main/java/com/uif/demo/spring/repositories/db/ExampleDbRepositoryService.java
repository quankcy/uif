package com.uif.demo.spring.repositories.db;

import org.springframework.stereotype.Repository;

@Repository
public class ExampleDbRepositoryService implements ExampleDbRepositoryInterface {

    @Override
    public void save(String name) {
        // Implementation of save method
        System.out.println("Saving name: " + name);
    }

}
