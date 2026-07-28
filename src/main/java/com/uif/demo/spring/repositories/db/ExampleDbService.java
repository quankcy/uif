package com.uif.demo.spring.repositories.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleDbService {

    private final ExampleDbRepositoryInterface repository;

}
