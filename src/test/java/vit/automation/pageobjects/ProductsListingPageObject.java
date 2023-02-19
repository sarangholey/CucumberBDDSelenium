package vit.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsListingPageObject {

	private static final Logger logger = LogManager.getLogger(ProductsListingPageObject.class);

	// Section1: Declare a driver object
	private WebDriver driver;
	private WebDriverWait webDriverWait;

	// Section 2: Paratmerize the constuctor
	public ProductsListingPageObject(WebDriver driver, WebDriverWait webDriverWait) {
		this.driver = driver;
		this.webDriverWait = webDriverWait;
	}

	// Section 3 : Define the locators
	// private By searchBoxElement = By.id("twotabsearchtextbox");

	// Section 4 : Write Business Methods (methods to be exposed) agent
	public void validateSearchResult(String prodName) {
		logger.info("waiting for the page title to contain -> " + prodName);
		webDriverWait.until(ExpectedConditions.titleIs("Amazon.in : "+prodName+""));
		//Assertion for Page Title
	    Assert.assertEquals("Page Title validation","Amazon.in : "+prodName+"", driver.getTitle());
	    logger.info("Assertion Passed for validation of Search Result with product name as -> " + prodName);
	}

}
