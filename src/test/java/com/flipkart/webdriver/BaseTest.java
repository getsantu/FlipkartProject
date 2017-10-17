package com.flipkart.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.flipkart.logger.Logger;
import com.flipkart.pages.HomePage;
import com.flipkart.utils.PropertiesUtil;

public class BaseTest 
{
	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		Logger.log("---------------Test Started");
	}
	
	public WebDriver getWebDriver()
	{
		String driverPathForFireFoxDriver = "D:\\Personal\\Selenium\\geckodriver-v0.19.0-win64\\geckodriver.exe";
		String driverPathForChromeDriver = "D:\\Personal\\Selenium\\chromedriver_win32\\chromedriver.exe";
		String browser1 = PropertiesUtil.getProperty("browser");
		if (browser1.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", driverPathForFireFoxDriver);
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			Logger.log(browser1+" browser was choose and opened");
		}
		else if (browser1.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", driverPathForChromeDriver);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			Logger.log(browser1+" browser was choose and opened");
		}
		return driver;
	}
	
	public HomePage gotoFlipkartHomePage()
	{
		Logger.log("Opening Flipkart home page with " + "\n" + "URL: " + PropertiesUtil.getProperty("flipkartHomePageURL"));
		driver.get(PropertiesUtil.getProperty("flipkartHomePageURL"));
		return new HomePage (this.driver);
	}
	
	
	public void close()
	{
		Logger.log("-----------------Test Ended");
		driver.close();
		driver.quit();
	}

}
