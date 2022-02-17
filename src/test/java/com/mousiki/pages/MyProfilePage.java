package com.mousiki.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;

public class MyProfilePage extends TestBase {
	//create webdriver variable
	WebDriver driver;
	
	//My Profile web elements
	By firstnametxt = By.xpath("//*[@id='firstName']");
	By lastnametxt = By.xpath("//*[@id='lastName']");
	By maleradio = By.xpath("//label[contains(.,'Male')]");
	By femaleradio = By.xpath("//label[contains(.,'Female')]");
	By otherradio = By.xpath("//label[contains(.,'Others')]");
	By dateofbirthtxt = By.xpath("//input[@name='dob']");
	By phonenotxt = By.xpath("//input[@id='phoneNumber']");
	By emailtxt = By.xpath("//*[@id='email']");
	
	By address1txt = By.xpath("//input[@id='address1']");
	By address2txt = By.xpath("//input[@id='address2']");
	By citytxt = By.xpath("//input[@id='city']");
	By pincodetxt = By.xpath("//input[@id='cityPincode']");
	By statetxt = By.xpath("//input[@id='state']");
	By countrytxt = By.xpath("//input[@name='country']");
	By savebtn = By.xpath("//button[contains(.,'Save')]");
	By pushnotifycheckbox = By.xpath("//input[@id='pushNotificationPreference']/following-sibling::label[1]");
	By termcondcheckbox = By.xpath("//input[@id='termsandconditions']/following-sibling::label[1]");

	By profilepictxt = By.xpath("//input[@placeholder='Upload your file']");
	By schoolnametxt = By.xpath("//input[@id='name']");
	
	//defining element names
	String firstnametxtnm = "First Name";
	String lastnametxtnm = "Last Name";
	String emailtxtnm = "Email";
	
	String maleradionm = "male radio button";
	String femaleradionm = "female radio button";
	String otherradionm = "other radio button";
	String savebtnnm = "save button";
	
	String phonenotxtnm = "Phone number textbox";
	String dateofbirthtxtnm = "date of birth textbox";
	String profilepictxtnm = "profile picture upload";
	String schoolnametxtnm = "school name textbox";
	String address1txtnm = "address1 textbox";
	String address2txtnm = "address2 textbox";
	String citytxtnm = "city textbox";
	String pincodetxtnm = "Pincode textbox";
	String statetxtnm = "state textbox";
	String countrytxtnm = "country listbox";
	String pushnotifycheckboxnm = "enable notification checkbox";
	String termcondcheckboxnm = "Accept terms and conditions checkbox";

	
	
	public MyProfilePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clicksavebutton() throws IOException {
		if(checkelementexists(driver, 10, savebtn)) {
			click(driver, savebtn, savebtnnm);
		}
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
	
	public void clickenablecheckbox() throws IOException {
		click(driver, pushnotifycheckbox, pushnotifycheckboxnm);
	}
	
	public void clickacceptcheckbox() throws IOException {
		click(driver, termcondcheckbox, termcondcheckboxnm);
	}
	
	public void selectgender(String gender) throws IOException {
		if(gender.equalsIgnoreCase("MALE"))
			click(driver, maleradio, maleradionm);
		else if(gender.equalsIgnoreCase("FEMALE"))
			click(driver, femaleradio, femaleradionm);
		else
			click(driver, otherradio, otherradionm);
	}
	
	public void enterphonenumber(String phonenumber) throws IOException {
		entertext(driver, phonenotxt, phonenumber, phonenotxtnm);
	}
	
	public void enterschoolname(String schoolname) throws IOException {
		entertext(driver, schoolnametxt, schoolname, schoolnametxtnm);
	}
	
	public void enteraddress1(String address1) throws IOException {
		entertext(driver, address1txt, address1, address1txtnm);
	}
	
	public void enteraddress2(String address2) throws IOException {
		entertext(driver, address2txt, address2, address2txtnm);
	}
	
	public void entercity(String city) throws IOException {
		entertext(driver, citytxt, city, citytxtnm);
	}
	
	public void enterpincode(String pincode) throws IOException {
		entertext(driver, pincodetxt, pincode, pincodetxtnm);
	}
	
	public void enterstate(String state) throws IOException {
		entertext(driver, statetxt, state, statetxtnm);
	}
	
	public void selectcountry(String state) throws IOException {
		entertext(driver, countrytxt, state, countrytxtnm);
	}
	
	public void enterdateofbirth(String dateofbirth) throws IOException {
		entertext(driver, dateofbirthtxt, dateofbirth, dateofbirthtxtnm);
	}
	
	public void uploadprofilepic(String profilepicpath) throws IOException {
		entertext(driver, profilepictxt, profilepicpath, profilepictxtnm);
	}
}
