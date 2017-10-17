package com.flipkart.tests;

import org.testng.annotations.Test;

import com.flipkart.logger.Logger;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.SearchResultsPage;
import com.flipkart.webdriver.BaseTest;

public class SampleTest extends BaseTest
{
	@Test
	public void searchResultValidation()
	{
		Logger.log("----------Test Started-------");
		this.getWebDriver();
		HomePage homePage = this.gotoFlipkartHomePage();
		SearchResultsPage searchResultsPage = homePage.searchFor("Nike");
		searchResultsPage.validateSrearchURL();
		searchResultsPage.validateSearchResults();
		this.close();
		Logger.log("---------Test Ended-----------");
	}

}
