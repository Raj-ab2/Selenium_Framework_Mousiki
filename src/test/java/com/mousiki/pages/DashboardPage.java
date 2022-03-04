
package com.mousiki.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.mousiki.testbase.TestBase;

public class DashboardPage extends TestBase {

	WebDriver driver;
	public static String parent_common_xpath = "//h5[contains(.,'%s')]";
	public static String tch_invoiceCommon_Xpath = "//p[contains(.,'%s')]";
	public static String tch_attendancecomm_Xpath = "//p[contains(.,'%s')]//following-sibling::div[@class=' css-2b097c-container']";
	public static String tch_Attendance_FromDateXpath = "//input[@name='fromDate' and @placeholder='D/M/YYYY']";
	public static String tch_Attendance_ToDateXpath = "//input[@name='toDate' and @placeholder='D/M/YYYY']";
	public By tch_UC_FullInfo_Xpath = By.xpath(
			"//div[@class='classes-list__right-cont--info-wrapper'] | //div[@class='classes-list__left-cont--info-wrapper']");
	public By tch_OpenInvoiceLink_Xpath = By.xpath("//p[contains(.,'Open Invoice(s)')]");
	public static String upcmngSecTxt = "Upcoming Classes";
	public static String invoicetxt = "Invoice Summary";
	public static String classTrendstxt = "Class Trends (3 Months)";
	public static String notificationtxt = "Notifications";
	public static String parentPerftxt = "Your Performance";
	public static String[] tch_UC_BubbleText = { "TestStudent QA", "Level-1 Coaching", "TQ" };
	public static String tch_FeesCollecTrend_txt = "Fees Collection Trend (3 Months)";
	public static String tch_CourseTrend_txt = "Course Trend (3 months)";
	public static String tch_Attendance_txt = "Attendance";
	public static String tch_Student_attdnc_txt = "Student";
	public static String tch_Enrollment_txt = "Enrollment";
	public static String tch_OpenInvoice_txt = "Open Invoice(s)";
	public static String tch_OverdueInvoice_txt = "Overdue Invoice(s)";
	public static String invoiceExpURL = "https://qa.mousiki.io/accounting/reports";
	public By accounting_Xpath = By
			.xpath("//a[@class='collapsible-header Ripple-parent arrow-r las leftnav__accounting']");
	public By createInv_Xpath = By.xpath("//span[normalize-space()='Create Invoice']");
	public By studentEnrollment_Xpath = By.xpath("//div[@class='elegant-select-cont']/p[contains(.,'Enrollment')]");
	public By EnrollmentField = By.xpath("//div[contains(@class,'css-e56m7-control')]");

	// List of WebElements name
	public String strAccountingTxt = "Accounting Label";
	public String strCreateInvTxt = "Create Invoice Label";

	public By invoiceColHeader_XPATH = By.xpath("//form[@name='contactForm']/./div[@class='sales-formtable-header'])");

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	public void validateColHeaders() {

		List<WebElement> eleColHeadList = driver.findElements(invoiceColHeader_XPATH);
		waitForLoad(driver);
		String strColHeader = eleColHeadList.get(eleColHeadList.size() - 1).getText();
		System.out.println("Column headers are:" + strColHeader);
	}

	public boolean dashboardValidation(String commonXpath, String sectionType) {

		String replacedxpath = commonXpath.replace("%s", sectionType);
		WebElement replacedXpath = driver.findElement(By.xpath(replacedxpath));
		if (replacedXpath.isEnabled()) {
			String actualtxt = driver.findElement(By.xpath(replacedxpath)).getText();
			if (actualtxt.equalsIgnoreCase(sectionType)) {
				System.out.println("Verified " + actualtxt + "section successfully");
				return true;
			} else {
				System.out.println("Section not displayed");
			}
		}
		return false;
	}

	public boolean checkEnrollmentField() throws IOException {
		if (checkelementexists(driver, 10, studentEnrollment_Xpath)) {
			if (driver.findElement(EnrollmentField).isEnabled()) {
				reportlog("Enrollment section is enabled", "PASS", "Enrollment visible");
				return true;
			} else {
				reportlog("Enrollment section is not enabled", "FAIL", "Enrollment section not visible");
			}

		}
		return false;

	}

	public boolean scrollAndValidate(String commonXpath, String sectionType) {
		String replacedxpath = commonXpath.replace("%s", sectionType);
		WebElement replacedXpath = driver.findElement(By.xpath(replacedxpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", replacedXpath);
		System.out.println("Scrolled to:" + replacedXpath);
		waitForLoad(driver);

		if (replacedXpath.isDisplayed()) {
			System.out.println("Verified " + sectionType + "section successfully");
			return true;
		} else
			System.out.println("Unable to validate" + sectionType + "section");
		return false;

	}

	public boolean upcomingClassesInfoValidation() {
		return checkelementexists(driver, 10, tch_UC_FullInfo_Xpath);
	}

	public void clickOnInvoice() throws IOException {
		click(driver, tch_OpenInvoiceLink_Xpath, tch_OpenInvoice_txt);
		waitForLoad(driver);
	}

	public void checkNavigatedURL() throws IOException {
		String actualURL = driver.getCurrentUrl();
		if (actualURL.equalsIgnoreCase(invoiceExpURL)) {
			reportlog("Navigated to Invoice report page", "PASS", "Navigated to Invoice page");
		} else
			reportlog("Unable to navigate to Invoice Report page", "FAIL", "Failed to navigate to Invoice Report page");
	}

	public void navigateToCreateInvoice() throws IOException {
		click(driver, accounting_Xpath, strAccountingTxt);
		waitForLoad(driver);
		click(driver, createInv_Xpath, strCreateInvTxt);
		waitForLoad(driver);

	}
}
