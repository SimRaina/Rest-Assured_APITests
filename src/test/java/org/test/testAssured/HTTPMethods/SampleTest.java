package org.test.testAssured.HTTPMethods;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SampleTest {
	
	
	@Test
	public void test() {
		
		given()
		      .contentType("Content-Type")
		      .body("test")
		
		.when()
		      .delete()
		
		
		.then()
		      .log().all();
		
		
		
	}
	
	@Test
	public void test1() {
		given().
		     contentType("Content-Type").
		     body("test")
		.when().
		    get()
		.then().
		      log().all();
	}

}
