package com.mousiki.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;

public class SignUpPage {
	/*signup page webelements
	 * signup page webelement names
	 * functionality on webelements
	 * */
	
	//declare webdriver variable
	WebDriver driver;
	
	//signup webelements
	By signuplink = By.xpath("//ul/li[1]/a[contains(.,'Sign')]");
	By imparentlink = By.xpath("//a[contains(.,'Parent')]");
	By imteacherlink = By.xpath("//a[contains(.,'Teacher')]");
	By imstudentlink = By.xpath("//a[contains(.,'Student')]");
	By wermusicschoollink = By.xpath("//a[contains(.,'School')]");
	By confirmregloginlink = By.xpath("//a[contains(.,'login')]");
	
	By registerbtn = By.xpath("//button[contains(.,'Register')]");
	
	By registercompletionmsg = By.xpath("//h2[contains(.,'Registration Completed')]");
	By registerconfirmmsg = By.xpath("//p[contains(.,'Your account has been verified successfully, click here to')]");
	
	
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
	String confirmregloginlinknm = "Login after register confirmation";
	
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
	
	public void confirmregistration(String verifi_link) {
		driver.get("https://qa.mousiki.io/VerifyUser?token=" + verifi_link);
	}
	
	public void enterfirstname(String firstname) throws Throwable {
		TestBase.entertext(driver, firstnametxt, firstname, firstnametxtnm);
	}
	
	public void enterlastname(String lastname) throws Throwable {
		TestBase.entertext(driver, lastnametxt, lastname, lastnametxtnm);
	}
	
	public void enteremailid(String emailid) throws Throwable {
		TestBase.entertext(driver, emailtxt, emailid, emailtxtnm);
	}
	
	public void enterpassword(String password) throws Throwable {
		TestBase.entertext(driver, passwordtxt, password, passwordtxtnm);
		
		TestBase.waitforelementvisible(driver, 30, registerbtn);
	}
	
	public void clicksignup() throws Throwable {
		TestBase.click(driver, signuplink, signuplinknm);
		
		TestBase.waitforelementvisible(driver, 30, imstudentlink);
	}
	
	public void clickregister() throws Throwable {
		TestBase.click(driver, registerbtn, registerbtnnm);
	}
	
	public void clickloginafterregister() throws Throwable {
		TestBase.click(driver, confirmregloginlink, confirmregloginlinknm);
	}
	
	public void clickimparent() throws Throwable {
		TestBase.click(driver, imparentlink, imparentlinknm);
		
		TestBase.waitforelementvisible(driver, 30, firstnametxt);
	}
	
	public void clickimstudent() throws Throwable {
		TestBase.click(driver, imstudentlink, imstudentlinknm);
		
		TestBase.waitforelementvisible(driver, 30, firstnametxt);
	}
	
	public void clickimteacher() throws Throwable {
		TestBase.click(driver, imteacherlink, imteacherlinknm);
		
		TestBase.waitforelementvisible(driver, 30, firstnametxt);
	}
	
	public void clickwearemusicschool() throws Throwable {
		TestBase.click(driver, wermusicschoollink, wermusicschoollinknm);
		
		TestBase.waitforelementvisible(driver, 30, firstnametxt);
	}
	
	public boolean checkforregistercompletion() throws Throwable {
				
		return TestBase.checkelementexists(driver, 30, registercompletionmsg);
	}
	
	public boolean checkforregisterconfirmation() throws Throwable {
		
		return TestBase.checkelementexists(driver, 30, confirmregloginlink);
	}
}
