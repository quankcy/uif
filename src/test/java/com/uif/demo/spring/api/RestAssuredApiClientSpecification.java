package com.uif.demo.spring.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestAssuredApiClientSpecification {

    public static RequestSpecification springAppRestControllerRepresentation(int springAppPort){
        RequestSpecification springAppRestControllerRepresentation = new RequestSpecBuilder()
                .setBaseUri("http://localhost:"+springAppPort)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                // Automatyczne logowanie żądań i odpowiedzi w przypadku błędu
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        return springAppRestControllerRepresentation;
    }

}
