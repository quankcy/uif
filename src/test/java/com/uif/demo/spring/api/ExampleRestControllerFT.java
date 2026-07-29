package com.uif.demo.spring.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.uif.demo.UifApplication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.uif.demo.spring.api.RestAssuredApiClientSpecification.springAppRestControllerRepresentation;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = UifApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleRestControllerFT {

    @LocalServerPort
    private int springAppPort;

    private static WireMockServer wireMockServer;


    @BeforeAll
    public static void setUp() {
        wireMockServer = new WireMockServer(8765);
        wireMockServer.start();
    }

    @AfterAll
    public static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void jakisLadnieNazwanyTest() {
        System.out.println("Spring port: " + springAppPort);
        System.out.println(wireMockServer.baseUrl());
        // GIVEN
        /*
        wireMockServer.getStubMappings() <- miejsce w pamieci, zeby zobaczyc aktualna zawartosc wiremocka.
        Nie mozna wejsc przez URL, np. GET http://localhost:8080/__admin/mappings bo breakpoint zablokuje proces
        Poniżej dynamiczne ustawienie stuba
         */
        wireMockServer.stubFor(get(urlEqualTo("/{name}"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello {{request.path.userId}}!")));

        // WHEN
//        1) Walidacja zrobiona bezpośrednio na Rest Assured
//        RestAssured.given(springAppRestControllerRepresentation(springAppPort)) // wgranie specyfikacji reprezentujacej KLIENTA tej aplikacji
//                .when()
//                .post("/{name}", "Kamil") // wywolanie endpointu Springa, ktory w srodku wywoluje WireMocka
//                .then()
//                .statusCode(200)
//                .contentType(ContentType.JSON)
//                .body(equalTo("Hello Kamil!"));

//        2) RestAssured uzyte jako klient, a walidacja na jUnit ( testNG )
        ExtractableResponse<Response> response = RestAssured.given(springAppRestControllerRepresentation(springAppPort)) // wgranie specyfikacji reprezentujacej KLIENTA tej aplikacji
                .when()
                .post("/{name}", "Kamil") // wywolanie endpointu Springa, ktory w srodku wywoluje WireMocka
                .then()
                .extract();

        // THEN
        // Uzycie assertAll sprawi, ze wszystkie assercje zostana przeprowadzone i zebrane bledy beda przekazane na raz
        Assertions.assertAll(
                () -> Assertions.assertEquals(200, response.statusCode()),
                () -> Assertions.assertEquals(ContentType.JSON.toString(), response.contentType()),
                () -> Assertions.assertEquals("Hello, Kamil!", response.body().asString())
        );

    }


}
