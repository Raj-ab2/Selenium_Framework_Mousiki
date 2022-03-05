package com.mousiki.pages;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.mousiki.testbase.TestBase;

public class InvoiceReportPage extends TestBase {

	WebDriver driver;

	public InvoiceReportPage(WebDriver driver) {
		this.driver = driver;
	}

	public String invColHeader_Common_XPATH = "//div[@class='sales-form--table-format__header table-format__%s']";

	public By invDate_Xpath = By.xpath("//div[@class='sales-form--table-format__header table-format__%s']");
	public By invNo_Xpath = By.xpath("sales-form--table-format__header table-format__invoice_no sales-header__no-sort");
	public By invCust_Xpath = By.xpath("sales-form--table-format__header table-format__customer");
	public By invAmount_Xpath = By.xpath("sales-form--table-format__header table-format__amount");
	public By invBalance_Xpath = By.xpath("sales-form--table-format__header table-format__balance");
	public By invStatus_Xpath = By
			.xpath("sales-form--table-format__header table-format__status sales-header__has-sort");
	public By invDueDate_Xpath = By
			.xpath("sales-form--table-format__header table-format__due_date sales-header__has-sort");

	public By invDashboardOpenText = By.xpath(
			"//div[@class='dashborad-invoice-summary-open-section']//p[@class='dashborad-invoice-summary__amt']");
	public By invDashboardOverdueText = By.xpath(
			"//div[@class='dashborad-invoice-summary-overdue-section']//p[@class='dashborad-invoice-summary__amt']");

	public By summaryOpenInv = By
			.xpath("//div[@class='summary-info summary-info__open']//p[@class='summary-invoice__amt']");
	public By summaryOverDueInv = By
			.xpath("//div[@class='summary-info summary-info__due']//p[@class='summary-invoice__amt']");

	public By fromDateCalInv_Xpath = By.cssSelector("input[class='react-datepicker-ignore-onclickoutside']");
	// By.xpath("(//input[@name='fromDate' and @placeholder='D/M/YYYY'])[1]");
	public By incorrectDateWarning_Xpath = By
			.xpath("//div[normalize-space()='The end date should be greater than to start date']");
	public By runReport_Xpath = By.xpath("(//button[normalize-space()='Run Report'])[1]");
	public By calDatepicker_Xpath = By.xpath(
			"//div[contains(@class,'react-datepicker__day react-datepicker__day--003 react-datepicker__day--selected')]");
	public By invoiceNumber_Xpath = By.xpath(
			"(//div[@class='table-format__cell table-format__invoice_no table-format__invoice_no_data tbl-invoice-number'])[1]");
	public By invReceivePaymentDD = By
			.xpath("//div[@class='mou-custom-dropdown__selected' and text()='Receive Payment']");
	public By receivePayment_btn = By.xpath("//button[@class='btn  ml-0 btn-primary' and text()='Receive Payment']");
	public By statusAfterPayment_xpath=By.xpath("//div[@class='view_inv_details__value view_inv_details--status' and text()='Paid']");

	// All partial xpath strings to be used on replacing
	public String strInvDate_Xpath_Text = "date sales-header__has-sort";
	public String strInvNo_Xpath_Text = "invoice_no sales-header__no-sort";
	public String strInvCust_Xpath_Text = "customer";
	public String strInvAmount_Xpath_Text = "amount";
	public String strInvBalance_Xpath_Text = "balance";
	public String strInvStatus_Xpath_Text = "status sales-header__has-sort";
	public String strInvDueDate_Xpath_Text = "due_date sales-header__has-sort";

	// All strings for webelement
	public String strExpInvDateText = "Date";
	public String strExpInvNoText = "Invoice No";
	public String strExpInvCustText = "Customer";
	public String strExpInvAmtText = "Amount";
	public String strExpInvBalText = "Balance";
	public String strExpInvStatusText = "Status";
	public String strExpInvDueDateText = "Due Date";
	public String strOpenInvoiceText = "OpenInvoice";
	public String strDueDateInvText = "OverDueInvoice";
	public String strRunReportText = "RunReport";
	public String strIncorrectDateRangeWarn = "Warning Message";
	public String strCalDateSelected = "Selected Calendar date";
	public String strReceivePayment = "Receive Payment";
	public String strInvStatusAfterPayment="Inv status";

	public String[] strExpHeaderList = { "Date", "Invoice No", "Customer", "Amount", "Balance", "Status", "Due Date" };
	public String strIncorrectDateWarnMsg = "The end date should be greater than to start date";
	public String fromDate = "04/02/2022";

