package com.mousiki.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import com.mousiki.pages.SignInPage;
import com.mousiki.testbase.TestBase;
//import com.relevantcodes.extentreports.LogStatus;

public class Login extends TestBase{

	SignInPage signinpage;
	
	@BeforeClass
	public void setup() throws IOException {
		//Invoke the browser
		invoke();
		signinpage = new SignInPage(driver);
	}
	
	@BeforeTest
	public void startTest(final ITestContext testContext) {
	    System.out.println("Test Name - " + testContext.getName()); // it prints "Check name test"
	    test = extent.createTest(testContext.getName());
	}
	
	@Test
	@Parameters ({"emailid", "password"})
	public void AppLogin(String emailid, String password) throws Throwable {
		
		
		//click signin link
		signinpage.clicksigninlink();
		
		//check signin URL
		String signinurl = signinpage.getcurrentURL();
		if(signinurl.equalsIgnoreCase(signinpage.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		}else {
			reportlog("failed to navigate sign in page. expected url is not matching:" + signinpage.getexpectedsigninurl(), "FAIL", "Signin navigation");
		}
		
		//Enter email id, password and click login button
		signinpage.enterusername(emailid);		
		signinpage.enterpassword(password);
		signinpage.clickloginbutton();
		
		//handle confirm timezone dialog
		if(signinpage.checkconfirmtimezonedialog()) {
			signinpage.closeconfirmtimezonedialog();
		}
		
		reportlog("Login completed sucessfully", "PASS", "Login");
	}
	
	@AfterClass
	public void closebrowser() {
		extent.flush();
//		extent.endTest(test);
		driver.close();
	}
	
}
