package com.mousiki.testscripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mousiki.dataprovider.TestCaseData;
import com.mousiki.pages.AccountingPage;
import com.mousiki.pages.DashboardPage;
import com.mousiki.pages.HomePage;
import com.mousiki.pages.InvoiceReportPage;
import com.mousiki.pages.SignInPage;
import com.mousiki.testbase.BrowserFactory;
import com.mousiki.testbase.TestBase;

public class E2E_AccountingSection extends TestBase {

	DashboardPage dashboardpg;
	AccountingPage accountingPg;
	SignInPage signInPg;
	HomePage homepg;
	InvoiceReportPage invoicePg;

	@BeforeClass
	public void setup() throws IOException {
		System.out.println("Before Class");
	}

	@BeforeTest
	public void startTest(final ITestContext testContext) throws IOException {
		System.setProperty("testngtestname", testContext.getName());
		System.out.println("Test Name - " + testContext.getName()); // it prints "Check name test"
		System.out.println("Suite Name: " + System.getProperty("testphase"));
	}

	@BeforeMethod
	public void nameBefore(Method method) throws Throwable {
		System.out.println("Before Method");
		Random rand = new Random();

		// nextInt as provided by Random is exclusive of the top value so you need to
		// add 1
		int randomNum = generaterandomnumber(1000, 10000);
		Thread.currentThread().sleep(randomNum);
		System.out.println("wait started:" + randomNum);

		// Invoke the browser
		invoke();
		signInPg = new SignInPage(BrowserFactory.getInstance().getDriver());
		dashboardpg = new DashboardPage(BrowserFactory.getInstance().getDriver());
		accountingPg = new AccountingPage(BrowserFactory.getInstance().getDriver());
		homepg = new HomePage(BrowserFactory.getInstance().getDriver());
		invoicePg = new InvoiceReportPage(BrowserFactory.getInstance().getDriver());
		System.out.println("current thread:" + Thread.currentThread().getId() + "driver intance"
				+ BrowserFactory.getInstance().getDriver());
		System.out.println("Test name: " + method.getName());
	}

	@Test(dataProviderClass = TestCaseData.class, dataProvider = "testdata")
	public void TC_Accounting_CreateInvoice(Map<String, String> data) throws IOException, InterruptedException {
		String testname = data.get("TestName");
		String emailId = data.get("Email");
		String password = data.get("Password");

		if (testname == null) {
			return;
		}
		extenttestinitialize(testname);
		signInPg.clicksigninlink();
		String signinurl = signInPg.getcurrentURL();
		if (signinurl.equalsIgnoreCase(signInPg.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		} else {
			reportlog(
					"failed to navigate sign in page. expected url is not matching:" + signInPg.getexpectedsigninurl(),
					"FAIL", "Signin navigation");
		}
		signInPg.enterusername(emailId);
		signInPg.enterpassword(password);
		signInPg.clickloginbutton();
		if (signInPg.checkhomepage()) {
			reportlog("Login completed sucessfully", "PASS", "Login");
		} else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}

		homepg.clickhamburgericon();
		app_leftnavigation("Accounting;Create Invoice");
		if (accountingPg.clickStudentName()) {
			reportlog("Clicked on Student Name drop down", "PASS", "Student NAme clicked");
		} else {
			reportlog("Failed to clicked on Student Name drop down", "FAIL", "Failed to click student name");
		}

		if (accountingPg.selectStudentFromDD()) {
			reportlog("Selected Student from drop down", "PASS", "Student selected from DD");
		} else {
			reportlog("Failed to select Student Name from drop down", "FAIL", "Failed to select student DD");
		}

		if (accountingPg.selectAvailableCourse()) {
			reportlog("Selected course successfully", "PASS", "Course selected as expected");
		} else {
			reportlog("Failed to select course", "FAIL", "Failed to select course");
		}

		if (accountingPg.clickOnSave()) {
			reportlog("Clicked on Save", "PASS", "Save clicked");
		} else {
			reportlog("Failed to click on save", "FAIL", "Failed to click save");
		}

	}

	@Test(dataProviderClass = TestCaseData.class, dataProvider = "testdata")
	public void TC_Accounting_ViewInvoice(Map<String, String> data) throws IOException, InterruptedException {
		String testname = data.get("TestName");
		String emailId = data.get("Email");
		String password = data.get("Password");

		if (testname == null) {
			return;
		}
		extenttestinitialize(testname);
		signInPg.clicksigninlink();
		String signinurl = signInPg.getcurrentURL();
		if (signinurl.equalsIgnoreCase(signInPg.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		} else {
			reportlog(
					"failed to navigate sign in page. expected url is not matching:" + signInPg.getexpectedsigninurl(),
					"FAIL", "Signin navigation");
		}
		signInPg.enterusername(emailId);
		signInPg.enterpassword(password);
		signInPg.clickloginbutton();
		if (signInPg.checkhomepage()) {
			reportlog("Login completed sucessfully", "PASS", "Login");
		} else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}
		homepg.clickhamburgericon();
		app_leftnavigation("Accounting;Reports");
		reportlog("Navigated to Report", "PASS", "Report page");
		accountingPg.clickOnInvNum();
		reportlog("Clicked on Invoice number", "PASS", "Report page- invoice num");
		accountingPg.validateInvoiceViewPage();
		reportlog("Compared the headers", "PASS", "Compared successfully");

	}

