package com.mousiki.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;

public class SignUpPage extends TestBase {
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
	
	By emptyfirstnameerr = By.xpath("//div[@class='input-feedback'][contains(.,'First Name is required')]");
	By emptylastnameerr = By.xpath("//div[@class='input-feedback'][contains(.,'Last Name is required')]");
	By emptyemailerr = By.xpath("//div[@class='input-feedback'][contains(.,'Please provide a valid email')]");
	By passworderr = By.xpath("//div/div[contains(@class,'input-feedback')]");
	By invalidfielderr = By.xpath("//div[@class='custom-error-message']");
	
	By logoimg = By.xpath("//img[@alt='Logo']");
	
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
	String logoimgnm = "Mousiki logo";
	String registerbtnnm = "Register";
	
	String firstnametxtnm = "First Name";
	String lastnametxtnm = "Last Name";
	String emailtxtnm = "Email";
	String passwordtxtnm = "Password";
	
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		System.out.println("setting driver in signup page:"+ this.driver);
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
		entertext(driver, firstnametxt, firstname, firstnametxtnm);
	}
	
	public void enterlastname(String lastname) throws Throwable {
		entertext(driver, lastnametxt, lastname, lastnametxtnm);
	}
	
	public void enteremailid(String emailid) throws Throwable {
		entertext(driver, emailtxt, emailid, emailtxtnm);
	}
	
	public void enterpassword(String password) throws Throwable {
		entertext(driver, passwordtxt, password, passwordtxtnm);
		
		waitforelementvisible(driver, 30, registerbtn);
	}
	
	public void clicksignup() throws Throwable {
		click(driver, signuplink, signuplinknm);
		waitForLoad(driver);
		waitforelementvisible(driver, 30, imstudentlink);
	}
	
	public void clicklogoimage() throws Throwable {
		click(driver, logoimg, logoimgnm);
	}
	
	public void clickregister() throws Throwable {
		click(driver, registerbtn, registerbtnnm);
		waitForLoad(driver);
	}
	
	public void clickloginafterregister() throws Throwable {
		click(driver, confirmregloginlink, confirmregloginlinknm);
		waitForLoad(driver);
	}
	
	public void clickimparent() throws Throwable {
		click(driver, imparentlink, imparentlinknm);
		waitForLoad(driver);
		waitforelementvisible(driver, 30, firstnametxt);
	}
	
	public void clickimstudent() throws Throwable {
		click(driver, imstudentlink, imstudentlinknm);
		waitForLoad(driver);
		waitforelementvisible(driver, 30, firstnametxt);
	}
	
	public void clickimteacher() throws Throwable {
		click(driver, imteacherlink, imteacherlinknm);
		waitForLoad(driver);
		waitforelementvisible(driver, 30, firstnametxt);
	}
	
	public void clickwearemusicschool() throws Throwable {
		click(driver, wermusicschoollink, wermusicschoollinknm);
		waitForLoad(driver);
		waitforelementvisible(driver, 30, firstnametxt);
	}
	
	public boolean checkforregistercompletion() throws Throwable {
				
		return checkelementexists(driver, 30, registercompletionmsg);
	}
	
	public boolean checkforregisterconfirmation() throws Throwable {
		
		return checkelementexists(driver, 30, confirmregloginlink);
	}
	
	public boolean checkemptyfirstname() throws Throwable {
		
		return checkelementexists(driver, 5, emptyfirstnameerr);
	}
	
	public boolean checkemptylastname() throws Throwable {
		
		return checkelementexists(driver, 5, emptylastnameerr);
	}
	
	public boolean checkemptyemail() throws Throwable {
		
		return checkelementexists(driver, 5, emptyemailerr);
	}
	
	public boolean checkpassworderror(String experrormsg) throws Throwable {
		
		if(checkelementexists(driver, 5, passworderr)) {
			if(getelementtext(driver, passworderr, "pasworderrormessage").contains(experrormsg)) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	public boolean checkinvalidfield(String experrormsg) throws Throwable {
		if(checkelementexists(driver, 5, invalidfielderr)) {
			System.out.println("**EXP:"+getelementtext(driver, invalidfielderr, "customerrormessage"));
			System.out.println("**ACT:"+experrormsg);
			if(getelementtext(driver, invalidfielderr, "customerrormessage").contains(experrormsg)) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
}
	