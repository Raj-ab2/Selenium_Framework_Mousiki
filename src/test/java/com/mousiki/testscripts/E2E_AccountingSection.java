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
		homepg= new HomePage(BrowserFactory.getInstance().getDriver());
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
		accountingPg.createInvoice();
		System.out.println("Created Invoice");
		accountingPg.validateInvID();
		System.out.println("Invoice no validated successfully");
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
		reportlog("Compared the headers","PASS","Compared successfully");
		
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
