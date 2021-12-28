package com.mousiki.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;

public class HomePage extends TestBase {
	/*signinpage objects
	 * signinpage objects name
	 * functions on object functionality
	 */
	
	//create webdriver variable
	WebDriver driver;
	
	//signin web elements
	By privacypolicylink = By.xpath("//a[contains(.,'Privacy Policy')]");
	
	//defining element names
	String privacypolicylinknm = "Privacy Policy";
	String loginbtnnm = "Log In";
	String emailtxtnm = "UserName";
	String passwordtxtnm = "Password";
	String confirmtimezonedialogclosenm = "confirm timezone dialog close";
}
