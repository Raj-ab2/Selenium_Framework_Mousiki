package com.mousiki.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
	WebDriver driver;
	By firstnametxt = By.xpath("//*[@id='firstName']");
	By lastnametxt = By.xpath("//*[@id='lastName']");
	
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getcurrentURL() {
		return driver.getCurrentUrl();
	}
}
