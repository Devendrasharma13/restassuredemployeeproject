package datadriven;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDriven_AddNewEmp {
	
	@Test
	void addNewEmployees() {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		// Created data which can send along with the post request
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", "Forest");
		requestParams.put("salary","2000");
		requestParams.put("age", "23");
		
		// Add a header stating the request body is a JSON
		httpRequest.header("Content-Type","application/json");
		
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		// Post Request 
		Response response = httpRequest.request(Method.POST,"/create");
		
		// capture response body to perform validation 
		String responseBody = response.getBody().asString();
		
		Assert.assertEquals(responseBody.contains("Forest"),true);
		Assert.assertEquals(responseBody.contains("2000"),true);
		Assert.assertEquals(responseBody.contains("23"),true);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
}
