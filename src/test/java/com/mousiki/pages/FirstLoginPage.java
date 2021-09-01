package com.mousiki.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;

public class FirstLoginPage {
	//create webdriver variable
	WebDriver driver;
	
	//first login web elements
	By confirmbtn = By.xpath("//button[contains(.,'Confirm')]");
	By okbtn = By.xpath("//button[contains(.,'Ok')]");
	By profilebtn = By.xpath("//nav//span/span");
	By logoutlink = By.xpath("//a[contains(.,'Logout')]");
	
	//defining element names
	String confirmbtnnm = "confirm button";
	String okbtnnm = "ok button";
	String profilebtnnm = "profile icon button";
	String logoutlinknm = "Logout link";
	
	public FirstLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickconfirmbutton() throws IOException {
		if(TestBase.checkelementexists(driver, 10, confirmbtn)) {
			TestBase.click(driver, confirmbtn, confirmbtnnm);
		}
	}
	
	public void clickprofileiconbutton() throws IOException {
		TestBase.click(driver, profilebtn, profilebtnnm);
	}
	
	public void clicklogoutlink() throws IOException {
		TestBase.click(driver, logoutlink, logoutlinknm);
	}
	
	public void clickokbutton() throws IOException {
		if(TestBase.checkelementexists(driver, 10, okbtn)) {
			TestBase.click(driver, okbtn, okbtnnm);
		}
	}
}
