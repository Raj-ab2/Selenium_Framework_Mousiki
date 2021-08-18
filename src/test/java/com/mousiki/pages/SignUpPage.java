package com.mousiki.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;

public class SignUpPage {
	WebDriver driver;
	By signuplink = By.xpath("//a[contains(.,'Sign Up')]");
	By imparentlink = By.xpath("//a[contains(.,'Parent')]");
	By imteacherlink = By.xpath("//a[contains(.,'Teacher')]");
	By imstudentlink = By.xpath("//a[contains(.,'Student')]");
	By wermusicschoollink = By.xpath("//a[contains(.,'School')]");
	
	By registerbtn = By.xpath("//button[contains(.,'Register')]");
	
	By firstnametxt = By.xpath("//*[@id='firstName']");
	By lastnametxt = By.xpath("//*[@id='lastName']");
	By emailtxt = By.xpath("//*[@id='email']");
	By passwordtxt = By.xpath("//*[@id='password']");
	
	//defining element names
	String signuplinknm = "Sign Up";
	String imparentlinknm = "I'm a Parent";
	String imstudentlinknm = "I'm a Student";
	String imteacherlinknm = "I'm a Teacher";
	String wermusicschoollinknm = "We're Music School";
	
	String registerbtnnm = "Register";
	
	String firstnametxtnm = "First Name";
	String lastnametxtnm = "Last Name";
	String emailtxtnm = "Email";
	String passwordtxtnm = "Password";
	
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getcurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public void enterfirstname(String firstname) {
		TestBase.entertext(driver, firstnametxt, firstname, firstnametxtnm);
	}
	
	public void enterlastname(String lastname) {
		TestBase.entertext(driver, lastnametxt, lastname, lastnametxtnm);
	}
	
	public void enteremailid(String emailid) {
		TestBase.entertext(driver, emailtxt, emailid, emailtxtnm);
	}
	
	public void enterpassword(String password) {
		TestBase.entertext(driver, passwordtxt, password, passwordtxtnm);
	}
	
	public void clicksignup() {
		TestBase.click(driver, signuplink, signuplinknm);
		
		TestBase.waitforelementvisible(driver, 30, imstudentlink);
	}
	
	public void clickregister() {
		TestBase.click(driver, registerbtn, registerbtnnm);
	}
	
	public void clickimparent() {
		TestBase.click(driver, imparentlink, imparentlinknm);
		
		TestBase.waitforelementvisible(driver, 30, firstnametxt);
	}
	
	public void clickimstudent() {
		TestBase.click(driver, imstudentlink, imstudentlinknm);
		
		TestBase.waitforelementvisible(driver, 30, firstnametxt);
	}
	
	public void clickimteacher() {
		TestBase.click(driver, imteacherlink, imteacherlinknm);
		
		TestBase.waitforelementvisible(driver, 30, firstnametxt);
	}
	
	public void clickwearemusicschool() {
		TestBase.click(driver, wermusicschoollink, wermusicschoollinknm);
		
		TestBase.waitforelementvisible(driver, 30, firstnametxt);
	}
}
