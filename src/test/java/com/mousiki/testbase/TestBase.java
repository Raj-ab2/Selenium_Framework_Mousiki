package com.mousiki.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	/**
	 * configuration of browsers
	 * all reusable methods
	 * reusable utilities
	 * property readers
	 * XML Readers
	 */
	public static WebDriver driver;
	public static Properties prop;
	public static FileInputStream fis;
	
	public static ExtentTest test;
	public static ExtentReports extent;
	
	static {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
		
		extent = new ExtentReports(System.getProperty("user.dir") + "\\testreport\\" + "report" + formatter.format(cal.getTime()) + ".html", false);
	}
	
	@BeforeSuite
	public void properties() throws IOException {
		prop = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\mousiki\\config\\config.properties");
		prop.load(fis);
		System.out.println("property file data:" + prop);
		
	}
	
	public void invoke() throws IOException {
		System.out.println("browser name=" + prop.getProperty("browsername"));
		invokeBrowser(prop.getProperty("browsername"));
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
	}
	
	@SuppressWarnings("deprecation")
	public void invokeBrowser(String browser) {
		if(prop.getProperty("OsName").contains("Win")) {
			if(browser.contains("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				DesiredCapabilities cap = new DesiredCapabilities().chrome();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				
				driver = new ChromeDriver(cap);
				
			}else if(browser.contains("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions fp = new FirefoxOptions();
				String path = "";
				fp.setBinary(path);
				driver = new FirefoxDriver(fp);
				
			}else {
				
			}
		}else if(prop.getProperty("OsName").contains("mac")) {
			if(browser.contains("safari")) {
				
			}
		}
	}
	
	public static void click(WebDriver driver, By ElementLocator, String name) {
		try {
			
			WebElement ele = driver.findElement(ElementLocator);
			if(ele.isDisplayed()) {
				ele.click();
				test.log(LogStatus.PASS, "To verify the user able to click button" + name, name + "clicked successfully");
			}
		}catch(Exception e) {
			test.log(LogStatus.PASS, "To verify " + name + " is visible or clickable in the provided time",  "An exception occured while click for element " + name);
		}
	}
	
	public static void entertext(WebDriver driver, By ElementLocator, String value, String name) {
		try {
			WebElement ele = driver.findElement(ElementLocator);
			if(ele.isDisplayed()) {
				ele.clear();
				ele.sendKeys(value);
				test.log(LogStatus.PASS, "To verify the user able to enter " + name, name + "entered successfully");
			}
		}catch(Exception e) {
			test.log(LogStatus.PASS, "To verify " + name + " is visible or editable in the provided time",  "An exception occured while enter for element " + name);
		}
	}
	
	public static void waitforelementvisible(WebDriver driver, int timeout, By ElementLocator) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ElementLocator));
	}
}
