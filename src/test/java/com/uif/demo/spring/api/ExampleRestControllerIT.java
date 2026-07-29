package com.uif.demo.spring.api;

import com.uif.demo.UifApplication;
import com.uif.demo.spring.domain.ExampleDomain;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static com.uif.demo.spring.api.RestAssuredApiClientSpecification.springAppRestControllerRepresentation;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = UifApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleRestControllerIT {

    @LocalServerPort
    private int springAppPort;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ExampleDomain exampleDomain() {
            return mock(ExampleDomain.class);
        }
    }

    @MockitoBean
    private ExampleDomain exampleDomain;

    @Test
    public void asd() {
        // GIVEN
        Mockito.when(exampleDomain.sayHelloTo("Kamil")).thenReturn("Hi, Kamil!");

        // WHEN
        ExtractableResponse<Response> response = RestAssured.given(springAppRestControllerRepresentation(springAppPort)) // wgranie specyfikacji reprezentujacej KLIENTA tej aplikacji
                .when()
                .post("/{name}", "Kamil") // wywolanie endpointu Springa, ktory w srodku wywoluje WireMocka
                .then()
                .extract();

        // THEN
        Assertions.assertAll(
                () -> Assertions.assertEquals("Hi, Kamil!", response.asPrettyString())
        );

    }

}
