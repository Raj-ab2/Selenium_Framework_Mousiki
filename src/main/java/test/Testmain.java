package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testmain {
	public static WebDriver driver;
	
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
/*		try{  
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			DesiredCapabilities cap = new DesiredCapabilities().chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			
			driver = new ChromeDriver(cap);
			
			driver.get("https://qa.mousiki.io");
			driver.manage().window().maximize();
			
			By signinlink = By.xpath("//a[contains(.,'Sign In')]");
			By emailtxt = By.xpath("//*[@id='username']");
			By passwordtxt = By.xpath("//*[@id='password']");
			By loginbtn = By.xpath("//*[@type='submit']");
			
			driver.findElement(signinlink).click();
			driver.findElement(emailtxt).sendKeys("hE8J31cZM1@gmail.com");
			driver.findElement(passwordtxt).sendKeys("Sgidfn345@");
			driver.findElement(loginbtn).click();
			
			By pushnotifycheckbox = By.xpath("//input[@id='pushNotificationPreference']/following-sibling::label[1]");
			By termcondcheckbox = By.xpath("//input[@id='termsandconditions']/following-sibling::label[1]");
			
			try {
				driver.findElement(termcondcheckbox).click();
			  } catch (Exception e) {
			     JavascriptExecutor executor = (JavascriptExecutor) driver;
			     executor.executeScript("arguments[0].click();", driver.findElement(termcondcheckbox));
			  }
			
			try {
				driver.findElement(pushnotifycheckbox).click();
			  } catch (Exception e) {
			     JavascriptExecutor executor = (JavascriptExecutor) driver;
			     executor.executeScript("arguments[0].click();", driver.findElement(pushnotifycheckbox));
			  }
			
			driver.findElement(By.xpath("//button[contains(.,'Ok')]"));
			
		}catch(Exception e){ System.out.println(e);}*/
		
		
	}
	/*public static Object[][] getExcelData() throws Throwable{
		//create file for datasheet
		FileInputStream tsdata;
		tsdata = new FileInputStream(System.getProperty("user.dir") + "\\excelinput\\TestData.xlsx");
		
		//create workbook and worksheet
		XSSFWorkbook wb = new XSSFWorkbook(tsdata);
		
		Sheet sheet = wb.getSheet("Registration");
		Iterable<Row> rows = sheet::rowIterator;
		List<Map<String, String>> results = new ArrayList<>();
		boolean header = true;
		List<String> keys = null;
		for (Row row : rows) {
		  List<String> values = getValuesInEachRow(row);
		  if (header) {
			header = false;
			keys = values;
			continue;
		  }
		  results.add(transform(keys, values));
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
		  String value = values.get(i);
		  results.put(key, value);
		}
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
	}*/
}
