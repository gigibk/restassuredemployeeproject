package com.employeeAPI.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;
import com.employeeAPI.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_PUT_Employee_Record extends TestBase{

	 RequestSpecification httpRequest; 
	 Response response ;
	 String empName=RestUtils.empName();
	 String empSalary=RestUtils.empSal();
	 String empAge=RestUtils.empAge();
	 
	 	 
	@BeforeClass
	void createEmploee() throws InterruptedException {
		
	   logger.info("*************started TC004_PUT__Employee_Record ************");
	   	     RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";        //BaseURL
	   httpRequest=RestAssured.given();                                      //RequestObject
	 		 JSONObject requestParams=new JSONObject();                             //REquestPayload
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		
		     httpRequest.header("Content-Type", "application/json");
		     httpRequest.body(requestParams.toJSONString());
		
		      response=httpRequest.request(Method.PUT,"/update/"+empID);  //Response object
		 Thread.sleep(5000);
			 
	                                                    }
	@Test
	void checkResponseBody(){
		logger.info("*************Checking response body************");
				String responseBody=response.getBody().asString();                     //print response in console
		//logger.info("ResponseBody is:::"+responseBody); 
		Assert.assertEquals(responseBody.contains(empName), true); //validate response body
		Assert.assertEquals(responseBody.contains(empAge), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		
	}
	@Test
	void checkStatusCode(){
		logger.info("*************Checking status code***********");
				int statuscode=response.getStatusCode();                               //validate status code
		logger.info("StatusCode is:::"+statuscode);
		       Assert.assertEquals(statuscode, 200);

	                       }
	
	@Test
	void checkStatusLine(){
		logger.info("*************Checking status line***********");
				String statusline=response.getStatusLine();                               //validate status code
		logger.info("StatusLine is:::"+statusline);
	      	Assert.assertEquals(statusline, "HTTP/1.1 200 OK");

	}
	@Test
	void checkContentencoding(){
		logger.info("*************Checking Contentencodinge***********");
				String contentencode=response.header("Content-Encoding");
		logger.info("contentencoding is:::"+ contentencode);
		        Assert.assertEquals( contentencode, "gzip");
	
	}
	@Test
	void checkContentType(){
		
		logger.info("*************Checking Contentent type***********");
				String contentType=response.header("Content-Type");
		logger.info("contentType is:::"+contentType);
		        Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}
	@Test
	void checkServertype(){
		
		logger.info("*************Checking Server type***********");
				String serverType=response.header("Server");
		logger.info("serverType is:::"+serverType);
	        	Assert.assertEquals(serverType, "nginx/1.16.0");
	                      }
	@Test
	void checkCookies(){
		
		logger.info("*************Checking Server type***********");
				String cookie=response.getCookie("PHPSESSID");
		logger.info("cookieeType is:::"+cookie);
	                   }
	@AfterClass
	void tearDown() {
		logger.info("*************finished TC004_PUT__Employee_Record ************");
		
	}
}
