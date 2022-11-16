package org.test.testAssured.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import org.test.configReader.ConfigReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Listeners(org.test.Listener.ListenerTest.class)
public class Test1_XML_GET {
	
	String host = ConfigReader.getValueFromPropertyFile("ThomasBayer_Host");
	
	//Checking XML Response for single body content
	//@Test
	public void testSingleContent() {
		given().
		get(host+"/sqlrest/CUSTOMER/10").
		then().
		body("CUSTOMER.ID", equalTo("10")).
		log().all();
	}
	
	//Checking XML Response for multiple body content
		//@Test
		public void testMultipleContent() {
			given().
			get(host+"/sqlrest/CUSTOMER/10").
			then().
			body("CUSTOMER.ID", equalTo("10")).
			body("CUSTOMER.FIRSTNAME", equalTo("Sue")).
			body("CUSTOMER.LASTNAME", equalTo("Fuller")).body("CUSTOMER.STREET", equalTo("135 Upland Pl.")).
			body("CUSTOMER.CITY", equalTo("Dallas")).
			log().all();
		}
		
		//Checking complete text in one go
		//@Test
		public void testCompleteTextInOneGo() {
			
			given().
			get(host+"/sqlrest/CUSTOMER/10").
			then().
			body("CUSTOMER.text()", equalTo("10SueFuller135 Upland Pl.Dallas")).
			log().all();
		}
		
		//Xpath can also be used to find values
		//@Test
		public void testUsingXpath() {
			given().
			get(host+"/sqlrest/CUSTOMER/10").
			then().
			body(hasXPath("/CUSTOMER/FIRSTNAME", containsString("Sue")));
		}
		
		// Xpath type 2
		
	@Test
	public void testXpath2() {
		given().
		get(host+"/sqlrest/INVOICE/").
		then().
		body(hasXPath("/INVOICEList/INVOICE[text()='40']")).log().all();
	}
		
		
		
		
		
		
		
		
		
		
		
		
		

}
