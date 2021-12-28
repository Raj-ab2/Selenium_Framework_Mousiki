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
	By termsofuselink = By.xpath("//a[contains(.,'Terms of Use')]");
	By mousikillclink = By.xpath("//a[contains(.,'Mousiki LLC')]");
	By twitterlink = By.xpath("//ul[contains(@class, 'social')]/li[1]/a");
	By instagramlink = By.xpath("//ul[contains(@class, 'social')]/li[2]/a");
	By facebooklink = By.xpath("//ul[contains(@class, 'social')]/li[3]/a");
	By youtubelink = By.xpath("//ul[contains(@class, 'social')]/li[4]/a");
	By linkinlink = By.xpath("//ul[contains(@class, 'social')]/li[5]/a");
	By pinterestlink = By.xpath("//ul[contains(@class, 'social')]/li[6]/a");
	
	//defining element names
	String privacypolicylinknm = "Privacy Policy";
	String loginbtnnm = "Log In";
	String emailtxtnm = "UserName";
	String passwordtxtnm = "Password";
	String confirmtimezonedialogclosenm = "confirm timezone dialog close";
}
