package com.mousiki.testscripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mousiki.dataprovider.TestCaseData;
import com.mousiki.pages.DashboardPage;
import com.mousiki.pages.HomePage;
import com.mousiki.pages.InvoiceReportPage;
import com.mousiki.pages.SignInPage;
import com.mousiki.testbase.BrowserFactory;
import com.mousiki.testbase.TestBase;

public class DashboardValidation extends TestBase {

	DashboardPage dashbrd;
	SignInPage signin;
	InvoiceReportPage invoicepg;
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

		signin = new SignInPage(BrowserFactory.getInstance().getDriver());
		dashbrd = new DashboardPage(BrowserFactory.getInstance().getDriver());
		invoicepg = new InvoiceReportPage(BrowserFactory.getInstance().getDriver());
		homepg = new HomePage(BrowserFactory.getInstance().getDriver());
		System.out.println("current thread:" + Thread.currentThread().getId() + "driver intance"
				+ BrowserFactory.getInstance().getDriver());

		System.out.println("Test name: " + method.getName());
	}

	@Test(dataProviderClass = TestCaseData.class, dataProvider = "testdata")
	public void TC_dashboard_validation_parent(Map<String, String> data) throws Throwable {

		String testname = data.get("TestName");
		String emailId = data.get("Email");
		String password = data.get("Password");

		if (testname == null) {
			return;
		}

		extenttestinitialize(testname);

		signin.clicksigninlink();
		String signinurl = signin.getcurrentURL();
		if (signinurl.equalsIgnoreCase(signin.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		} else {
			reportlog("failed to navigate sign in page. expected url is not matching:" + signin.getexpectedsigninurl(),
					"FAIL", "Signin navigation");
		}
		signin.enterusername(emailId);
		signin.enterpassword(password);
		signin.clickloginbutton();
		if (signin.checkhomepage()) {
			reportlog("Login completed sucessfully", "PASS", "Login");
		} else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.upcmngSecTxt)) {
			reportlog("Upcoming Classes section validation successfull", "PASS", "Upcoming Classes section success");
		} else {
			reportlog("Upcoming Classes section validation unsuccessfull", "FAIL", "Upcoming Classes section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.invoicetxt)) {
			reportlog("ParentInvoicetxt section validation successfull", "PASS", "ParentInvoicetxt section success");
		} else {
			reportlog("ParentInvoicetxt section validation unsuccessfull", "FAIL", "ParentInvoicetxt section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.classTrendstxt)) {
			reportlog("ParentTrendstxt section validation successfull", "PASS", "ParentTrendstxt section success");
		} else {
			reportlog("ParentTrendstxt section validation unsuccessfull", "FAIL", "ParentTrendstxt section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.notificationtxt)) {
			reportlog("ParentNotificationtxt section validation successfull", "PASS",
					"ParentNotificationtxt section success");
		} else {
			reportlog("ParentNotificationtxt section validation unsuccessfull", "FAIL",
					"ParentNotificationtxt section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.parentPerftxt)) {
			reportlog("Parent performance section validation successfull", "PASS",
					"Parent performance section success");
		} else {
			reportlog("Parent performance section validation unsuccessfull", "FAIL",
					"Parent performance section failed");
		}

	}
	
	@Test(dataProviderClass = TestCaseData.class, dataProvider = "testdata")
	public void TC_dashboard_validation_student(Map<String, String> data) throws Throwable {

		String testname = data.get("TestName");
		String emailId = data.get("Email");
		String password = data.get("Password");

		if (testname == null) {
			return;
		}

		extenttestinitialize(testname);

		signin.clicksigninlink();
		String signinurl = signin.getcurrentURL();
		if (signinurl.equalsIgnoreCase(signin.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		} else {
			reportlog("failed to navigate sign in page. expected url is not matching:" + signin.getexpectedsigninurl(),
					"FAIL", "Signin navigation");
		}
		signin.enterusername(emailId);
		signin.enterpassword(password);
		signin.clickloginbutton();
		if (signin.checkhomepage()) {
			reportlog("Login completed sucessfully", "PASS", "Login");
		} else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.upcmngSecTxt)) {
			reportlog("Upcoming Classes section validation successfull", "PASS", "Upcoming Classes section success");
		} else {
			reportlog("Upcoming Classes section validation unsuccessfull", "FAIL", "Upcoming Classes section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.invoicetxt)) {
			reportlog("Invoice section validation successfull", "PASS", "Invoice section success");
		} else {
			reportlog("Invoice section validation unsuccessfull", "FAIL", "Invoice section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.classTrendstxt)) {
			reportlog("Class Trends section validation successfull", "PASS", "Class Trends section success");
		} else {
			reportlog("Class Trends section validation unsuccessfull", "FAIL", "Class Trends section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.notificationtxt)) {
			reportlog("Notification section validation successfull", "PASS",
					"Notification section success");
		} else {
			reportlog("Notification section validation unsuccessfull", "FAIL",
					"Notification section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.parentPerftxt)) {
			reportlog("performance section validation successfull", "PASS",
					"performance section success");
		} else {
			reportlog("performance section validation unsuccessfull", "FAIL",
					"performance section failed");
		}
		
		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.tch_Attendance_txt)) {
			reportlog("Attendance section validation successfull", "PASS", "Attendance section success");
		} else {
			reportlog("Attendance section validation unsuccessfull", "FAIL", "Attendance section failed");
		}
		
		if (dashbrd.scrollAndValidate(DashboardPage.tch_attendancecomm_Xpath, DashboardPage.tch_Enrollment_txt)) {
			reportlog("Enrollment drop down is enabled", "PASS", "Enrollment field validation is success");
		} else {
			reportlog("Enrollment drop down is not enabled", "FAIL", "Enrollment field validation is unsuccessfull");
		}

	}

	@Test(dataProviderClass = TestCaseData.class, dataProvider = "testdata")
	public void TC_dashboard_validation_teacher(Map<String, String> data) throws Throwable {

		String testname = data.get("TestName");
		String emailId = data.get("Email");
		String password = data.get("Password");

		if (testname == null) {
			return;
		}

		extenttestinitialize(testname);

		signin.clicksigninlink();
		String signinurl = signin.getcurrentURL();
		if (signinurl.equalsIgnoreCase(signin.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		} else {
			reportlog("failed to navigate sign in page. expected url is not matching:" + signin.getexpectedsigninurl(),
					"FAIL", "Signin navigation");
		}
		signin.enterusername(emailId);
		signin.enterpassword(password);
		signin.clickloginbutton();
		if (signin.checkhomepage()) {
			reportlog("Login completed sucessfully", "PASS", "Login");
		} else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.upcmngSecTxt)) {
			reportlog("Upcoming Classes section validation successfull", "PASS", "Upcoming Classes section success");
		} else {
			reportlog("Upcoming Classes section validation unsuccessfull", "FAIL", "Upcoming Classes section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.invoicetxt)) {
			reportlog("ParentInvoicetxt section validation successfull", "PASS", "ParentInvoicetxt section success");
		} else {
			reportlog("ParentInvoicetxt section validation unsuccessfull", "FAIL", "ParentInvoicetxt section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.classTrendstxt)) {
			reportlog("ParentTrendstxt section validation successfull", "PASS", "ParentTrendstxt section success");
		} else {
			reportlog("ParentTrendstxt section validation unsuccessfull", "FAIL", "ParentTrendstxt section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.notificationtxt)) {
			reportlog("ParentNotificationtxt section validation successfull", "PASS",
					"ParentNotificationtxt section success");
		} else {
			reportlog("ParentNotificationtxt section validation unsuccessfull", "FAIL",
					"ParentNotificationtxt section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.tch_FeesCollecTrend_txt)) {
			reportlog("Fees colection trend section validation successfull", "PASS",
					"Fees colection trend section success");
		} else {
			reportlog("Fees colection trend section validation unsuccessfull", "FAIL",
					"Fees colection trend section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.tch_CourseTrend_txt)) {
			reportlog("Course trend section validation successfull", "PASS", "Course trend section success");
		} else {
			reportlog("Course trend section validation unsuccessfull", "FAIL", "Course trend section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.parent_common_xpath, DashboardPage.tch_Attendance_txt)) {
			reportlog("Attendance section validation successfull", "PASS", "Attendance section success");
		} else {
			reportlog("Attendance section validation unsuccessfull", "FAIL", "Attendance section failed");
		}

		if (dashbrd.dashboardValidation(DashboardPage.tch_invoiceCommon_Xpath, DashboardPage.tch_OpenInvoice_txt)) {
			reportlog("Open Invoice field is present", "PASS", "Open Invoice validation is success");
		} else {
			reportlog("Open invoice field is not visible", "FAIL", "Open invoice validation is unsuccessfull");
		}

		if (dashbrd.dashboardValidation(DashboardPage.tch_invoiceCommon_Xpath, DashboardPage.tch_OverdueInvoice_txt)) {
			reportlog("Overdue Invoice field is present", "PASS", "Overdue Invoice validation is success");
		} else {
			reportlog("Overdue invoice field is not visible", "FAIL", "Overdue invoice validation is unsuccessfull");
		}

		if (dashbrd.scrollAndValidate(DashboardPage.tch_attendancecomm_Xpath, DashboardPage.tch_Student_attdnc_txt)) {
			reportlog("Student drop down is enabled", "PASS", "Student field validation is success");
		} else {
			reportlog("Student drop down is not enabled", "FAIL", "Student field validation is unsuccessfull");
		}

		if (dashbrd.scrollAndValidate(DashboardPage.tch_attendancecomm_Xpath, DashboardPage.tch_Enrollment_txt)) {
			reportlog("Enrollment drop down is enabled", "PASS", "Enrollment field validation is success");
		} else {
			reportlog("Enrollment drop down is not enabled", "FAIL", "Enrollment field validation is unsuccessfull");
		}
	}

	@Test(dataProviderClass = TestCaseData.class, dataProvider = "testdata")
	public void TC_dashboard_validation_upcomingClasses(Map<String, String> data) throws Throwable {

		String testname = data.get("TestName");
		String emailId = data.get("Email");
		String password = data.get("Password");

		if (testname == null) {
			return;
		}

		extenttestinitialize(testname);

		signin.clicksigninlink();
		String signinurl = signin.getcurrentURL();
		if (signinurl.equalsIgnoreCase(signin.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		} else {
			reportlog("failed to navigate sign in page. expected url is not matching:" + signin.getexpectedsigninurl(),
					"FAIL", "Signin navigation");
		}
		signin.enterusername(emailId);
		signin.enterpassword(password);
		signin.clickloginbutton();
		if (signin.checkhomepage()) {
			reportlog("Login completed sucessfully", "PASS", "Login");
		} else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}

		if(dashbrd.upcomingClassesInfoValidation()) {
		reportlog("Highlights in Upcoming classes are displaying successfully", "PASS", "upcomingClassesInfoValidation");
		}
		else {
			reportlog("Highlights in Upcoming classes are not displaying", "FAIL", "Failed-Upcoming Class Info");
		}
			
	}

	@Test(dataProviderClass = TestCaseData.class, dataProvider = "testdata")
	public void TC_Dashboard_Validation_NavigateToInvoiceScreen(Map<String, String> data) throws IOException {
		String testname = data.get("TestName");
		String emailId = data.get("Email");
		String password = data.get("Password");

		if (testname == null) {
			return;
		}
		extenttestinitialize(testname);
		signin.clicksigninlink();
		String signinurl = signin.getcurrentURL();
		if (signinurl.equalsIgnoreCase(signin.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		} else {
			reportlog("failed to navigate sign in page. expected url is not matching:" + signin.getexpectedsigninurl(),
					"FAIL", "Signin navigation");
		}
		signin.enterusername(emailId);
		signin.enterpassword(password);
		signin.clickloginbutton();
		if (signin.checkhomepage()) {
			reportlog("Login completed sucessfully", "PASS", "Login");
		} else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}
		dashbrd.clickOnInvoice();
		dashbrd.checkNavigatedURL();
		reportlog("Navigated to Invoice Report Page", "PASS", "Pass-Invoice report page");
	}

	@Test(dataProviderClass = TestCaseData.class, dataProvider = "testdata")
	public void TC_Dashboard_InvoiceReportValidation(Map<String, String> data) throws IOException {
		String testname = data.get("TestName");
		String emailId = data.get("Email");
		String password = data.get("Password");

		if (testname == null) {
			return;
		}
		extenttestinitialize(testname);
		signin.clicksigninlink();
		String signinurl = signin.getcurrentURL();
		if (signinurl.equalsIgnoreCase(signin.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		} else {
			reportlog("failed to navigate sign in page. expected url is not matching:" + signin.getexpectedsigninurl(),
					"FAIL", "Signin navigation");
		}
		signin.enterusername(emailId);
		signin.enterpassword(password);
		signin.clickloginbutton();
		if (signin.checkhomepage()) {
			reportlog("Login completed sucessfully", "PASS", "Login");
		} else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}

		if (invoicepg.getOpenInvTextFromDashbrd()) {
			reportlog("Open and Overdue amounts are matching in Dashboard and Summary screens", "PASS",
					"Invoice amounts matching");
		} else {
			reportlog("Open and Overdue amounts are not matching in Dashboard and Summary screens", "FAIL",
					"Invoice amounts are not matching");
		}
	}

	@Test(dataProviderClass = TestCaseData.class, dataProvider = "testdata")
	public void TC_Dashboard_NegFlow_ValidateWarningInIncorrectDate(Map<String, String> data) throws IOException, InterruptedException {
		String testname = data.get("TestName");
		String emailId = data.get("Email");
		String password = data.get("Password");
		String fromDate = data.get("FromDate");
		String incorrectDateWarning = data.get("Warning_Msg");

		if (testname == null) {
			return;
		}
		extenttestinitialize(testname);
		signin.clicksigninlink();
		String signinurl = signin.getcurrentURL();
		if (signinurl.equalsIgnoreCase(signin.getexpectedsigninurl())) {
			reportlog("navigated to signin page successfully", "INFO");
		} else {
			reportlog("failed to navigate sign in page. expected url is not matching:" + signin.getexpectedsigninurl(),
					"FAIL", "Signin navigation");
		}
		signin.enterusername(emailId);
		signin.enterpassword(password);
		signin.clickloginbutton();
		if (signin.checkhomepage()) {
			reportlog("Login completed sucessfully", "PASS", "Login");
		} else {
			reportlog("Login completed Unsucessfull", "FAIL", "Login");
		}
		
		homepg.clickhamburgericon();
		app_leftnavigation("Accounting;Reports");
		
		if (invoicepg.validateIncorrectInvDateRangeSelection(fromDate,incorrectDateWarning)) {
			reportlog("Expected Warning message is displayed", "PASS", "Expected Warning");
		} else {
			reportlog("Expected warning message is not displayed", "FAIL", "Not the expected warning");
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