	public boolean invColHeaderValidation(String commonXpath, String colHeaderType) throws IOException {
		boolean bVal = false;
		waitForLoad(driver);
		String replacedxpath = commonXpath.replace("%s", strInvDate_Xpath_Text);
		WebElement replacedXpath = driver.findElement(By.xpath(replacedxpath));
		System.out.println("Actual xpath:" + replacedXpath);
		if (replacedXpath.isDisplayed()) {
			String actualtxt = driver.findElement(By.xpath(replacedxpath)).getText();
			for (String expHeaderValue : strExpHeaderList) {
				if (actualtxt.equalsIgnoreCase(expHeaderValue)) {
					reportlog("Verified " + expHeaderValue + "section successfully", "PASS",
							"Sections verified successfully");
					bVal = true;
				} else
					reportlog("Header values did not match", "FAIL", "Headers are not matching");
			}
		}
		return bVal;
	}

	public boolean getOpenInvTextFromDashbrd() throws IOException {
		String openInvDashboardText = getelementtext(driver, invDashboardOpenText, strOpenInvoiceText);
		String dueDateInvDashboardText = getelementtext(driver, invDashboardOverdueText, strDueDateInvText);
		click(driver, invDashboardOpenText, strOpenInvoiceText);
		waitForLoad(driver);
		String openInvSummaryText = getelementtext(driver, summaryOpenInv, strOpenInvoiceText);
		String overdueInvSummaryText = getelementtext(driver, summaryOverDueInv, strDueDateInvText);

		if (openInvDashboardText.equalsIgnoreCase(openInvSummaryText)) {
			if (dueDateInvDashboardText.equalsIgnoreCase(overdueInvSummaryText)) {
				reportlog("OVerdue Invoice amount displayed in Dashboard and summary are matching", "PASS",
						"Overdue Invoice amount is matching");
			} else {
				reportlog("OVerdue Invoice amount displayed in Dashboard and summary are matching", "FAIL",
						"Overdue Invoice amount is matching");
			}
			return true;
		} else {
			reportlog("Open Invoice amount displayed in Dashboard and summary are not matching", "FAIL",
					"Amounts are not matching");
		}
		return false;

	}

	public boolean validateIncorrectInvDateRangeSelection(String fromDate, String incorrectDateWarning)
			throws IOException {
		// String calDate=randomNumForDate();
		entertext(driver, fromDateCalInv_Xpath, fromDate, strCalDateSelected);
		waitForLoad(driver);
		reportlog("RunReport clicked successfully", "PASS", "Clicked RunReport");
		String strActualWarning = getelementtext(driver, incorrectDateWarning_Xpath, strIncorrectDateRangeWarn);
		if (strActualWarning.equalsIgnoreCase(incorrectDateWarning)) {
			reportlog("Expected warning message displayed", "PASS", "Warning displayed");
			return true;
		} else {
			reportlog("Expected warning message not displayed", "FAIL", "No Warning displayed");
		}
		return false;
	}

	public boolean clickOnInvNumber() throws IOException, InterruptedException {
		Thread.sleep(10000);
		if (checkelementexists(driver, 0, invoiceNumber_Xpath)) {
			Thread.sleep(10000);
			click(driver, invoiceNumber_Xpath, strExpInvNoText);
			waitForLoad(driver);
			System.out.println("Clicked invoice number");
			return true;
		} else {
			System.out.println("Failed to click invoice number");
			return false;
		}
	}

	public boolean clickOnReceivePayment() throws IOException, InterruptedException {
		Thread.sleep(10000);
		if (checkelementexists(driver, 0, invReceivePaymentDD)) {
			Thread.sleep(10000);
			click(driver, invReceivePaymentDD, fromDate);
			waitForLoad(driver);
			System.out.println("Clicked on receive payment");
			return true;
		} else {
			System.out.println("Failed to click on receive payment");
			return false;
		}
	}

	public boolean clickOnReceivePaymentinPopUp() throws IOException, InterruptedException {
		Thread.sleep(10000);
		if (checkelementexists(driver, 0, receivePayment_btn)) {
			Thread.sleep(10000);
			click(driver, receivePayment_btn, strReceivePayment);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkInvoiceStatusAfterPayment(String expectedInvStatus) throws IOException, InterruptedException {
		Thread.sleep(10000);
		if(checkelementexists(driver, 0, statusAfterPayment_xpath)) {
			String actualInvStatus=getelementtext(driver, statusAfterPayment_xpath, strInvStatusAfterPayment);
			if(actualInvStatus.equalsIgnoreCase(expectedInvStatus)) {
				System.out.println("Status after payment is:"+ actualInvStatus);
				return true;
			}
			else
				System.out.println("Status after payment is:"+ actualInvStatus);
				return false;
		}
		return false;
	}

}
