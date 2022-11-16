package org.test.testAssured.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import org.test.configReader.ConfigReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Listeners(org.test.Listener.ListenerTest.class)
public class Test1_JSON_GET {
	
	String host = ConfigReader.getValueFromPropertyFile("Typicode_Host");
	String host1 = ConfigReader.getValueFromPropertyFile("Ergast_Host");
	
	// Checking status code
	@Test
	public void testStatusCode() {
		given().
		get(host+"/posts/3").
		then().
		statusCode(200);
	}
	
	//Log response
	
	@Test
	public void testLogging() {
		given().
		get(host+"/posts/3").
		then().
		log().all();
	}
	
	//Checking single content using org.hamcrest.Matchers
	
	@Test
	public void testEqualToFunction() {
		given().
		get(host+"/posts/3").
		then().
		body("id", equalTo(3));
	}
	
	
	//Checking multiple content using org.hamcrest.Matchers
	@Test
	public void testHasItemFunction() {
		given().
		get(host+"/posts").
		then().
		body("id", hasItems(3,2,100));
	}
	
	//parameters and headers can be set
	@Test
	public void testParametersandHeaders() {
		given().
		param("Key1","Value1").
		header("HeaderKey1","HeaderValue1").
		when().
		get(host+"/posts/3").
		then().
		statusCode(200).   
		log().all();
	}
	
	//Using and to increase readability
	
	@Test
	public void testParametersandHeadersWithAnd() {
		given().param("Key1","Value1").and().header("HeaderKey1","HeaderValue1").
		when().get(host+"/posts/3").then().statusCode(200).and().body("id", equalTo(3));
	}

	//Using is
	//Not setting root
	
	@Test
	public void testWithoutRoot() {
		given().
		get(host1+"/api/f1/2017/circuits.json").
		then().
		body("MRData.CircuitTable.Circuits[0].Location.locality", is("Melbourne")).
		log().all();
	}
	
	//Using is
	// Setting root
	
	@Test
	public void testWithRoot() {
		given().
		get(host1+"/api/f1/2017/circuits.json").
		then().
		root("MRData.CircuitTable").
		body("Circuits[0].Location.locality", is("Melbourne"));
	}
	
	//Detach Root
	
	@Test
	public void testDetachRoot() {
		given().
		get(host1+"/api/f1/2017/circuits.json").
		then().
		root("MRData.CircuitTable").
		body("Circuits[0].Location.locality", is("Melbourne")).
		detachRoot("CircuitTable").
		body("CircuitTable.Circuits[0].Location.locality", is("Melbourne"));
	}
}