	@Test(dataProviderClass = TestCaseData.class, dataProvider = "testdata")
	public void TC_Accounting_ValidateStudentNotSelectedWarningMsg(Map<String, String> data) throws Throwable {
		String testname = data.get("TestName");
		String emailId = data.get("Email");
		String password = data.get("Password");
		String experrormsg = data.get("Expected_Error");

		if (testname == null) {
			return;
		}
		extenttestinitialize(testname);
		signInPg.clicksigninlink();
		String signinurl = signInPg.getcurrentURL();
		if (signinurl.equalsIgnoreCase(signInPg.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		} else {
			reportlog(
					"failed to navigate sign in page. expected url is not matching:" + signInPg.getexpectedsigninurl(),
					"FAIL", "Signin navigation");
		}
		signInPg.enterusername(emailId);
		signInPg.enterpassword(password);
		signInPg.clickloginbutton();
		if (signInPg.checkhomepage()) {
			reportlog("Login completed sucessfully", "PASS", "Login");
		} else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}
		homepg.clickhamburgericon();
		app_leftnavigation("Accounting;Create Invoice");
		reportlog("Clicked on Create Invoice", "PASS", "create inv clecked");
		accountingPg.clickOnSaveInv();
		if (accountingPg.checkStudentNotSelectedWarning(experrormsg)) {
			reportlog("Expected error displayed", "PASS", "Error displayed when student field is empty");
		} else {
			reportlog("Expected error not displayed", "FAIL", "Error displayed when student field is empty");
		}

	}

	@Test(dataProviderClass = TestCaseData.class, dataProvider = "testdata")
	public void TC_Accounting_ReceivePayment(Map<String, String> data) throws Throwable {
		String testname = data.get("TestName");
		String emailId = data.get("Email");
		String password = data.get("Password");
		String expectedInvStatus = data.get("Expected_Error");

		if (testname == null) {
			return;
		}
		extenttestinitialize(testname);
		signInPg.clicksigninlink();
		String signinurl = signInPg.getcurrentURL();
		if (signinurl.equalsIgnoreCase(signInPg.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		} else {
			reportlog(
					"failed to navigate sign in page. expected url is not matching:" + signInPg.getexpectedsigninurl(),
					"FAIL", "Signin navigation");
		}
		signInPg.enterusername(emailId);
		signInPg.enterpassword(password);
		signInPg.clickloginbutton();
		if (signInPg.checkhomepage()) {
			reportlog("Login completed sucessfully", "PASS", "Login");
		} else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}
		homepg.clickhamburgericon();
		app_leftnavigation("Accounting;Reports");
		if (invoicePg.clickOnInvNumber()) {
			reportlog("Clicked on Invoice number", "PASS", "Clicked on Invoice number");
		} else {
			reportlog("Failed to click on Invoice number", "FAIL", "Failed to click on Invoice number");
		}
		if (invoicePg.clickOnReceivePayment()) {
			reportlog("Clicked on Receive Payment Drop down", "PASS", "Clicked Receive payment");
		} else {
			reportlog("Failed to click on Receive Payment Drop down", "FAIL", "Failed to click Receive payment");
		}

		if (invoicePg.clickOnReceivePaymentinPopUp()) {
			reportlog("Clicked on Receive Payment button", "PASS", "Clicked Receive payment button");
		} else {
			reportlog("Failed to click on Receive Payment button", "FAIL", "Failed to click Receive payment button");
		}

		if (invoicePg.checkInvoiceStatusAfterPayment(expectedInvStatus)) {
			reportlog("Expected Invoice status displayed", "PASS", "Invoice status displayed as expected");
		} else {
			reportlog("Expected Invoice status did not display", "FAIL", "Status did not display as exepcted");
		}

	}

	@AfterMethod
	public void closebrowser() {
		System.out.println("After method");
		// extent.flush();
		// extent.endTest(test);
		BrowserFactory.getInstance().removeDriver();

	}

	@AfterTest
	public void backtohomepage() throws IOException {
		System.out.println("after test complete");
	}

	@AfterClass
	public void extentflush() {
		extent.flush();
		// extent.endTest(test);
		// driver.close();

	}
}
