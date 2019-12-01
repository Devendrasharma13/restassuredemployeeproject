package com.rest.api;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Post_Request {
	
	@Test
	public void test_RegistrationSuccessful() {
		
		// Specify base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response object
		
		// Request payload sending along with post request
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("FirstName", "Tom3");
		requestParams.put("LastName", "Hank3");
		requestParams.put("UserName", "Tom3");
		requestParams.put("Password", "secret3");
		requestParams.put("Email", "tom3@example.com");

		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		// Response Object
		Response response = httpRequest.request(Method.POST,"/register");
		
		// print response on console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: "+responseBody);
		
		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: "+statusCode);
		Assert.assertEquals(statusCode, 201);
		
		// Verify string from the response body
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println("Success Code is: "+successCode);
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
		
	}

}
