package com.rest.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_GET_Request_ExtractValue {
	
	@Test
	public void getExtractValueFromNode() {
		
		// Specify BaseURI
		RestAssured.baseURI = "https://samples.openweathermap.org";		
		
		// Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response Object 
		Response response = httpRequest.request(Method.GET,"/data/3.0/stations?appid=b1b15e88fa797225412429c1c50c122a1");
		
		// print response on console 
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: "+responseBody);
		
		JsonPath jsonPath = response.jsonPath();
		System.out.println(jsonPath.get("id"));
		System.out.println(jsonPath.get("created_at"));
		System.out.println(jsonPath.get("updated_at"));
		System.out.println(jsonPath.get("external_id"));
		System.out.println(jsonPath.get("name"));
		System.out.println(jsonPath.get("longitude"));
		System.out.println(jsonPath.get("latitude"));
		System.out.println(jsonPath.get("altitude"));
		System.out.println(jsonPath.get("rank"));
		
		Assert.assertEquals(jsonPath.get("name"), "San Francisco Test Station");
		
	}
}
