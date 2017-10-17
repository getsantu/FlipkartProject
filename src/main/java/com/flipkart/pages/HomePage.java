package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.utils.PropertiesUtil;
import com.flipkart.logger.Logger;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.SearchResultsPage;

public class HomePage extends BasePage
{
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	
	By SearchBar = By.xpath(PropertiesUtil.getProperty("HomePage.SearchBar"));
	By SignIn = By.xpath(PropertiesUtil.getProperty("HomePage.SignIn"));
	By LoginModal = By.xpath(PropertiesUtil.getProperty("HomePage.LoginPopUp"));
	By Email = By.xpath(PropertiesUtil.getProperty("HomePage.LoginPopUp.EmailField"));
	By Password = By.xpath(PropertiesUtil.getProperty("HomePage.LoginPopUp.PasswordField"));
	By SignInButton = By.xpath(PropertiesUtil.getProperty("HomePage.LoginPopUp.SignInButton"));
	By ErrorMessage = By.xpath(PropertiesUtil.getProperty("HomePage.LoginPopUp.SignInErrorMessage"));
	By CancelButton = By.xpath(PropertiesUtil.getProperty("HomePage.LoginPopUp.ClosePopUp"));
	
	
	public HomePage SignIn(String email, String password)
	{
		WebElement element = elementReadyForOperation(this.driver, SignIn);
		element.click();
		WebElement loginWindow = elementReadyForOperation(this.driver, LoginModal);
		driver.switchTo().frame(loginWindow);
		element = elementReadyForOperation(this.driver, Email);
		element.sendKeys(email);
		element = elementReadyForOperation(this.driver, Password);
		element.sendKeys(password);
		
		try 
		{
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		element = elementReadyForOperation(this.driver, SignInButton);
		Logger.log("SignIn button was clicked");
		element.click();
		
		try
		{
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		if(waitForElementToBeDisappeared(this.driver, SignInButton))
		{
			Logger.log("Login was successful");
		}
		else
		{
			Logger.log("Login was not successful. Please check the captured screenshot");
			getScreenShot();
		}
			
		//checkLoginSuccessful(element);
		return this;
	}

	public SearchResultsPage searchFor(String product) 
	{
		WebElement element = elementReadyForOperation(this.driver, SearchBar);
		element.sendKeys(product);
		element.submit();
		Logger.log("Searched for " + product);
		return new SearchResultsPage(this.driver, product);
	}
	
	public void checkLoginSuccessful(WebElement element)
	{
		if(element.isEnabled()){
			Logger.log("Login Modal wasn't closed");
			try {
				getScreenShot();
			} catch (Exception e) {
				Logger.log("Exception in capturing screenshot");
				e.printStackTrace();
			}
		}
		else{
			
			Logger.log("Login was successful");
		}
	}
	

}
