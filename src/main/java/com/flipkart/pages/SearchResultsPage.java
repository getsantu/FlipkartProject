package com.flipkart.pages;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.flipkart.utils.PropertiesUtil;
import com.flipkart.logger.Logger;

public class SearchResultsPage extends BasePage 
{
	String searchedProduct = null;
	
	By SearchResultItem = By.xpath(PropertiesUtil.getProperty("SearchResultsPage.Product"));
	By SearchResultsPageProductTitle = By.xpath(PropertiesUtil.getProperty("SearchResultsPage.Product.Title"));
	
	public SearchResultsPage(WebDriver driver, String product)
	{
		this.driver = driver;
		searchedProduct = product;
		PageFactory.initElements(this.driver, this);
	}
	
	
	public void validateSrearchURL()
	{
		String url = PropertiesUtil.getProperty("flipkartHomePageURL");
		url = url.concat("/_-_/keyword-");
		url = url.concat(searchedProduct);
	}
	
	
	public void validateSearchResults() 
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//WebElement elementToWaitFor = elementReadyForOperation(this.driver, SearchResultsPageProductTitle);
		//waitForElement(this.driver, elementToWaitFor);
		//Thread.sleep(40);
		List<WebElement> itemList = driver.findElements(SearchResultsPageProductTitle);
		Iterator<WebElement> iterator = itemList.iterator();
		while (iterator.hasNext())
		{
			WebElement elementOne = iterator.next();
			Assert.assertTrue(elementOne.getText().contains(searchedProduct), "Searched products are displayed");
			Logger.log("Search Results Matched!! Search term: "+searchedProduct+" was found in the product title: "+elementOne.getText());
		}
	}
	
	
	

}
