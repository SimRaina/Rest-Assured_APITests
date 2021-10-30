package org.busyqa.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutRESTJSONTest {

    @Test
    public void putRestJSONTest(){

        JSONObject jsonobj = new JSONObject();

        jsonobj.put("name", "Tracey");
        jsonobj.put("job", "QA");

        String request = jsonobj.toJSONString(); // to convert JSONObject to JSONString

        given().contentType("application/json").body(request).
        when().put("https://reqres.in/api/users/6").
        then().log().all().statusCode(200).body("name", equalTo("Tracey"));
    }
}
