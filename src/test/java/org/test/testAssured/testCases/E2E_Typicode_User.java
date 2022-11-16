package org.test.testAssured.testCases;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.test.configReader.ConfigReader;

@Listeners(org.test.Listener.ListenerTest.class)
public class E2E_Typicode_User {
	
	String host = ConfigReader.getValueFromPropertyFile("Typicode_Host");
	
	public static Map<String, String> map = new HashMap<String, String>();
	
	
	@Test
	public void e2eTypicodeUser(){
		RequestSpecification request=given();
		request.header("Content-Type","application/json");
		
		JSONObject json = new JSONObject();
		json.put("userId", "22");
		json.put("id", "22");
		json.put("title", "This is test");
		json.put("body", "This is the first test to create user");
		
		request.body(json.toJSONString());
			
		// Response response = request.get(host+"/posts/22");
		Response response = request.post(host+"/posts");
		
		// Response response = request.get(host+"/posts/22");
		// Response response = request.put(host+"/posts/22");
		// Response response = request.get(host+"/posts/22");
		// Response response = request.delete(host+"/posts/22");
		// Response response = request.get(host+"/posts/22");
		
		int code = response.getStatusCode();
		String data = response.asString();
		System.out.println("Response is "+data);
		
		Assert.assertEquals(code, 201);
	}
}

