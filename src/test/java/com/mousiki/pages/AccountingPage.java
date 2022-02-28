package com.mousiki.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mousiki.testbase.TestBase;

public class AccountingPage extends TestBase {

	WebDriver driver;
	DashboardPage dashboardPg;
	HomePage homePg;
	// List of all WebElements in Accounting Page
	public By studentDD_Xpath = By.xpath("//div[contains(@class,'css-1hwfws3')]/div[contains(.,'Student Name')]");
	public By availableCourseCheckbx_Xpath = By.xpath("//label[contains(@class,'form-check-label')]");
	public By invSave_Xpath = By.xpath("//button[contains(.,'Save')and contains(@class,'btn ml-0 btn-primary')]");
	public By invCreatedMsg = By.xpath("//h2[contains(@class,'page-heading-title new-invoice')]");
	public By reportsLabel_Xpath = By.xpath("//span[contains(.,'Reports')]");
	public By invNoInReports_Xpath = By.xpath(
			"//div[contains(@class,'table-format__cell table-format__invoice_no table-format__invoice_no_data tbl-invoice-number')]");
	public By ddValue_Xpath = By
			.xpath("//div[@class=' css-1uccc91-singleValue']//div[contains(text(),'TestStudent QA')]");
	// ("//div[contains(@class,'multiselect-option')]/div[contains(.,'TestStudent
	// QA')]");
	public By navBar_Xpath = By.xpath("//i[@class='la la-bars']");
	public By accounting_Xpath = By
			.xpath("//a[@class='collapsible-header Ripple-parent arrow-r active las leftnav__accounting']");
	public By reports_Xpath = By.xpath("//span[normalize-space()='Reports']");
	public By reportHeaderList_Xpath = By.xpath("//table[contains(@class,'template_tbl')]/thead/tr/td");
	public By studentNotSelected_Xpath = By.xpath("//div[normalize-space()='Please select student']");

	// List of all Strings to compare or enter in text field
	public String strStudentName = "TestStudent QA";
	public String strCourseCheckbx = "Course Checkbox";
	public String strSaveBtn = "Save Button";
	public String strInvCreationMsg = "Edit Invoice Message";
	public String strInvNoInReport = "Invoice No in Report";
	public String strReportsLabel = "Accounting-Reports";
	public String strNavBar = "Navigation bar";
	public String strAccountingLabel = "Accounting";
	public String[] expectedListOfColHeaders = { "Description", "Qty", "Rate", "Discount", "Amount" };
	public String strStudentNotSelectedTxt = "Please select student";

	// List of webelement Strings
	public String strStudentDDTxt = "Student Name";

	public AccountingPage(WebDriver driver) {
		this.driver = driver;
	}

	public void createInvoice() throws IOException, InterruptedException {
		// click(driver, studentDD_Xpath, strStudentDDTxt);
		driver.findElement(
				By.xpath("//div[contains(.,'Student Name') and contains(@class,' css-1wa3eu0-placeholder')]")).click();
		Thread.sleep(10000);
		reportlog("Clicked DD", "PASS", strStudentDDTxt);
		if (checkelementexists(driver, 0, studentDD_Xpath)) {
			driver.findElement(
					By.xpath("//div[contains(@class,'multiselect-option')]/div[contains(.,'TestStudent QA')]")).click();
			// reportlog("Selected student", "PASS", strStudentDDTxt);
			/*
			 * driver.findElement(ddValue_Xpath).sendKeys(Keys.ARROW_DOWN);
			 * driver.findElement(ddValue_Xpath).sendKeys(Keys.ENTER);
			 */
			reportlog("Selected student", "PASS", strStudentDDTxt);
			waitForLoad(driver);
			click(driver, availableCourseCheckbx_Xpath, strCourseCheckbx);

			reportlog("Selected course", "PASS", strCourseCheckbx);
			click(driver, invSave_Xpath, strSaveBtn);

			reportlog("Clicked Save", "PASS", strSaveBtn);
		} else {
			reportlog("Invoice failed to create", "FAIL", "Invoice failed to create");
		}
	}

	public void validateInvID() throws IOException, InterruptedException {
		String stractualText = getelementtext(driver, invCreatedMsg, strInvCreationMsg);
		String trimedText = stractualText.replace("Edit Invoice ", "");
		System.out.println(trimedText + "is the actual text");
		Thread.sleep(3000);
		click(driver, navBar_Xpath, strNavBar);
		Thread.sleep(3000);
		if (driver.findElement(accounting_Xpath).isEnabled()) {
			click(driver, accounting_Xpath, strNavBar);
			Thread.sleep(5000);
			System.out.println("Accounting clicked successfully" + accounting_Xpath);

			if (driver.findElement(reports_Xpath).isEnabled()) {
				click(driver, reports_Xpath, strReportsLabel);
				reportlog("Clicked reports", "PASS", strSaveBtn);
				waitForLoad(driver);
			}
		} else {
			System.out.println("Accounting  and reports failed to click");
		}

		String strActualInvNo = getelementtext(driver, invNoInReports_Xpath, strInvNoInReport);
		if (strActualInvNo.equalsIgnoreCase(trimedText)) {
			System.out.println("ACtual inv.no is:" + strActualInvNo);
		} else {
			reportlog("Invoice number is not matching", "FAIL", "Invoice not matching");
		}

	}

	public void clickOnInvNum() throws IOException {
		click(driver, invNoInReports_Xpath, strInvNoInReport);
	}

	public boolean checkStudentNotSelectedWarning(String experrormsg) throws Throwable {

		if (checkelementexists(driver, 5, studentNotSelected_Xpath)) {
			getelementtext(driver, studentNotSelected_Xpath, strStudentNotSelectedTxt);
			System.out.println(experrormsg);
			if (getelementtext(driver, studentNotSelected_Xpath, strStudentNotSelectedTxt).contains(experrormsg)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public boolean clickOnSaveInv() throws IOException, InterruptedException {
		
		if(checkelementexists(driver, 5, invSave_Xpath)) {
			click(driver, invSave_Xpath, strSaveBtn);
			reportlog("Clicked On Save button", "PASS", "Clicked on Save");
			return true;
		}
		else {
			reportlog("Failed to click Save button", "FAIL", "Failed to click Save");
		}
		return false;
		
	}

	public void validateInvoiceViewPage() throws InterruptedException, IOException {
		Thread.sleep(10000);
		List<WebElement> reportColHeaderList = driver.findElements(reportHeaderList_Xpath);
		System.out.println(driver.findElement(reportHeaderList_Xpath).getAttribute("innerHTML"));
		System.out.println("Actual list is:" + reportColHeaderList.size());
		for (int k = 1; k <= reportColHeaderList.size(); k++) {
			System.out.println("Loop:" + k);
			By reportHeader_Xpath = By.xpath("//table[contains(@class,'template_tbl')]/thead/tr/td[" + k + "]");
			System.out.println(driver.findElement(reportHeader_Xpath).getAttribute("innerHTML"));
			if ((driver.findElement(reportHeader_Xpath).getAttribute("innerHTML"))
					.equalsIgnoreCase(expectedListOfColHeaders[k - 1])) {
				reportlog("Both are matching", "PASS", "Values are matching");
			} else
				reportlog("Values are not matching", "FAIL", "Values not matching");
		}

	}
}
