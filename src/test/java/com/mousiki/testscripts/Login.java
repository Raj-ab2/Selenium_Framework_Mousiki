package com.mousiki.testscripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import com.mousiki.dataprovider.TestCaseData;
import com.mousiki.pages.FirstLoginPage;
import com.mousiki.pages.SignInPage;
import com.mousiki.pages.SignUpPage;
import com.mousiki.testbase.BrowserFactory;
import com.mousiki.testbase.TestBase;
//import com.relevantcodes.extentreports.LogStatus;

public class Login extends TestBase{

	SignInPage signinpage;
	
	@BeforeClass
	public void setup() throws IOException {
		//Invoke the browser
//		invoke();
	}
	
	@BeforeTest
	public void startTest(final ITestContext testContext) {
		System.setProperty("testngtestname", testContext.getName());
	    System.out.println("Test Name - " + testContext.getName()); // it prints "Check name test"
//	    test = extent.createTest(testContext.getName());
//	    testsuitename = testContext.getSuite().getName();
	    System.out.println("Suite Name: " + System.getProperty("testphase"));
	}
	
	@BeforeMethod
	public void nameBefore(Method method) throws Throwable
	{
		System.out.println("Before Method");
		
		Random rand = new Random();

		// nextInt as provided by Random is exclusive of the top value so you need to add 1 
		
		int randomNum = generaterandomnumber(1000, 10000);
		Thread.currentThread().sleep(randomNum);
		System.out.println("wait started:" + randomNum);
		
		//Invoke the browser
		invoke();
		
		signinpage = new SignInPage(BrowserFactory.getInstance().getDriver());
		
		System.out.println("current thread:" + Thread.currentThread().getId() + "driver intance" + BrowserFactory.getInstance().getDriver() );
		
		System.out.println("Test name: " + method.getName());       
	}
	
	@Test(dataProviderClass=TestCaseData.class, dataProvider="testdata")
	public void TC_appLogin_positiveflow(Map<String, String> data) throws Throwable {
		
		String testname = data.get("TestName");
		String emailid = data.get("Email");
		String password = data.get("Password");
		
		if(testname==null) {
			return;
		}
		
		extenttestinitialize(testname, "Check Login is working for valid user id and password");
		
		//Click Signin and Login
		app_Login(emailid, password);
		
		if(signinpage.checkconfirmtimezonedialog()) {
			signinpage.closeconfirmtimezonedialog();
		}
		
		if(signinpage.checkhomepage()){
			reportlog("Login completed sucessfully", "PASS", "Login");
		}else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}
		
	}
	
	@Test(dataProviderClass=TestCaseData.class, dataProvider="testdata")
	public void TC_appLogin_InvalidLogin(Map<String, String> data) throws Throwable {
		
		String testname = data.get("TestName");
		String emailid = data.get("Email");
		String password = data.get("Password");
		
		if(testname==null) {
			return;
		}
		
		extenttestinitialize(testname, "Check login error message for Invalid login credentials");
		
		//Click Signin and Login
		app_Login(emailid, password);
		
		if(signinpage.checkloginerrormsg()){
			reportlog("Login shows error message for invalid login credentials as expected", "PASS", "Invalid Login");
		}else {
			reportlog("Login not showing any error for invalid login credentials", "FAIL", "Invalid Login");
		}
		
	}
	
	@Test(dataProviderClass=TestCaseData.class, dataProvider="testdata")
	public void TC_appLogin_blankemail(Map<String, String> data) throws Throwable {
		
		String testname = data.get("TestName");
		String emailid = data.get("Email");
		String password = data.get("Password");
		
		if(testname==null) {
			return;
		}
		
		extenttestinitialize(testname, "Check error messages for blank login credentials");
		
		//Click Signin and Login
		app_Login(emailid, password);
		
		if(signinpage.checkblankemailerrormsg()){
			reportlog("Login shows error message for blank email credentials as expected", "PASS", "Blank email");
		}else {
			reportlog("Login not showing any error for blank email credentials", "FAIL", "Blank email");
		}
		
	}
	
	@Test(dataProviderClass=TestCaseData.class, dataProvider="testdata")
	public void TC_appLogin_blankpassword(Map<String, String> data) throws Throwable {
		
		String testname = data.get("TestName");
		String emailid = data.get("Email");
		String password = data.get("Password");
		
		if(testname==null) {
			return;
		}
		
		extenttestinitialize(testname, "Check error message for blank login password");
		
		//Click Signin and Login
		app_Login(emailid, password);
		
		if(signinpage.checkblankpassworderrormsg()){
			reportlog("Login shows error message for blank password credentials as expected", "PASS", "Blank password");
		}else {
			reportlog("Login not showing any error for blank password credentials", "FAIL", "Blank password");
		}
		
	}
	
	@Test(dataProviderClass=TestCaseData.class, dataProvider="testdata")
	public void TC_appLogin_multiplewrongpassword(Map<String, String> data) throws Throwable {
		
		String testname = data.get("TestName");
		String emailid = data.get("Email");
		String password = data.get("Password");
		
		if(testname==null) {
			return;
		}
		
		extenttestinitialize(testname, "Check multiple times error login attempts");
		
		//click signin link
		signinpage.clicksigninlink();
		
		//check signin URL
		String signinurl = signinpage.getcurrentURL();
		if(signinurl.equalsIgnoreCase(signinpage.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		}else {
			reportlog("failed to navigate sign in page. expected url is not matching:" + signinpage.getexpectedsigninurl(), "FAIL", "Signin navigation");
		}
		
//		Enter email id, password and click login button
		signinpage.enterusername(emailid);		
		signinpage.enterpassword(password);
		for(int counter = 0;counter < 6;counter++) {
			signinpage.clickloginbutton();
		}
		
		if(signinpage.checkloginlockerrormsg()){
			reportlog("Login shows error message for multiple wrong password credentials as expected", "PASS", "multiple wrong password");
		}else {
			reportlog("Login not showing any error for multiple wrong password credentials", "FAIL", "multiple wrong password");
		}
		
	}
	
	@AfterMethod
	public void closebrowser() {
		System.out.println("After method");
//		extent.flush();
//		extent.endTest(test);
		BrowserFactory.getInstance().removeDriver();
		
	}
	
	@AfterTest
	public void backtohomepage() throws IOException {
		System.out.println("after test complete");
	}
	
	@AfterClass
	public void extentflush() {
		extent.flush();
//		extent.endTest(test);
//		driver.close();
		
	}
	
}
