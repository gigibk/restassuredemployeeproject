package com.employeeAPI.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GET_All_Employees extends TestBase {
	@BeforeClass
	void getAllEmploees() throws InterruptedException {
		
		logger.info("*************started TC001_GET_All_Employees************");
	    RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";//BaseURL
    	httpRequest=RestAssured.given();                  //RequestObject
	    response=httpRequest.request(Method.GET,"/employees");       //Response object
	
	    Thread.sleep(3000);
	 		}
	@Test
	void checkResponseBody(){
		
		logger.info("*************Checking response body************");
				String responseBody=response.getBody().asString();                     //print response in console
		logger.info("ResponseBody is:::"+responseBody); 
	        	Assert.assertTrue(responseBody!=null); //validate response body
	                        }
	@Test
	void checkStatusCode(){
		
		logger.info("*************Checking status code***********");
		int statuscode=response.getStatusCode();                               //validate status code
		logger.info("StatusCode is:::"+statuscode);
		Assert.assertEquals(statuscode, 200);

	                      }
	@Test
	void checkResponseTime(){
		
		logger.info("*************Checking response time***********");
		long responsetime=response.getTime();                               //validate status code
		logger.info("Responsetime is:::"+responsetime);
		if(responsetime>2000)
			logger.warn("responsetime>2000");
		Assert.assertTrue(responsetime<10000);
	
                        	}
	@Test
	void checkStatusLine(){
		
		logger.info("*************Checking status line***********");
			String statusline=response.getStatusLine();                               //validate status code
		logger.info("StatusCode is:::"+statusline);
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
	void checkServertype(){
		
		logger.info("*************Checking Server type***********");
				String serverType=response.header("Server");
		logger.info("contentType is:::"+serverType);
		      Assert.assertEquals(serverType, "nginx/1.16.0");
	                       }
	void checkCookies(){
		logger.info("*************Checking Server type***********");
				String cookie=response.getCookie("PHPSESSID");
		logger.info("contentType is:::"+cookie);
		
	                   }
	@AfterClass
	void tearDown() {
		logger.info("*************Finished TC001_GET_All_Employees************");
		
	                }
}
