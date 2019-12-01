package com.rest.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Get_Request {
	
	@Test
	public void getProductTest() {
		
		// Specify base URI
		RestAssured.baseURI= "http://localhost:3030";
		
		// Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response object
		Response response = httpRequest.request(Method.GET,"/products?id=43900");
		
		// print response on console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: "+responseBody);
		
		// Validating Header
		String contentType = response.header("Content-Type");
		System.out.println("Content Type is: "+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
		String transferEncoding = response.header("Transfer-Encoding");
		System.out.println("Transfer Encoding is: "+transferEncoding);
		Assert.assertEquals(transferEncoding, "chunked");
		
		String vary = response.header("Vary");
		System.out.println("Transfer Encoding is: "+vary);
		Assert.assertEquals(vary, "Accept, Accept-Encoding");
		
	}

}
