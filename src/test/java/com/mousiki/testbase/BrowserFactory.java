package com.mousiki.testbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
	        	WebDriverManager.chromedriver().setup();
				/*ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				DesiredCapabilities cap = new DesiredCapabilities().chrome();
				cap.setCapability(ChromeOptions.CAPABILITY, options);*/	
				return new ChromeDriver();
	         }else if(System.getProperty("browsername").equalsIgnoreCase("FIREFOX")) {
	        	WebDriverManager.firefoxdriver().setup();
				FirefoxOptions fp = new FirefoxOptions();
				String path = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
				fp.setBinary(path);
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
