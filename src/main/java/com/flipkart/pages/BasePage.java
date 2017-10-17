package com.flipkart.pages;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.logger.Logger;
import com.flipkart.utils.PropertiesUtil;
import com.google.common.base.Function;

public class BasePage 
{
	public int defaultTimeOut = Integer.parseInt(PropertiesUtil.getProperty("defaultTimeOut"));
	protected WebDriver driver;
	
	public void getElementToBeClickable(WebDriver driver, By webElement)
	{
		this.driver = driver;
		WebDriverWait myWait = new WebDriverWait(driver, defaultTimeOut);
		myWait.until(ExpectedConditions.elementToBeClickable(webElement));		
	}
	
	
	public WebElement elementReadyForOperation(WebDriver driver, final By by)
	{
		Wait<WebDriver> myWait = new FluentWait<WebDriver>(driver).withTimeout(defaultTimeOut, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		
		WebElement element = myWait.until(new Function<WebDriver, WebElement>() 
		{
			public WebElement apply(WebDriver driver) 
			{
				return driver.findElement(by);
			}
		});
		return element;		
	}
	
	
	public void waitForElement(WebDriver driver, WebElement webElement)
	{
		this.driver = driver;
		WebDriverWait myWait = new WebDriverWait(driver, defaultTimeOut);
		myWait.until(ExpectedConditions.visibilityOf(webElement));		
	}
	
	
	public boolean waitForElementToBeDisappeared(WebDriver driver, By webElement)
	{
		boolean booleanValue = false;
		WebDriverWait myWait = new WebDriverWait(driver, defaultTimeOut);
		booleanValue = myWait.until(ExpectedConditions.invisibilityOfElementLocated(webElement));
		return booleanValue;		
	}
	
	
	public void getScreenShot()
	{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try
		{
			FileUtils.copyDirectory(srcFile, new File (System.getProperty("user.dir")+ File.separator + "failedScreenShot.png"));
		}
		catch (IOException e)
		{
			Logger.log("Exception while capturing screenshot");
			e.printStackTrace();
		}
		Logger.log("Screenshot was captured");
	}
	
	
	public String getTitle(WebDriver driver)
	{
		return driver.getTitle();
	}

}
