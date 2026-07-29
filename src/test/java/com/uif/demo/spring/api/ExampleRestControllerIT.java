package com.uif.demo.spring.api;

import com.uif.demo.UifApplication;
import com.uif.demo.spring.domain.ExampleDomain;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
//import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = UifApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleRestControllerIT {

//    @TestConfiguration
//    static class TestConfig {
//        @Bean
//        public ExampleDomain exampleDomain() {
//            return mock(ExampleDomain.class);
//        }
//    }
//
//    @MockBean
//    private ExampleDomain exampleDomain;

    @Test
    public void asd(){

    }

}
