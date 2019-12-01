package datadriven;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import groovy.xml.XmlUtil;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.XLUtilis;

public class DataDriven_AddNewEmp2 {
	
	@Test(dataProvider = "empdataprovider")
	void addNewEmployees(String ename, String esal, String eage) {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		// Created data which can send along with the post request
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", ename);
		requestParams.put("salary",esal);
		requestParams.put("age", eage);
		
		// Add a header stating the request body is a JSON
		httpRequest.header("Content-Type","application/json");
		
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		// Post Request 
		Response response = httpRequest.request(Method.POST,"/create");
		
		// capture response body to perform validation 
		String responseBody = response.getBody().asString();
		
		System.out.println("Response Body  is: "+responseBody);
		
		
		Assert.assertEquals(responseBody.contains(ename),true);
		Assert.assertEquals(responseBody.contains(esal),true);
		Assert.assertEquals(responseBody.contains(eage),true);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	/*
	@DataProvider(name="empdataprovider")
	String[][] getEmpData(){
		String[][] empData = {{"abcd-101","3000","40"},{"abcd-201","4000","35"},{"abcd-301","4500","25"}};
		return empData;
	}
	*/
	
	@DataProvider(name="empdataprovider")
	String[][] getEmpData() throws IOException{
		
		// Read data from excelsheet
		String path = System.getProperty("user.dir") + "/src/test/java/utils/emp_testdata.xlsx";
		
		System.out.println(path);
		
		int rownum = XLUtilis.getRowCount(path, "Emp");
		int colnum = XLUtilis.getCellCount(path, "Emp", 1);
		
		String empdata[][] = new String[rownum][colnum];
		
		//String[][] empData = {{"abcd-101","3000","40"},{"abcd-201","4000","35"},{"abcd-301","4500","25"}};
		
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {
				empdata[i-1][j]= XLUtilis.getCellData(path, "Emp", i, j);
			}
		}
		
		return empdata;
	}
	
	
}
