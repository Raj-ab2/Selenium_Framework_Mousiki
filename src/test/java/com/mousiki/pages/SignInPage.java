package com.mousiki.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;

public class SignInPage {
	/*signinpage objects
	 * signinpage objects name
	 * functions on object functionality
	 */
	
	//create webdriver variable
	WebDriver driver;
	
	//signin web elements
	By signinlink = By.xpath("//a[contains(.,'Sign In')]");
	By emailtxt = By.xpath("//*[@id='username']");
	By passwordtxt = By.xpath("//*[@id='password']");
	By loginbtn = By.xpath("//*[@type='submit']");
	
	By confirmtimezonedialog = By.xpath("//h4[contains(.,'Confirm TimeZone')]");
	By confirmtimezonedialogclose = By.xpath("//h4[contains(.,'Confirm TimeZone')]/following-sibling::button[1]");
	
	//defining element names
	String signinlinknm = "Sign In";
	String loginbtnnm = "Log In";
	String emailtxtnm = "UserName";
	String passwordtxtnm = "Password";
	String confirmtimezonedialogclosenm = "confirm timezone dialog close";
	
	String signinurlexpected = "https://qa.mousiki.io/SignIn";
	
	
	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getcurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public String getexpectedsigninurl() {
		return signinurlexpected;
	}
	public void enterusername(String username) throws IOException {
		TestBase.entertext(driver, emailtxt, username, emailtxtnm);
	}
	
	public void enterpassword(String password) throws IOException {
		TestBase.entertext(driver, passwordtxt, password, passwordtxtnm);
	}
	
	public void clickloginbutton() throws IOException {
		TestBase.click(driver, loginbtn, loginbtnnm);
	}
	
	public void clicksigninlink() throws IOException {
		TestBase.click(driver, signinlink, signinlinknm);
		
		TestBase.waitforelementvisible(driver, 30, emailtxt);
	}
	
	public boolean checkconfirmtimezonedialog() throws IOException {
		return TestBase.checkelementexists(driver, 30, confirmtimezonedialog);
	}
	
	public void closeconfirmtimezonedialog() throws IOException {
		TestBase.click(driver, confirmtimezonedialogclose, confirmtimezonedialogclosenm);
	}
	
}
