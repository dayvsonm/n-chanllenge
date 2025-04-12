package com.dayvson.n_challenge.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void shouldLoginAndReturnToken() {
        String json = """
            {
                "email": "dayvson@example.com",
                "password": "1234safe"
            }
            """;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body(notNullValue()); // ou validar o conteúdo do token
    }
}
