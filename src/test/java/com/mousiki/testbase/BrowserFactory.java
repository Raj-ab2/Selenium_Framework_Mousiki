package com.mousiki.testbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
		private BrowserFactory()
	   {
	      //Do-nothing..Do not allow to initialize this class from outside
	   }
	   private static BrowserFactory instance = new BrowserFactory();

	   public static BrowserFactory getInstance()
	   {
	      return instance;
	   }

	   ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local driver object for webdriver
	   {
	      @Override
	      protected WebDriver initialValue()
	      {
	    	  System.out.println("browser name from commad line"+ System.getProperty("browsername"));
	         // can be replaced with other browser drivers
	         if(System.getProperty("browsername").equalsIgnoreCase("CHROME")) {
	        	//WebDriverManager.chromedriver().setup();
	        	WebDriverManager.chromedriver().driverVersion("96.0.4664.45").setup();
	        	ChromeOptions options = new ChromeOptions();
	        	//if(System.getProperty("user.dir").indexOf("jenkins") > -1) {
	        		// chrome binary location specified here
//	        		options.setBinary("/usr/bin/google-chrome");
//		        	options.addArguments("--headless");
		        	options.addArguments("--incognito");
		        	options.addArguments("start-maximized"); // open Browser in maximized mode
		        	options.addArguments("disable-infobars"); // disabling infobars
		        	options.addArguments("--disable-extensions"); // disabling extensions
		        	options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		        	options.addArguments("--no-sandbox"); // Bypass OS security model
		        	options.addArguments("--window-size=1920,1080");
		        	options.addArguments("--disable-gpu");
		        	options.setExperimentalOption("useAutomationExtension", false);
		        	options.addArguments("--proxy-server='direct://'");
		        	options.addArguments("--proxy-bypass-list=*");
	        	//}
				return new ChromeDriver(options);
	         }else if(System.getProperty("browsername").equalsIgnoreCase("FIREFOX")) {
	        	WebDriverManager.firefoxdriver().setup();
				FirefoxOptions fp = new FirefoxOptions();
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				firefoxBinary.addCommandLineOptions("--headless");
				firefoxBinary.addCommandLineOptions("--no-sandbox");
				fp.setBinary(firefoxBinary);
				/*String path = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
				fp.setBinary(path);*/
				return new FirefoxDriver(fp);
	         }else {
	        	return null;
	         }
				
	      }
	   };

	   public WebDriver getDriver() // call this method to get the driver object and launch the browser
	   {
	      return driver.get();
	   }

	   public void removeDriver() // Quits the driver and closes the browser
	   {
	      driver.get().quit();
	      driver.remove();
	   }
	}
