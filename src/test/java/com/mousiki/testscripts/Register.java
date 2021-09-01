package com.mousiki.testscripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mousiki.pages.FirstLoginPage;
import com.mousiki.pages.SignInPage;
import com.mousiki.pages.SignUpPage;
import com.mousiki.testbase.TestBase;

public class Register extends TestBase {
	SignUpPage signuppage;
	SignInPage signinpage;
	FirstLoginPage firstloginpage;
	
	@BeforeClass
	public void setup() throws IOException {
		//Invoke the browser
		invoke();
		signuppage = new SignUpPage(driver);
		signinpage = new SignInPage(driver);
		firstloginpage = new FirstLoginPage(driver);
	}
	
	@BeforeTest
	public void startTest(final ITestContext testContext) throws IOException {
	    System.out.println("Test Name - " + testContext.getName()); // it prints "Check name test"
//	    test = extent.createTest(testContext.getName());
	}
	
	@Test(dataProvider="registrationdata")
	public void appregistration(String testname, String firstname, String lastname, String emailid, String password, String registeroption) throws Throwable {
		
		test = extent.createTest(testname);
		
		if(emailid.isEmpty()) {
			emailid = getAlphaNumericString(10) + "@gmail.com";
		}
		//click signup page
		signuppage.clicksignup();
		
		//check registration option
		if(registeroption.equalsIgnoreCase("parent")) {
			  signuppage.clickimparent();
		}else if(registeroption.equalsIgnoreCase("student")) {
		    signuppage.clickimstudent();
		}else if(registeroption.equalsIgnoreCase("teacher")) {
			  signuppage.clickimteacher();
		}else{
		    signuppage.clickwearemusicschool();
		}
		
		//enter registration details
		signuppage.enterfirstname(firstname);
		signuppage.enterlastname(lastname);
		signuppage.enteremailid(emailid);
		signuppage.enterpassword(password);
		
		//Click register button
		signuppage.clickregister();
		
		if(signuppage.checkforregistercompletion()){
			reportlog("Registration completed sucessfully", "PASS", "Registration");			
		}else {
			reportlog("Registration Failed due to error", "FAIL", "Registration");
		}
		
		boolean continueexe = false;

		//get confirmation link from DB
		String query = "Select verifi_link from user where email = '" + emailid + "'";
		String data[][] = getDBValues(query);
		if(data==null) {
			reportlog("Registration verification link not available", "FAIL", "Registration verification");
			
		}else {
			System.out.println("Verification Link: " + data[0][0]);
			continueexe = true;
		}
		
		//continue execution if verification link exists
		if(continueexe) {
			//confirm registration
			signuppage.confirmregistration(data[0][0]);
			
			if(signuppage.checkforregisterconfirmation()){
				reportlog("Registration confirmated sucessfully, Username:" + emailid + " password:" + password, "PASS", "Registration confirmation");
				
			}else {
				reportlog("Registration confirmation Failed due to error", "FAIL", "Registration confirmation");
			}
			
			//click login after confirmation
			signuppage.clickloginafterregister();
			
			//enter Login and password
			signinpage.enterusername(emailid);
			signinpage.enterpassword(password);
			signinpage.clickloginbutton();
			
			firstloginpage.clickconfirmbutton();
			firstloginpage.clickokbutton();
			
			reportlog("Login completed sucessfully", "PASS", "Login");
			
			//signout
			firstloginpage.clickprofileiconbutton();
			firstloginpage.clicklogoutlink();
		}
	}
	
	@AfterTest
	public void backtohomepage() throws IOException {
		System.out.println("after test complete");
	}
	
	@DataProvider(name="registrationdata")
	public Object[][] getregisterinput() throws IOException {
		
		//create data object
		Object data[][] = getexcelinput();
		return data;
	}
	
	@AfterClass
	public void closebrowser() {
		extent.flush();
//		extent.endTest(test);
		driver.close();
		
	}
	
}
