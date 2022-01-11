package com.mousiki.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;

public class HomePage extends TestBase {
	
	//create webdriver variable
	WebDriver driver;
	
	//Homepage web elements
	By hamburgericon = By.xpath("//div[contains(@class,'burger_menu')]/i");
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
	String termsofuselinknm = "terms of use link";
	String privacypolicylinknm = "Privacy Policy";
	String mousikillclinknm = "Mousiki llc link";
	String menulinknm = "Menu link";
	String hamburgericonnm = "hamburger icon name";
	String facebooklinknm = "Facebook link";
	String instagramlinknm = "Instagram link";
	String twitterlinknm = "Twitter link";
	String youtubelinknm = "Youtube link";
	String linkinlinknm = "LinkedIn link";
	String pinterestlinknm = "Pinterest link";
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickPrivacyPolicylink() throws IOException{
		click(driver, privacypolicylink, privacypolicylinknm);
	}
	
	public void clickmousikillclink() throws IOException{
		click(driver, mousikillclink, mousikillclinknm);
	}
	
	public void clicktermsofuse() throws IOException{
		click(driver, termsofuselink, termsofuselinknm);
	}
	
	public boolean checkhamburgericon() throws IOException{
		return checkelementexists(driver, 30, hamburgericon);
	}
	
	public void clickhamburgericon() throws IOException{
		click(driver, hamburgericon, hamburgericonnm);
	}
	
	public boolean checkmenulinkvisibility(String menuname) throws IOException{
		By menulink = By.xpath("//div[@class='mou-nav_items']//a[contains(.,'" + menuname + "')]");
		return checkelementexists(driver, 5, menulink);
	}
	
	public void clickmenulink(String menuname) throws IOException{
		By menulink = By.xpath("//div[@class='mou-nav_items']//a[contains(.,'" + menuname + "')]");
		click(driver, menulink, menulinknm);
	}
	
	public String getyoutubelinkaddress() throws IOException{
		return getelementlinkaddress(driver, youtubelink, youtubelinknm);
	}
	
	public String getlinkedinlinkaddress() throws IOException{
		return getelementlinkaddress(driver, linkinlink, linkinlinknm);
	}
	
	public String getpinterestlinkaddress() throws IOException{
		return getelementlinkaddress(driver, pinterestlink, pinterestlinknm);
	}
	
	public String getfacebooklinkaddress() throws IOException{
		return getelementlinkaddress(driver, facebooklink, facebooklinknm);
	}
	
	public String getinstagramlinkaddress() throws IOException{
		return getelementlinkaddress(driver, instagramlink, instagramlinknm);
	}
	
	public String gettwitterlinkaddress() throws IOException{
		return getelementlinkaddress(driver, twitterlink, twitterlinknm);
	}
}
