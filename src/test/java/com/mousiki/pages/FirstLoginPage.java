package com.mousiki.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mousiki.testbase.TestBase;



public class FirstLoginPage extends TestBase {
	//create webdriver variable
	WebDriver driver;
	
	//first login web elements
	By confirmbtn = By.xpath("//button[contains(.,'Confirm')]");
	By okbtn = By.xpath("//button[contains(.,'Ok')]");
	By profilebtn = By.xpath("//nav//span/span");
	By profilebtn2 = By.xpath("//nav//ul/li[3]//span/span");
	By logoutlink = By.xpath("//a[contains(.,'Logout')]");
	
	By usertypelist = By.xpath("//input[@id='react-select-2-input']");
	By instrumentlist = By.xpath("//input[@id='react-select-5-input']");

	By maleradio = By.xpath("//label[contains(.,'Male')]");
	By femaleradio = By.xpath("//label[contains(.,'Female')]");
	By otherradio = By.xpath("//label[contains(.,'Others')]");
	
	By invoicesummtxt = By.xpath("//h5[contains(.,'Invoice Summary')]");

	By phonenotxt = By.xpath("//input[@id='phoneNumber']");
	By dateofbirthtxt = By.xpath("//input[@name='dateOfBirth']");
	By profilepictxt = By.xpath("//input[@placeholder='Upload your file']");
	By schoolnametxt = By.xpath("//input[@id='name']");
	By address1txt = By.xpath("//input[@id='address1']");
	By address2txt = By.xpath("//input[@id='address2']");
	By citytxt = By.xpath("//input[@id='city']");
	By pincodetxt = By.xpath("//input[@id='cityPincode']");
	By statetxt = By.xpath("//input[@id='state']");
	By pushnotifycheckbox = By.xpath("//input[@id='pushNotificationPreference']/following-sibling::label[1]");
	By termcondcheckbox = By.xpath("//input[@id='termsandconditions']/following-sibling::label[1]");

	By submitbtn = By.xpath("//button[contains(.,'Submit')]");
	By resetbtn = By.xpath("//button[contains(.,'Reset')]");
	
	//defining element names
	String confirmbtnnm = "confirm button";
	String okbtnnm = "ok button";
	String profilebtnnm = "profile icon button";
	String logoutlinknm = "Logout link";
	
	String usertypelistnm = "user type list";
	String instrumentlistnm = "Instrument list";
	String maleradionm = "male radio button";
	String femaleradionm = "female radio button";
	String otherradionm = "other radio button";

	String phonenotxtnm = "Phone number textbox";
	String dateofbirthtxtnm = "date of birth textbox";
	String profilepictxtnm = "profile picture upload";
	String schoolnametxtnm = "school name textbox";
	String address1txtnm = "address1 textbox";
	String address2txtnm = "address2 textbox";
	String citytxtnm = "city textbox";
	String pincodetxtnm = "Pincode textbox";
	String statetxtnm = "state textbox";
	String pushnotifycheckboxnm = "enable notification checkbox";
	String termcondcheckboxnm = "Accept terms and conditions checkbox";

	String submitbtnnm = "submit button";
	String resetbtnnm = "reset button";
	
	public FirstLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickconfirmbutton() throws IOException {
		if(checkelementexists(driver, 10, confirmbtn)) {
			click(driver, confirmbtn, confirmbtnnm);
		}
	}
	
	public void clickprofileiconbutton() throws IOException {
		if(checkelementexists(driver, 10, profilebtn)) {
			click(driver, profilebtn, profilebtnnm);
		}
		if(checkelementexists(driver, 10, profilebtn2)) {
			click(driver, profilebtn2, profilebtnnm);
		}
	}
	
	public void clicklogoutlink() throws IOException {
		click(driver, logoutlink, logoutlinknm);
	}
	
	public void clickokbutton() throws IOException {
		if(checkelementexists(driver, 10, okbtn)) {
			click(driver, okbtn, okbtnnm);
		}
	}
	
	public void clickenablecheckbox() throws IOException {
		click(driver, pushnotifycheckbox, pushnotifycheckboxnm);
	}
	
	public void clickacceptcheckbox() throws IOException {
		click(driver, termcondcheckbox, termcondcheckboxnm);
	}
	
	public void clicksubmitbutton() throws IOException {
		click(driver, submitbtn, submitbtnnm);
	}
	
	public void clickresetbutton() throws IOException {
		click(driver, resetbtn, resetbtnnm);
	}
	
	public void selectusertype(String usertype) throws IOException {
		waitforelementvisible(driver, 30, usertypelist);
		selectlist(driver, usertypelist, usertype, usertypelistnm);
	}
	
	public void selectinstrument(String instrument) throws IOException {
		selectlist(driver, instrumentlist, instrument, instrumentlistnm);
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
	
	public void enterdateofbirth(String dateofbirth) throws IOException {
		entertext(driver, dateofbirthtxt, dateofbirth, dateofbirthtxtnm);
	}
	
	public void uploadprofilepic(String profilepicpath) throws IOException {
		entertext(driver, profilepictxt, profilepicpath, profilepictxtnm);
	}
	
	public boolean checkhomepage() throws IOException{
		return checkelementexists(driver, 30, invoicesummtxt);
	}
}
