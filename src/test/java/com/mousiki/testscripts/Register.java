package com.mousiki.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mousiki.pages.SignUpPage;
import com.mousiki.testbase.TestBase;

public class Register extends TestBase {
	SignUpPage signuppage;
	
	@BeforeClass
	public void setup() throws IOException {
		//Invoke the browser
		invoke();
		signuppage = new SignUpPage(driver);
	}
	
	@Test
	@Parameters ({"registeroption", "firstname", "lastname", "emailid", "password"})
	public void AppLogin(String registeroption, String firstname, String lastname, String emailid, String password) throws InterruptedException {
		test = extent.startTest("TC02_Register");
		System.out.println("Successfully launched browser");
		
		signuppage.clicksignup();
		
		if(registeroption.contains("parent")) {
			  signuppage.clickimparent();
		}else if(registeroption.contains("student")) {
		    signuppage.clickimstudent();
		}else if(registeroption.contains("teacher")) {
			  signuppage.clickimteacher();
		}else if(registeroption.contains("musicschool")) {
		    signuppage.clickwearemusicschool();
		}
				
		signuppage.enterfirstname(firstname);
		signuppage.enterlastname(lastname);
		signuppage.enteremailid(emailid);
		signuppage.enterpassword(password);
		
		signuppage.clickregister();
	}
	
	@AfterClass
	public void closebrowser() {
		extent.flush();
		extent.endTest(test);
		driver.close();
	}
	
}
