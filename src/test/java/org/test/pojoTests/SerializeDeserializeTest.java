package org.test.pojoTests;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import org.test.pojoClasses.User;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SerializeDeserializeTest {

    @Test
    public void testSerialize() { // Java Objects to JSON
        // Base URI
        RestAssured.baseURI = "https://api.example.com";

        // Create User object
        User user = new User("John Doe", 30, "john@example.com");

        // Send POST request with serialized JSON
        given()
            .contentType(ContentType.JSON)
            .body(user)  // Automatically serialized to JSON
        .when()
            .post("/users")
        .then()
            .statusCode(201);
    }

    /*
    Let's assume an API returns the following JSON response:
        {
            "name": "John Doe",
            "age": 30,
            "email": "john@example.com"
        }
    */
    @Test
    public void testDeserialize() {  // JSON to Java Objects

        // Base URI
        RestAssured.baseURI = "https://api.example.com";

        // Send GET request and get response
        Response response = 
            given()
                .contentType(ContentType.JSON)
            .when()
                .get("/users/1")
            .then()
                .statusCode(200)
                .extract()
                .response();

        // Deserialize JSON to User object
        User user = response.as(User.class);

        // Print details
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Email: " + user.getEmail());
    }
}