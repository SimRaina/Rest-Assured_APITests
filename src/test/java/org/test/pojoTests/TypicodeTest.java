package org.test.pojoTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.test.pojoClasses.PostPayLoad;
import static io.restassured.RestAssured.given;

public class TypicodeTest {
    
    @Test
    public void test1() {

        PostPayLoad payload = new PostPayLoad(
            22,
            22,
            "This is test",
            "This is the first test to create user"
    );

    Response response =
            given()
                    .contentType("application/json")
                    .body(payload)
            .when()
                    .post("/posts")
            .then()
                    .statusCode(201)
                    .extract().response();

    System.out.println("Response is: " + response.asString());
    }
}
