package com.mousiki.testscripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mousiki.dataprovider.TestCaseData;
import com.mousiki.pages.FirstLoginPage;
import com.mousiki.pages.HomePage;
import com.mousiki.pages.MyAccountPage;
import com.mousiki.pages.SignInPage;
import com.mousiki.pages.SignUpPage;
import com.mousiki.testbase.BrowserFactory;
import com.mousiki.testbase.TestBase;

public class MyAccount extends TestBase {
	
	SignInPage signinpage;
	HomePage homepage;
	MyAccountPage myaccountpage;
	
	@BeforeClass
	public void setup() throws IOException {
		System.out.println("Before Class");
	}
	
	@BeforeTest
	public void startTest(final ITestContext testContext) throws IOException {
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
		homepage = new HomePage(BrowserFactory.getInstance().getDriver());
		myaccountpage = new MyAccountPage(BrowserFactory.getInstance().getDriver());
		
		System.out.println("current thread:" + Thread.currentThread().getId() + "driver intance" + BrowserFactory.getInstance().getDriver() );
		
		System.out.println("Test name: " + method.getName());       
	}
	
	@Test(dataProviderClass=TestCaseData.class, dataProvider="testdata")
	public void TC_appchangepassword_negativeflow(Map<String, String> data) throws Throwable {
		String testname = data.get("TestName");
		String emailid = data.get("Email");
		String password = data.get("Password");
		String newpassword = data.get("NewPassword");
		String experrormsg = data.get("Expected_Error");
		
		if(testname==null) {
			return;
		}
		
		extenttestinitialize(testname, "Negative validation on Change password field, Check expected error - " + experrormsg);
		
		//Login application
		app_Login(emailid, password);
		
		if(homepage.checkhamburgericon()) {
			reportlog("Application logged in successfully", "PASS", "Login");
		}else {
			reportlog("Application failed logged in", "FAIL", "Login");
		}
		
		//click hanburger icon
		homepage.clickhamburgericon();
		
		app_leftnavigation("My Account;Change Password");
		
		if(myaccountpage.getcurrentURL().equalsIgnoreCase("https://qa.mousiki.io/User/ChangePassword")) {
			reportlog("application navigated to change password page successfully", "INFO");
		}else {
			reportlog("application navigation failed", "FAIL");
		}
		hardwait(3000);
		
		myaccountpage.enteroldpassword(password);
		myaccountpage.enternewpassword(newpassword);
		myaccountpage.clicksubmitbutton();
		
		if(myaccountpage.checkpassworderror(experrormsg)) {
			reportlog("Expeted error(" + experrormsg + ") shown as expected", "PASS", "Change password validation");
		}else {
			reportlog("Expected error(" + experrormsg + ") not shown", "FAIL", "Change password validation");
		}
	}
	
	@Test(dataProviderClass=TestCaseData.class, dataProvider="testdata")
	public void TC_appchangepassword_erroroldpasswordflow(Map<String, String> data) throws Throwable {
		String testname = data.get("TestName");
		String emailid = data.get("Email");
		String password = data.get("Password");
		String newpassword = data.get("NewPassword");
		String experrormsg = data.get("Expected_Error");
		
		if(testname==null) {
			return;
		}
		
		extenttestinitialize(testname, "Negative validation on Change password field, Check expected error - " + experrormsg);
		
		//Login application
		app_Login(emailid, password);
		
		if(homepage.checkhamburgericon()) {
			reportlog("Application logged in successfully", "PASS", "Login");
		}else {
			reportlog("Application failed logged in", "FAIL", "Login");
		}
		
		//click hanburger icon
		homepage.clickhamburgericon();
		
		app_leftnavigation("My Account;Change Password");
		
		if(myaccountpage.getcurrentURL().equalsIgnoreCase("https://qa.mousiki.io/User/ChangePassword")) {
			reportlog("application navigated to change password page successfully", "INFO");
		}else {
			reportlog("application navigation failed", "FAIL");
		}
		hardwait(3000);
		
		myaccountpage.enteroldpassword("gfdhgdhd");
		myaccountpage.enternewpassword(newpassword);
		myaccountpage.clicksubmitbutton();
		
		if(myaccountpage.checkcustompassworderror(experrormsg)) {
			reportlog("Expeted error(" + experrormsg + ") shown as expected", "PASS", "Change password validation");
		}else {
			reportlog("Expected error(" + experrormsg + ") not shown", "FAIL", "Change password validation");
		}
	}
	
	@Test(dataProviderClass=TestCaseData.class, dataProvider="testdata")
	public void TC_appchangepassword_samenegativeflow(Map<String, String> data) throws Throwable {
		String testname = data.get("TestName");
		String emailid = data.get("Email");
		String password = data.get("Password");
		String experrormsg = data.get("Expected_Error");
		
		if(testname==null) {
			return;
		}
		
		extenttestinitialize(testname, "Negative validation on Change password field, Check expected error - " + experrormsg);
		
		//Login application
		app_Login(emailid, password);
		
		if(homepage.checkhamburgericon()) {
			reportlog("Application logged in successfully", "PASS", "Login");
		}else {
			reportlog("Application failed logged in", "FAIL", "Login");
		}
		
		//click hanburger icon
		homepage.clickhamburgericon();
		
		app_leftnavigation("My Account;Change Password");
		
		if(myaccountpage.getcurrentURL().equalsIgnoreCase("https://qa.mousiki.io/User/ChangePassword")) {
			reportlog("application navigated to change password page successfully", "INFO");
		}else {
			reportlog("application navigation failed", "FAIL");
		}
		hardwait(3000);
		
		myaccountpage.enteroldpassword(password);
		myaccountpage.enternewpassword(password);
		myaccountpage.clicksubmitbutton();
		
		if(myaccountpage.checkpassworderror(experrormsg)) {
			reportlog("Expeted error(" + experrormsg + ") shown as expected", "PASS", "Change password validation");
		}else {
			reportlog("Expected error(" + experrormsg + ") not shown", "FAIL", "Change password validation");
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
