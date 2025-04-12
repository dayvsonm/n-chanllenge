package com.dayvson.n_challenge.controller;

import com.dayvson.n_challenge.dto.UserRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @LocalServerPort
    private int port;

    private static String token;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    void shouldLoginSuccessfully() {
        String loginJson = """
            {
              "email": "dayvson@example.com",
              "password": "1234safe"
            }
            """;

        token = given()
                .contentType(ContentType.JSON)
                .body(loginJson)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .asString();
    }

    @Test
    @Order(2)
    void shouldCreateUserSuccessfully() {
        UserRequest request = new UserRequest("Novo Usuário", "novo@email.com", "123456789ET", "senha123");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("email", equalTo("novo@email.com"))
                .body("fullName", equalTo("Novo Usuário"));
    }

    @Test
    @Order(3)
    void shouldListUsers() {
        given().header("Authorization", "Bearer " + token).when().
        get("/api/users")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    @Order(4)
    void shouldGetUserById() {
        given().header("Authorization", "Bearer " + token).when().
        get("/api/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }
}
