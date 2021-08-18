package com.mousiki.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;

public class SignInPage {
	WebDriver driver;
	
	By signinlink = By.xpath("//a[contains(.,'Sign In')]");
	By emailtxt = By.xpath("//*[@id='username']");
	By passwordtxt = By.xpath("//*[@id='password']");
	By loginbtn = By.xpath("//*[@type='submit']");
	
	//defining element names
	String signinlinknm = "Sign In";
	String loginbtnnm = "Log In";
	String emailtxtnm = "UserName";
	String passwordtxtnm = "Password";
	
	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getcurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public void enterusername(String username) {
		TestBase.entertext(driver, emailtxt, username, emailtxtnm);
	}
	
	public void enterpassword(String password) {
		TestBase.entertext(driver, passwordtxt, password, passwordtxtnm);
	}
	
	public void clickloginbutton() {
		TestBase.click(driver, loginbtn, loginbtnnm);
	}
	
	public void clicksigninlink() {
		TestBase.click(driver, signinlink, signinlinknm);
		
		TestBase.waitforelementvisible(driver, 30, emailtxt);
	}
}
