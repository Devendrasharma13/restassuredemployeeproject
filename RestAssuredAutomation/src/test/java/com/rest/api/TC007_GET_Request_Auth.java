package com.rest.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_Request_Auth {
	
	@Test
	public void getAuthTest() {
		
		// Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
		
		// Basic authentication
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");
		
		RestAssured.authentication = authScheme;
		
		// Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response Object 
		Response response = httpRequest.request(Method.GET,"/");
		
		// print response on console 
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: "+responseBody);
			
		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is: "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
	}
	
}
