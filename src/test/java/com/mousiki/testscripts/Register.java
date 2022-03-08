package com.mousiki.testscripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.mousiki.dataprovider.TestCaseData;
import com.mousiki.pages.FirstLoginPage;
import com.mousiki.pages.SignInPage;
import com.mousiki.pages.SignUpPage;
import com.mousiki.testbase.BrowserFactory;
import com.mousiki.testbase.TestBase;

//import oracle.jrockit.jfr.events.DynamicValueDescriptor;

public class Register extends TestBase {
	
	SignUpPage signuppage;
	SignInPage signinpage;
	FirstLoginPage firstloginpage;
	
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
		
		signuppage = new SignUpPage(BrowserFactory.getInstance().getDriver());
		signinpage = new SignInPage(BrowserFactory.getInstance().getDriver());
		firstloginpage = new FirstLoginPage(BrowserFactory.getInstance().getDriver());
		
		System.out.println("current thread:" + Thread.currentThread().getId() + "driver intance" + BrowserFactory.getInstance().getDriver() );
		
		System.out.println("Test name: " + method.getName());       
	}
	
	@Test(dataProviderClass=TestCaseData.class, dataProvider="testdata")
	public void TC_appregistration_positiveflow(Map<String, String> data) throws Throwable {
	    
		String testname = data.get("TestName");
		String firstname = data.get("FirstName");
		String lastname = data.get("LastName");
		String emailid = data.get("Email");
		String password = data.get("Password");
		String registeroption = data.get("Option");
		
		if(testname==null) {
			return;
		}
		
		extenttestinitialize(testname, "Create a new User using registration - " + registeroption);
		
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
		hardwait(3000);
		reportlog("Registration Details entered sucessfully", "PASS", "Registration");	
		
		//Click register button
		signuppage.clickregister();
		
		if(signuppage.checkforregistercompletion()){
			reportlog("Registration completed sucessfully", "PASS", "Registration");			
		}else {
			reportlog("Registration Failed due to error", "FAIL", "Registration");
		}
		hardwait(1000);
		
		boolean continueexe = false;

		//get confirmation link from DB
		String query = "Select verifi_link from user where email = '" + emailid + "'";
		System.out.println("query-"+query);
		String dbdata[][] = getDBValues(query);
		if(dbdata==null) {
			reportlog("Registration verification link not available", "FAIL", "Registration verification");
			
		}else {
			System.out.println("Verification Link: " + dbdata[0][0]); 
			continueexe = true;
		}
		
		//continue execution if verification link exists
		if(continueexe) {
			//confirm registration
			signuppage.confirmregistration(dbdata[0][0]);
			
			if(signuppage.checkforregisterconfirmation()){
				reportlog("Registration confirmated sucessfully, Username:" + emailid + " password:" + password, "PASS", "Registration confirmation");
				
			}else {
				reportlog("Registration confirmation Failed due to error", "FAIL", "Registration confirmation");
			}
			
			//click login after confirmation
			signuppage.clickloginafterregister();
			hardwait(3000);
			
			//enter Login and password
			signinpage.enterusername(emailid);
			signinpage.enterpassword(password);
			signinpage.clickloginbutton();
			
			firstloginpage.clickconfirmbutton();
			firstloginpage.clickokbutton();
			
			reportlog("Login completed sucessfully", "PASS", "Login");
			
			enterfirstlogindetails(data.get("Option"), data.get("Gender"),	data.get("PhoneNumber"), data.get("DOB"), data.get("Instrument"), data.get("SchoolName"), data.get("AddressLine1"), data.get("AddressLine2"), data.get("City"), data.get("PostalCode"), data.get("State"));
			
			reportlog("firstLogin completed sucessfully", "INFO");
			
			//signout
			firstloginpage.clickprofileiconbutton();
			firstloginpage.clicklogoutlink();
		}
	}
	
	@Test(dataProviderClass=TestCaseData.class, dataProvider="testdata")
	public void TC_appregistration_negativeflow(Map<String, String> data) throws Throwable {
				
		String testname = data.get("TestName");
		String firstname = data.get("FirstName");
		String lastname = data.get("LastName");
		String emailid = data.get("Email");
		String password = data.get("Password");
		String registeroption = data.get("Option");
		String experror = data.get("ExpectedError");

		if(testname==null) {
			return;
		}
		extenttestinitialize(testname, "Negative validation on Registration field for " + registeroption + ", Check expected error - " + experror);
		
		if(emailid.isEmpty()) {
			emailid = getAlphaNumericString(10) + "@gmail.com";
		}else if(emailid.equalsIgnoreCase("(EMPTY)")){
			emailid = "";
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
		System.out.println("active thread:" + Thread.currentThread().getId());
		
		//enter registration details
		signuppage.enterfirstname(firstname);
		signuppage.enterlastname(lastname);
		signuppage.enteremailid(emailid);
		signuppage.enterpassword(password);
		hardwait(3000);
		reportlog("Registration Details entered sucessfully", "PASS", "Registration");	
		
		//Click register button
		signuppage.clickregister();
		
		if(experror.contains("invalid")||experror.contains("Invalid")) {
			if(signuppage.checkinvalidfield(experror)){
				reportlog("Expected Error message ('" + experror + "') is shown for invalid data as expected", "PASS", experror);
			}else {
				reportlog("Expected Error message ('" + experror + "') is not shown for invalid data", "FAIL", experror);
			}
		}else if(experror.contains("Password should")) {
			if(signuppage.checkpassworderror(experror)){
				reportlog("Expected Error message ('" + experror + "') is shown for invalid data as expected", "PASS", experror);
			}else {
				reportlog("Expected Error message ('" + experror + "') is not shown for invalid data", "FAIL", experror);
			}
		}else if(experror.contains("empty first name")) {
			if(signuppage.checkemptyfirstname()){
				reportlog("Expected Error message ('" + experror + "') is shown for invalid data as expected", "PASS", experror);
			}else {
				reportlog("Expected Error message ('" + experror + "') is not shown for invalid data", "FAIL", experror);
			}
		}else if(experror.contains("empty last name")) {
			if(signuppage.checkemptylastname()){
				reportlog("Expected Error message ('" + experror + "') is shown for invalid data as expected", "PASS", experror);
			}else {
				reportlog("Expected Error message ('" + experror + "') is not shown for invalid data", "FAIL", experror);
			}
		}else if(experror.contains("empty email")) {
			if(signuppage.checkemptyemail()){
				reportlog("Expected Error message ('" + experror + "') is shown for invalid data as expected", "PASS", experror);
			}else {
				reportlog("Expected Error message ('" + experror + "') is not shown for invalid data", "FAIL", experror);
			}
		}else if(experror.contains("empty password")) {
			if(signuppage.checkpassworderror("Password is required")){
				reportlog("Expected Error message ('" + experror + "') is shown for invalid data as expected", "PASS", experror);
			}else {
				reportlog("Expected Error message ('" + experror + "') is not shown for invalid data", "FAIL", experror);
			}
		}
		
		//back to home
		signuppage.clicklogoimage();
		
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
	
	public void enterfirstlogindetails(String option, String gender, String phoneno, String dateofbirth, String instrument, String schoolname, String address1, String address2, String city, String pincode, String state) throws Throwable {
		if(option.equalsIgnoreCase("TEACHER")) {
			firstloginpage.selectusertype(option);
			firstloginpage.selectgender(gender);
			firstloginpage.enterphonenumber(phoneno);
			firstloginpage.enterdateofbirth(dateofbirth);
//			firstloginpage.uploadprofilepic("C:\\temp.jpg");
			firstloginpage.selectinstrument(instrument);
		}else if(option.equalsIgnoreCase("STUDENT")) {
			firstloginpage.selectusertype(option);
			firstloginpage.selectgender(gender);
			firstloginpage.enterphonenumber(phoneno);
			firstloginpage.enterdateofbirth(dateofbirth);
//			firstloginpage.uploadprofilepic("C:\\temp.jpg");
			firstloginpage.selectinstrument(instrument);
		}else if(option.equalsIgnoreCase("PARENT")) {
			firstloginpage.selectusertype(option);
			firstloginpage.selectgender(gender);
			firstloginpage.enterdateofbirth(dateofbirth);
			firstloginpage.enterphonenumber(phoneno);
//			firstloginpage.uploadprofilepic("C:\\temp.jpg");
		}else{
			firstloginpage.selectgender(gender);
			firstloginpage.enterphonenumber(phoneno);
			firstloginpage.enterdateofbirth(dateofbirth);
//			firstloginpage.uploadprofilepic("C:\\temp.jpg");
			
			firstloginpage.enterschoolname(schoolname);
			firstloginpage.enteraddress1(address1);
			firstloginpage.enteraddress2(address2);
			firstloginpage.entercity(city);
			firstloginpage.enterstate(state);
			firstloginpage.enterpincode(pincode);
			System.out.println("school details updated");
		}
		firstloginpage.clickenablecheckbox();
		firstloginpage.clickokbutton();
		firstloginpage.clickacceptcheckbox();
		firstloginpage.clicksubmitbutton();
		
		if(firstloginpage.checkhomepage()){
			reportlog("First login updated sucessfully", "PASS", "First login confirmation");
			
		}else {
			reportlog("First login updation Failed due to error", "FAIL", "First login confirmation");
		}
	}
}
