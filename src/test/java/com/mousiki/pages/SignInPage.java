package com.mousiki.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;


public class SignInPage extends TestBase {
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
	
	By loginerrormsg = By.xpath("//div[@class='custom-error-message']");
	By loginlockerrormsg = By.xpath("//div[@class='custom-error-message' and contains(.,'Too many failed attempt to login. Please wait for few minutes before retrying.')]");
	By blankemailerrormsg = By.xpath("//div[contains(.,'Please provide a valid email') and @class='input-feedback']");
	By blankpassworderrormsg = By.xpath("//div[contains(.,'Please provide a valid password') and @class='input-feedback']");
	By invoicesummtxt = By.xpath("//h5[contains(.,'Invoice Summary')]");
	
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
		entertext(driver, emailtxt, username, emailtxtnm);
	}
	
	public void enterpassword(String password) throws IOException {
		entertext(driver, passwordtxt, password, passwordtxtnm);
	}
	
	public void clickloginbutton() throws IOException {
		click(driver, loginbtn, loginbtnnm);
	}
	
	public void clicksigninlink() throws IOException {
		click(driver, signinlink, signinlinknm);
		
		waitforelementvisible(driver, 30, emailtxt);
	}
	
	public boolean checkconfirmtimezonedialog() throws IOException {
		return checkelementexists(driver, 30, confirmtimezonedialog);
	}
	
	public void closeconfirmtimezonedialog() throws IOException {
		click(driver, confirmtimezonedialogclose, confirmtimezonedialogclosenm);
	}
	
	public boolean checkhomepage() throws IOException{
		return checkelementexists(driver, 30, invoicesummtxt);
	}
	
	public boolean checkloginerrormsg() throws IOException{
		return checkelementexists(driver, 30, loginerrormsg);		
	}
	
	public boolean checkloginlockerrormsg() throws IOException{
		return checkelementexists(driver, 30, loginlockerrormsg);		
	}
	
	public boolean checkblankemailerrormsg() throws IOException{
		return checkelementexists(driver, 30, blankemailerrormsg);		
	}
	
	public boolean checkblankpassworderrormsg() throws IOException{
		return checkelementexists(driver, 30, blankpassworderrormsg);		
	}
}
