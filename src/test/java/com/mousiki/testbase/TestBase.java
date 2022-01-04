package com.mousiki.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestNG;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mousiki.testscripts.Register;

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
//	public static WebDriver driver;
//	public ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static Properties prop;
	public static FileInputStream fis;
	
	public static ExtentTest test;
	public static ExtentReports extent;
	private static ThreadLocal<ExtentTest> extenttest;
	
	
	
	static {
		System.out.println("Extent Report Initialize");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports();
		System.out.println("User directory - "+System.getProperty("user.dir"));
//		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + System.getProperty("file.separator") +"testreport"+ System.getProperty("file.separator") + "report" + formatter.format(cal.getTime()) + ".html");
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + System.getProperty("file.separator") +"testreport"+ System.getProperty("file.separator") + "report" + ".html");
		System.out.println("report path-" + System.getProperty("user.dir") + System.getProperty("file.separator") +"testreport"+ System.getProperty("file.separator") + "report" + ".html");
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
		fis = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") +"src"+ System.getProperty("file.separator") +"test"+ System.getProperty("file.separator") +"java"+ System.getProperty("file.separator") +"com"+ System.getProperty("file.separator") +"mousiki"+ System.getProperty("file.separator") +"config"+ System.getProperty("file.separator") +"config.properties");
		prop.load(fis);
		System.out.println("property file data:" + prop);
		
	}
	
	/*public void setdriver(WebDriver newdriver) {
		this.driver.set(newdriver);
	}
	
	public WebDriver getDriver()
	{
		return this.driver.get();
	}*/
	
	/**
	 * browser invoke based on browser name in property file
	 * @throws IOException
	 */
	public void invoke() throws IOException {
		
		System.out.println("browser name=" + System.getProperty("browsername"));
		
		/*invokeBrowser(prop.getProperty("browsername"));
		try {
			hardwait(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		WebDriver driver = BrowserFactory.getInstance().getDriver();
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		waitForLoad(driver);
	}
	
	/**
	 * different browser initialize components
	 * @param browser
	 */
	@SuppressWarnings("deprecation")
	/*public void invokeBrowser(String browser) {
		if(prop.getProperty("OsName").contains("Win")) {
			if(browser.contains("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				DesiredCapabilities cap = new DesiredCapabilities().chrome();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				
//				driver = new ChromeDriver(cap);
				setdriver(new ChromeDriver(cap));
			}else if(browser.contains("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions fp = new FirefoxOptions();
				String path = "";
				fp.setBinary(path);
//				driver = new FirefoxDriver(fp);
				setdriver(new FirefoxDriver(fp));
			}else {
				//for other browsers
			}
			
			//do implicit wait 
			getDriver().manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("Implicitwait")), TimeUnit.SECONDS);
			
		}else if(prop.getProperty("OsName").contains("mac")) {
			if(browser.contains("safari")) {
				
			}
		}
	}*/
	
	/**
	 * method to click a webelement using webdriver and element locator
	 * @param driver
	 * @param ElementLocator
	 * @param name
	 * @throws IOException
	 */
	public void click(WebDriver driver, By ElementLocator, String name) throws IOException {
		try {
			
			WebElement ele = driver.findElement(ElementLocator);
			if(ele.isDisplayed()) {
				ele.click();
				waitForLoad(driver);
				reportlog(name + "clicked successfully", "INFO");
			}
		}catch(Exception e) {
			try {
				WebElement ele = driver.findElement(ElementLocator);
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", ele);
				waitForLoad(driver);
			}catch(Exception f) {
				reportlog("An exception occured while click for element " + name + " Exeception:" + f, "FAIL", "Click fail");
			}
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
	public void entertext(WebDriver driver, By ElementLocator, String value, String name) throws IOException {
		try {
			WebElement ele = driver.findElement(ElementLocator);
			if(ele.isDisplayed()) {
				ele.clear();
				ele.sendKeys(Keys.CONTROL + "a");
				ele.sendKeys(Keys.DELETE);
				ele.sendKeys(value);
				String text = ele.getAttribute("value");
				reportlog(name + " field entered '" + text +"' successfully", "INFO");
			}
		}catch(Exception e) {
			reportlog("An exception occured while enter for element " + name + " Exeception:" + e, "FAIL", "Enter text fail");
		}
			
	}
	
	public String getelementtext(WebDriver driver, By ElementLocator, String name) throws IOException {
		String elementtext = "";
		try {
			WebElement ele = driver.findElement(ElementLocator);
			if(ele.isDisplayed()) {
				elementtext = ele.getText();
				reportlog(name + "get text from element successfully", "INFO");
			}
			return elementtext;
		}catch(Exception e) {
			reportlog("An exception occured while enter for element " + name + " Exeception:" + e, "FAIL", "Enter text fail");
			return elementtext;
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
	public void sendtext(WebDriver driver, By ElementLocator, String value, String name) throws IOException {
		try {
			WebElement ele = driver.findElement(ElementLocator);
			if(ele.isDisplayed()) {
				ele.click();
				ele.sendKeys(value);
				waitForLoad(driver);
				reportlog(name + "entered successfully", "INFO");
			}
		}catch(Exception e) {
			reportlog("An exception occured while enter for element " + name + " Exeception:" + e, "FAIL", "Enter text fail");
		}
			
	}
	
	public void waitForLoad(WebDriver driver) {
	    new WebDriverWait(driver, 60).until((ExpectedCondition<Boolean>) wd ->
	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
	
	/**
	 * method to enter text in textbox using webdriver and element locator
	 * @param driver
	 * @param ElementLocator
	 * @param value
	 * @param name
	 * @throws IOException
	 */
	public void selectlist(WebDriver driver, By ElementLocator, String value, String name) throws IOException {
		try {
			WebElement ele = driver.findElement(ElementLocator);
			if(ele.isDisplayed()) {
				ele.sendKeys(value + Keys.ENTER);
				waitForLoad(driver);
				reportlog(name + "Selected successfully", "INFO");
			}
		}catch(Exception e) {
			reportlog("An exception occured while select for element " + name + " Exeception:" + e, "FAIL", "Select text fail");
		}
			
	}
	
	/**
	 * explicit wait component for webdriver and element locator
	 * @param driver
	 * @param timeout
	 * @param ElementLocator
	 * @throws InterruptedException 
	 */
	public void waitforelementvisible(WebDriver driver, int timeout, By ElementLocator) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ElementLocator));
	}
	
	/**
	 * hard wait for execution to stop in the middle
	 * @param timeout
	 * @throws InterruptedException
	 */
	public void hardwait(int timeout) throws InterruptedException {
		Thread.currentThread().sleep(timeout);
	}
	
	/**
	 * @param min
	 * @param max
	 * @return
	 */
	public int generaterandomnumber(int min, int max) {
		Random rand = new Random();

		// nextInt as provided by Random is exclusive of the top value so you need to add 1 

		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	/**
	 * check whether element exist in the current application page
	 * @param driver
	 * @param timeout
	 * @param ElementLocator
	 * @return
	 */
	public boolean checkelementexists(WebDriver driver, int timeout, By ElementLocator) {
		boolean returnvalue = false;
		try {
			waitForLoad(driver);
			waitforelementvisible(driver, timeout, ElementLocator);
			
			if(driver.findElement(ElementLocator).isDisplayed()) {
				returnvalue = true;
			}
		}catch(Exception e) {
			returnvalue = false;
		}
		return returnvalue;
	}
	
	/**
	 * method to connect DB and return query result
	 * @param query
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String[][] getDBValues(String query){
		String rn[][] = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			String hostname = prop.getProperty("DBHostname");
			String username = prop.getProperty("DBUsername");
			String password = prop.getProperty("DBPassword");
			Connection con=DriverManager.getConnection(  
					hostname,username,password);  
			
			/*Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);*/  
			
			//alternate method to overcome type forward only exception
			PreparedStatement pstat = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstat.executeQuery();
			System.out.println("execute query");
			
			//retrieve row count
			rs.last();
			int rows = rs.getRow();
			System.out.println("DB Rows:"+ rows);
			
			//retrieve column count
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			System.out.println("DB column:"+ columns);
			
			if(rows > 0) {			
				String data[][] = new String[rows][columns];
				
				int i=0;
				rs.beforeFirst();
				while(rs.next()) {
					for(int j=0;j<columns;j++) {
						data[i][j] = rs.getString(j+1);
					}
					i++;
				}
				return data;
			}else {
				return null;
			}
		}catch(Exception e) {
			System.out.println("exception:"+ e.getMessage());
			return null;
		}
		
	}
	
	public Object[][] getexcelinput(String sheetname, String testname){
		try {
		//declare data formatter
		DataFormatter format = new DataFormatter();
		
		//create file for datasheet
		FileInputStream tsdata = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") +"excelinput"+ System.getProperty("file.separator") +"TestData.xlsx");
		
		//create workbook and worksheet
		XSSFWorkbook wb = new XSSFWorkbook(tsdata);
		XSSFSheet ws = wb.getSheet(sheetname);
//		XSSFSheet ws = wb.getSheetAt(0);
		
		//get rowcount and columncount
		int rowcount = ws.getPhysicalNumberOfRows();
		XSSFRow row = ws.getRow(0);
		int colcount = row.getLastCellNum();
		
		System.out.println("Rows:"+rowcount);
		System.out.println("col:"+colcount);
		//create data object
		Object data[][] = new Object[rowcount-1][colcount];
		
		System.out.println(sheetname+":");
		System.out.println(testname+":");
		//read values from excel cell
		for(int i=0;i<rowcount-1;i++) {
			row = ws.getRow(i+1);
			if(row.getCell(0).toString().equalsIgnoreCase(testname)) {
				System.out.println("matched:"+row.getCell(0).toString());
				for(int j=0;j<colcount;j++) {
					data[i][j] = format.formatCellValue(row.getCell(j));
					System.out.println("data at "+i+", "+j+ " " +data[i][j]);
				}
			}
		}
		return data;
		}catch(Exception e) {
			System.out.println("Exception occurs:" + e);
		}
		return null;
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
	public String takescreenshot() throws IOException {
		//create screenshot file name
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String temp = formatter.format(cal.getTime());
		String screenshotpath = System.getProperty("user.dir") + System.getProperty("file.separator") +"screenshots" + System.getProperty("file.separator") + "RUN_" + temp + ".png";
		
		//Convert webdriver to TakeScreenshot
		File screenshotFile = ((TakesScreenshot) BrowserFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshotFile , new File(screenshotpath));
		
		if(System.getProperty("user.dir").indexOf("jenkins") > -1) {
			String jenkinsserver = System.getProperty("jenkinsserver");//"https://test.mousiki.io";
			String jenkinsbuildname = System.getProperty("jenkinsbuild");//"MosuikiTestBuild";
			screenshotpath = jenkinsserver + "/job/" + jenkinsbuildname + "/ws/screenshots/RUN_" + temp + ".png";
//			screenshotpath = "https://test.mousiki.io/job/FIrstBuildTest/ws/screenshots/RUN_" + temp + ".png";
		}
		return screenshotpath;
	}
	
	public void extenttestinitialize(String testname) {
		test = extent.createTest(testname);
		extenttest = new ThreadLocal<ExtentTest>();
		extenttest.set(test);
	}
	
	/**
	 * method for logging steps in extend report with screenshot
	 * @param stepdescription
	 * @param status
	 * @param screenshotname
	 * @throws IOException
	 */
	public void reportlog(String stepdescription, String status, String screenshotname) throws IOException {
		/*if(status.equalsIgnoreCase("PASS")) {
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
		}*/
		if(status.equalsIgnoreCase("PASS")) {
			extenttest.get().pass(stepdescription);
			extenttest.get().addScreenCaptureFromPath(takescreenshot(), screenshotname);
		}else if(status.equalsIgnoreCase("FAIL")) {
			extenttest.get().fail(stepdescription);
			extenttest.get().addScreenCaptureFromPath(takescreenshot(), screenshotname);
		}else if(status.equalsIgnoreCase("INFO")) {
			extenttest.get().info(stepdescription);
			extenttest.get().addScreenCaptureFromPath(takescreenshot(), screenshotname);
		}else {
			extenttest.get().info(stepdescription);
			extenttest.get().addScreenCaptureFromPath(takescreenshot(), screenshotname);
		}
	}
	
	/**
	 * method for logging steps in extend report without screenshot
	 * @param stepdescription
	 * @param status
	 * @throws IOException
	 */
	public void reportlog(String stepdescription, String status) throws IOException {
		/*if(status.equalsIgnoreCase("PASS")) {
			test.pass(stepdescription);
		}else if(status.equalsIgnoreCase("FAIL")) {
			test.fail(stepdescription);
		}else if(status.equalsIgnoreCase("INFO")) {
			test.info(stepdescription);
		}else {
			test.info(stepdescription);
		}*/
		if(status.equalsIgnoreCase("PASS")) {
			extenttest.get().pass(stepdescription);
		}else if(status.equalsIgnoreCase("FAIL")) {
			extenttest.get().fail(stepdescription);
		}else if(status.equalsIgnoreCase("INFO")) {
			extenttest.get().info(stepdescription);
		}else {
			extenttest.get().info(stepdescription);
		}
	}
	
	// function to generate a random string of length n
    public String getAlphaNumericString(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }
    
    public static boolean checktestphase(String testscenario, String testphase) throws Throwable{
    	boolean validtestphase = false;
    	//create file for datasheet
		FileInputStream tsdata;
		tsdata = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "excelinput" + System.getProperty("file.separator") + "TestData.xlsx");
		
		//create workbook and worksheet
		XSSFWorkbook wb = new XSSFWorkbook(tsdata);
		
		Sheet sheet = wb.getSheet("TestBase");
		Iterable<Row> rows = sheet::rowIterator;
		
		boolean header = true;
		List<String> keys = null;
		int testphaseindex = -1;
		int testscenarioindex = -1;
		for (Row row : rows) {
		  List<String> values = getValuesInEachRow(row);
		  if (header) {
			  
			  for (int headindex = 0;headindex<values.size();headindex++) {
				  if(values.get(headindex).equalsIgnoreCase(testphase)) {
					  testphaseindex = headindex;
				  }
				  if(values.get(headindex).equalsIgnoreCase("TestScenario")) {
					  testscenarioindex = headindex;
				  }
			  }
			header = false;
			keys = values;
			continue;
		  }
		  if(values.get(testphaseindex).contains("YES") && values.get(testscenarioindex).contains(testscenario)) {
			  validtestphase = true;
			  break;
		  }
		}
    	return validtestphase;
    }
    public static List<String> gettestphasetestlist(String testscenario, String testphase) throws Throwable{
		
		//create file for datasheet
		FileInputStream tsdata;
		tsdata = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "excelinput" + System.getProperty("file.separator") + "TestData.xlsx");
		
		//create workbook and worksheet
		XSSFWorkbook wb = new XSSFWorkbook(tsdata);
		
		Sheet sheet = wb.getSheet("TestBase");
		Iterable<Row> rows = sheet::rowIterator;
		List<String> results = new ArrayList<>();
		boolean header = true;
		List<String> keys = null;
		int testphaseindex = -1;
		int testscenarioindex = -1;
		int testnameindex = -1;
		for (Row row : rows) {
		  List<String> values = getValuesInEachRow(row);
		  if (header) {
			  
			  for (int headindex = 0;headindex<values.size();headindex++) {
				  if(values.get(headindex).equalsIgnoreCase(testphase)) {
					  testphaseindex = headindex;
				  }
				  if(values.get(headindex).equalsIgnoreCase("TestScenario")) {
					  testscenarioindex = headindex;
				  }
				  if(values.get(headindex).equalsIgnoreCase("Testcase name")) {
					  testnameindex = headindex;
				  }
			  }
			header = false;
			keys = values;
			continue;
		  }
		  if(values.get(testphaseindex).contains("YES") && values.get(testscenarioindex).contains(testscenario)) {
			  results.add(values.get(testnameindex));
			  
		  }
		}
		return results;
	}
    
	public static Object[][] getExcelData(String sheetname, String testname, String testphase) throws Throwable{
		//create file for datasheet
		FileInputStream tsdata;
		tsdata = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") +"excelinput"+ System.getProperty("file.separator") +"TestData.xlsx");
		
		//create workbook and worksheet
		XSSFWorkbook wb = new XSSFWorkbook(tsdata);
		
		Sheet sheet = wb.getSheet(sheetname);
		Iterable<Row> rows = sheet::rowIterator;
		List<Map<String, String>> results = new ArrayList<>();
		boolean header = true;
		List<String> keys = null;
		boolean validtestphase = checktestphase(sheetname, testphase);
		if(validtestphase) {
			int testphaseindex = -1;
			for (Row row : rows) {
			  List<String> values = getValuesInEachRow(row);
			  if (header) {
				header = false;
				keys = values;
				for (int headindex = 0;headindex<values.size();headindex++) {
					  if(values.get(headindex).equalsIgnoreCase(testphase)) {
						  testphaseindex = headindex;
						  break;
					  }
				}
				continue;
			  }
			  
			  if(values.get(0).contains(testname) && values.get(testphaseindex).equalsIgnoreCase("YES")) {
				  results.add(transform(keys, values));
			  }
			}
		}else {
			System.out.println("skipping test scenario:" + sheetname);
		}
		return asTwoDimensionalArray(results);
	}
	
	private static Object[][] asTwoDimensionalArray(List<Map<String, String>> interimResults) {
		Object[][] results = new Object[interimResults.size()][1];
		int index = 0;
		for (Map<String, String> interimResult : interimResults) {
		  results[index++] = new Object[] {interimResult};
		}
		return results;
	}

	private static Map<String, String> transform(List<String> names, List<String> values) {
		Map<String, String> results = new HashMap<>();
		for (int i = 0; i < names.size(); i++) {
		  String key = names.get(i);
		  String value = "";
		  try {
			  value = values.get(i);
		  }catch(Exception e) {}
		  results.put(key, value);
		}
		System.out.println(results);
		return results;
	}

	private static List<String> getValuesInEachRow(Row row) {
		//declare data formatter
		DataFormatter format = new DataFormatter();
		List<String> data = new ArrayList<>();
		Iterable<Cell> columns = row::cellIterator;
		for (Cell column : columns) {
			data.add(format.formatCellValue(column));
		}
		return data;
	}
}
