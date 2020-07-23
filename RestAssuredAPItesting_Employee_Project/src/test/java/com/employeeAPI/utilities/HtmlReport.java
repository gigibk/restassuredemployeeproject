package com.employeeAPI.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
	 public class HtmlReport extends TestListenerAdapter {
		public ExtentHtmlReporter htmlReporter;
	    public ExtentReports extent;
		public ExtentTest test;
		
		
		@Override
		public void onStart(ITestContext context) {
			String dateName=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());			
htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/Reports/"+"HTMLReport"+dateName+".html");
  
              htmlReporter.config().setDocumentTitle("AutomationReport");		
              htmlReporter.config().setReportName("RESTASSUREDAPITest Report");
              htmlReporter.config().setTheme(Theme.DARK);
extent=new ExtentReports();
extent.attachReporter(htmlReporter);
htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
			extent.setSystemInfo("HOST NAME","localhost");
		    extent.setSystemInfo("Environment","QA");
		    extent.setSystemInfo("USER","GigiBabuKoshy");
		    extent.setSystemInfo("BROWSER","CHROME");
		 	          
		}
			
		@Override
		public void onTestStart(ITestResult result) {
			
			System.out.println("Testcases started-----"+result.getName());
		}
		


		@Override
		public void onTestSuccess(ITestResult result) {
			test=extent.createTest(result.getName());
			System.out.println("Testcases passed------"+result.getName());
			if(result.getStatus() == ITestResult.SUCCESS) {
	        	test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
	        }
			//driver.quit();
		}

		@Override
		public void onTestFailure(ITestResult result) {
			test=extent.createTest(result.getName());
	        if(result.getStatus() == ITestResult.FAILURE) {
	        	test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
	        	test.fail(result.getThrowable());
	        	
	        	String screenshotPath=System.getProperty("user.dir")+"\\ScreenShot\\"+result.getName()+".png";
	        	File f=new File(screenshotPath);
	        	if(f.exists()) {
	        		
	        		try {
	    				test.fail("screenshot"+ test.addScreenCaptureFromPath(screenshotPath));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}          					
	       }
			
		}
	     
			@Override
		public void onTestSkipped(ITestResult result) {
			// TODO Auto-generated method stub
			test=extent.createTest(result.getName());
			  if(result.getStatus() == ITestResult.SKIP)
			{
	        	test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
	        	test.skip(result.getThrowable());
	        }
	             
		}
	    
		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			
			
		}
       
		@Override
		public void onFinish(ITestContext context) {
			extent.flush();
		}
		
}
