package com.uif.demo.spring.domain;

import com.uif.demo.spring.repositories.db.ExampleDbService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeanConfiguration {

    @Bean
    public ExampleDomain exampleDomain(ExampleDbService service) {
        return new ExampleDomain(service);
    }

}
