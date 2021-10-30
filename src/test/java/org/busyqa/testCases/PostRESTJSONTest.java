package org.busyqa.testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRESTJSONTest {

    HashMap map = new HashMap();  // generic hashmap because data could be of any data type

    String url = "https://reqres.in/api/users";

    @BeforeTest
    public void postData(){
         // Creating our test data
        map.put("name", "Simran");
        map.put("job", "QA");
    }

    @Test
    public void postRestJSONTest(){


        given().
                contentType("application/json").
                header("myheader", "test").
                body(map).
        when().
                post(url).
        then().
                log().all().
                statusLine("HTTP/1.1 201 Created").
                body("name", equalTo("Simran")).
                header("Content-Type", "application/json; charset=utf-8");

    }
}
