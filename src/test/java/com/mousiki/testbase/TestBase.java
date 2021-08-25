package com.mousiki.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;*/

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author USER
 *
 */

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
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "\\testreport\\" + "report" + formatter.format(cal.getTime()) + ".html");
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Mousiki Automation");
		extent.attachReporter(spark);

	}
	
	/**
	 * @throws IOException
	 * property file initialization
	 */
	@BeforeSuite
	public void properties() throws IOException {
		prop = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\mousiki\\config\\config.properties");
		prop.load(fis);
		System.out.println("property file data:" + prop);
		
	}
	
	/**
	 * browser invoke based on browser name in property file
	 * @throws IOException
	 */
	public void invoke() throws IOException {
		System.out.println("browser name=" + prop.getProperty("browsername"));
		invokeBrowser(prop.getProperty("browsername"));
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
	}
	
	/**
	 * different browser initialize components
	 * @param browser
	 */
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
				//for other browsers
			}
			
			//do implicit wait 
			driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("Implicitwait")), TimeUnit.SECONDS);
			
		}else if(prop.getProperty("OsName").contains("mac")) {
			if(browser.contains("safari")) {
				
			}
		}
	}
	
	/**
	 * method to click a webelement using webdriver and element locator
	 * @param driver
	 * @param ElementLocator
	 * @param name
	 * @throws IOException
	 */
	public static void click(WebDriver driver, By ElementLocator, String name) throws IOException {
		try {
			
			WebElement ele = driver.findElement(ElementLocator);
			if(ele.isDisplayed()) {
				ele.click();
				reportlog(name + "clicked successfully", "INFO");
			}
		}catch(Exception e) {
			reportlog("An exception occured while click for element " + name, "FAIL", "Click fail");
		}
	}
	
	/**
	 * method to enter text in textbox using webdriver and element locator
	 * @param driver
	 * @param ElementLocator
	 * @param value
	 * @param name
	 * @throws IOException
	 */
	public static void entertext(WebDriver driver, By ElementLocator, String value, String name) throws IOException {
		try {
			WebElement ele = driver.findElement(ElementLocator);
			if(ele.isDisplayed()) {
				ele.clear();
				ele.sendKeys(value);
				reportlog(name + "entered successfully", "INFO");
			}
		}catch(Exception e) {
			reportlog("An exception occured while enter for element " + name, "FAIL", "Enter text fail");
		}
			
	}
	
	/**
	 * explicit wait component for webdriver and element locator
	 * @param driver
	 * @param timeout
	 * @param ElementLocator
	 */
	public static void waitforelementvisible(WebDriver driver, int timeout, By ElementLocator) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ElementLocator));
	}
	
	/**
	 * check whether element exist in the current application page
	 * @param driver
	 * @param timeout
	 * @param ElementLocator
	 * @return
	 */
	public static boolean checkelementexists(WebDriver driver, int timeout, By ElementLocator) {
		boolean returnvalue = false;
		try {
			if(driver.findElement(ElementLocator).isDisplayed()) {
				returnvalue = true;
			}
		}catch(Exception e) {
			returnvalue = false;
		}
		return returnvalue;
	}
	
	/**
	 * generic function to get value from property file using propertykey name
	 * @param PropertyKey
	 * @return
	 */
	public static String getpropertyvalue(String PropertyKey) {
		return prop.getProperty(PropertyKey);
	}
	
	/**
	 * generic component to take screenshot using web driver
	 * @return screenshot path
	 * @throws IOException
	 */
	public static String takescreenshot() throws IOException {
		//create screenshot file name
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String screenshotpath = System.getProperty("user.dir") + "\\screenshots\\" + "RUN_" + formatter.format(cal.getTime()) + ".png";
		
		//Convert webdriver to TakeScreenshot
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshotFile , new File(screenshotpath));
		
		return screenshotpath;
	}
	
	/**
	 * method for logging steps in extend report with screenshot
	 * @param stepdescription
	 * @param status
	 * @param screenshotname
	 * @throws IOException
	 */
	public static void reportlog(String stepdescription, String status, String screenshotname) throws IOException {
		if(status.equalsIgnoreCase("PASS")) {
			test.pass(stepdescription);
			test.addScreenCaptureFromPath(takescreenshot(), screenshotname);
		}else if(status.equalsIgnoreCase("FAIL")) {
			test.fail(stepdescription);
			test.addScreenCaptureFromPath(takescreenshot(), screenshotname);
		}else if(status.equalsIgnoreCase("INFO")) {
			test.info(stepdescription);
			test.addScreenCaptureFromPath(takescreenshot(), screenshotname);
		}else {
			test.info(stepdescription);
			test.addScreenCaptureFromPath(takescreenshot(), screenshotname);
		}
	}
	
	/**
	 * method for logging steps in extend report without screenshot
	 * @param stepdescription
	 * @param status
	 * @throws IOException
	 */
	public static void reportlog(String stepdescription, String status) throws IOException {
		if(status.equalsIgnoreCase("PASS")) {
			test.pass(stepdescription);
		}else if(status.equalsIgnoreCase("FAIL")) {
			test.fail(stepdescription);
		}else if(status.equalsIgnoreCase("INFO")) {
			test.info(stepdescription);
		}else {
			test.info(stepdescription);
		}
	}
}
