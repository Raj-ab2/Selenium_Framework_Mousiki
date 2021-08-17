package com.mousiki.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.*;

import com.mousiki.pages.SignInPage;
import com.mousiki.testbase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class Login extends TestBase{

	SignInPage signinpage;
	
	@BeforeClass
	public void setup() throws IOException {
		//Invoke the browser
		invoke();
		signinpage = new SignInPage(driver);
	}
	
	@Test
	public void AppLogin() throws InterruptedException {
		test = extent.startTest("TC01_test");
		System.out.println("Successfully launched browser");
		
		signinpage.clicksigninlink();
		Thread.sleep(10000);
		
		String title = signinpage.getTitle();
		Assert.assertEquals(title, "Mousiki.io - Collaboration App for Music Learning Community.");
		
		signinpage.enterusername("TestUser");
		
		signinpage.enterpassword("TestPassword");
		
		signinpage.clickloginbutton();
		
	}
	
	@AfterClass
	public void closebrowser() {
		extent.flush();
		extent.endTest(test);
		driver.close();
	}
	
}
