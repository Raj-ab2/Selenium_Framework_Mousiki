package com.mousiki.testscripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
	
	static String testngtestname="";
	static String testngtestmethod="";
	
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
		testngtestname = testContext.getName();
	    System.out.println("Test Name - " + testngtestname); // it prints "Check name test"
//	    test = extent.createTest(testContext.getName());
	}
	
	@BeforeMethod
	public void nameBefore(Method method)
	{
		testngtestmethod = method.getName();
	    System.out.println("Test name: " + method.getName());       
	}
	
	@Test(dataProvider="registrationdata")
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
	
	@Test(dataProvider="registrationdata")
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
		test = extent.createTest(testname);
		
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
		hardwait(2000);
		
		//enter registration details
		signuppage.enterfirstname(firstname);
		signuppage.enterlastname(lastname);
		signuppage.enteremailid(emailid);
		signuppage.enterpassword(password);
		
		//Click register button
		signuppage.clickregister();
		
		if(experror.contains("invalid")||experror.contains("Invalid")) {
			if(signuppage.checkinvalidfield(experror)){
				reportlog("Registration not completed for '" + experror + "' invalid data as expected", "PASS", experror);
			}else {
				reportlog("Registration completed for '" + experror + "' invalid data", "FAIL", experror);
			}
		}else if(experror.contains("Password should")) {
			if(signuppage.checkpassworderror(experror)){
				reportlog("Registration not completed for '" + experror + "' invalid data as expected", "PASS", experror);
			}else {
				reportlog("Registration completed for '" + experror + "' invalid data", "FAIL", experror);
			}
		}else if(experror.contains("empty first name")) {
			if(signuppage.checkemptyfirstname()){
				reportlog("Registration not completed for '" + experror + "' invalid data as expected", "PASS", experror);
			}else {
				reportlog("Registration completed for '" + experror + "' invalid data", "FAIL", experror);
			}
		}else if(experror.contains("empty last name")) {
			if(signuppage.checkemptylastname()){
				reportlog("Registration not completed for '" + experror + "' invalid data as expected", "PASS", experror);
			}else {
				reportlog("Registration completed for '" + experror + "' invalid data", "FAIL", experror);
			}
		}else if(experror.contains("empty email")) {
			if(signuppage.checkemptyemail()){
				reportlog("Registration not completed for '" + experror + "' invalid data as expected", "PASS", experror);
			}else {
				reportlog("Registration completed for '" + experror + "' invalid data", "FAIL", experror);
			}
		}else if(experror.contains("empty password")) {
			if(signuppage.checkpassworderror("Password is required")){
				reportlog("Registration not completed for '" + experror + "' invalid data as expected", "PASS", experror);
			}else {
				reportlog("Registration completed for '" + experror + "' invalid data", "FAIL", experror);
			}
		}
		
		//back to home
		signuppage.clicklogoimage();
	}
	
	@AfterTest
	public void backtohomepage() throws IOException {
		System.out.println("after test complete");
	}
	
	@DataProvider(name="registrationdata")
	public Object[][] getregisterinput(Method m) throws Throwable {
		Object data[][] = getExcelData(testngtestname, m.getName());
		return data;
	}
	
	@AfterClass
	public void closebrowser() {
		extent.flush();
//		extent.endTest(test);
		driver.close();
		
	}
	
	public void enterfirstlogindetails(String option, String gender, String phoneno, String dateofbirth, String instrument, String schoolname, String address1, String address2, String city, String pincode, String state) throws Throwable {
		if(option.equalsIgnoreCase("TEACHER")) {
			firstloginpage.selectusertype(option);
			firstloginpage.selectgender(gender);
			firstloginpage.enterphonenumber(phoneno);
			firstloginpage.enterdateofbirth(dateofbirth);
			firstloginpage.uploadprofilepic("C:\\temp.jpg");
			firstloginpage.selectinstrument(instrument);
		}else if(option.equalsIgnoreCase("STUDENT")) {
			firstloginpage.selectusertype(option);
			firstloginpage.selectgender(gender);
			firstloginpage.enterphonenumber(phoneno);
			firstloginpage.enterdateofbirth(dateofbirth);
			firstloginpage.uploadprofilepic("C:\\temp.jpg");
			firstloginpage.selectinstrument(instrument);
		}else if(option.equalsIgnoreCase("PARENT")) {
			firstloginpage.selectusertype(option);
			firstloginpage.selectgender(gender);
			firstloginpage.enterdateofbirth(dateofbirth);
			firstloginpage.enterphonenumber(phoneno);
			firstloginpage.uploadprofilepic("C:\\temp.jpg");
		}else{
			firstloginpage.selectgender(gender);
			firstloginpage.enterphonenumber(phoneno);
			firstloginpage.enterdateofbirth(dateofbirth);
			firstloginpage.uploadprofilepic("C:\\temp.jpg");
			
			firstloginpage.enterschoolname(schoolname);
			firstloginpage.enteraddress1(address1);
			firstloginpage.enteraddress2(address2);
			firstloginpage.entercity(city);
			firstloginpage.enterstate(state);
			firstloginpage.enterpincode(pincode);
			
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
