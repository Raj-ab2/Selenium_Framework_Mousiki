package com.mousiki.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;

public class MyAccountPage extends TestBase {
	
	//create webdriver variable
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Myaccount web elements
	By oldpasswordtxt = By.xpath("//input[@id='oldPassword']");
	By newpasswordtxt = By.xpath("//input[@id='newPassword']");
	By submitbtn = By.xpath("//button[@id='submitBtn']");
	By passworderr = By.xpath("//div[@class='input-feedback']");
	By oldpassworderr = By.xpath("//div[@class='custom-error-message']");
	By alertmsg = By.xpath("//div[@role='alert']/span");
	By alertsucessmsg = By.xpath("//div[@role='alert']");
	By fieldmsg = By.xpath("//div[@class='input-feedback']");
	
	
	//defining element names
	String oldpasswordtxtnm = "Old Password text box";
	String newpasswordtxtnm = "New Password text box";
	String submitbtnnm = "Submit button";
	String alertmsgnm = "Fail message alert";
	String alertsucessmsgnm = "Success message alert";
	String fieldmsgnm = "field error message";
	
	public String getfielderrormsgtext() throws Throwable {
		waitForLoad(driver);
		return getelementtext(driver, fieldmsg, fieldmsgnm);
	}
	
	public String getalertmsgtext() throws Throwable {
		waitForLoad(driver);
		return getelementtext(driver, alertmsg, alertmsgnm);
	}
	
	public String getsuccessalertmsgtext() throws Throwable {
		waitForLoad(driver);
		return getelementtext(driver, alertsucessmsg, alertsucessmsgnm);
	}
	
	public boolean checkcustompassworderror(String experrormsg) throws Throwable {
		
		if(checkelementexists(driver, 5, oldpassworderr)) {
			System.out.println("error exist");
			System.out.println(getelementtext(driver, oldpassworderr, "pasworderrormessage"));
			System.out.println(experrormsg);
			if(getelementtext(driver, oldpassworderr, "pasworderrormessage").contains(experrormsg)) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	public boolean checkpassworderror(String experrormsg) throws Throwable {
		
		if(checkelementexists(driver, 5, passworderr)) {
			System.out.println("error exist");
			System.out.println(getelementtext(driver, passworderr, "pasworderrormessage"));
			System.out.println(experrormsg);
			if(getelementtext(driver, passworderr, "pasworderrormessage").contains(experrormsg)) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	public String getcurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public void enteroldpassword(String password) throws IOException {
		entertext(driver, oldpasswordtxt, password, oldpasswordtxtnm);
	}
	
	public void enternewpassword(String password) throws IOException {
		entertext(driver, newpasswordtxt, password, newpasswordtxtnm);
	}
	
	public void clicksubmitbutton() throws IOException {
		click(driver, submitbtn, submitbtnnm);
	}
}
