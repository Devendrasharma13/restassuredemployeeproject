package com.rest.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_Request_JSON_Body {

	@Test
	public void validatingJsonResponseTest() {
		// Specify base URI
		RestAssured.baseURI = "https://samples.openweathermap.org";
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response Object
		Response response = httpRequest.request(Method.GET,"/data/3.0/stations?appid=b1b15e88fa797225412429c1c50c122a1");
		
		// Print response on console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: "+responseBody);
		
		// Verify specific string present or not 
		Assert.assertEquals(responseBody.contains("San Francisco Test Station"), true);
		
		
		
		
	}
	
}
