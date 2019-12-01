package com.rest.api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_GET_Request {

	@Test
	public void fullHeaderReadingTest() {
		// Base URI
		RestAssured.baseURI = "https://samples.openweathermap.org";
		
		// Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Reponse Object
		Response response = httpRequest.request(Method.GET,"/data/3.0/stations?appid=b1b15e88fa797225412429c1c50c122a1");
		
		
		// print response on console
		String printBody = response.getBody().prettyPrint();

		// capture all the headers from response 
		Headers allHeader = response.headers();
		for (Header header : allHeader) {
			System.out.println(header.getName()+": "+header.getValue());
		}
		
	}
	
}
