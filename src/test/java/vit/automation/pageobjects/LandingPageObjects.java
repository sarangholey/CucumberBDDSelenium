package vit.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPageObjects {
	
	private static final Logger logger = LogManager.getLogger(LandingPageObjects.class);
	
	 //Section1:  Declare a driver object
		private WebDriver driver;
		private WebDriverWait webDriverWait;
	
	// Section 2: Paratmerize the constuctor
	  public LandingPageObjects(WebDriver driver, WebDriverWait webDriverWait){
	      this.driver = driver;
	      this.webDriverWait = webDriverWait;
	  }
	
	//Section 3 : Define the locators
	  private By searchBoxElement = By.id("twotabsearchtextbox");
	  private By searchButtonElement = By.xpath("//input[@value='Go']");

	//Section 4 : Write Business Methods (methods to be exposed) agent
    public void searchProduct(String prodName){
    	webDriverWait.until(ExpectedConditions.elementToBeClickable(searchBoxElement));
    	logger.info("waiting for webelement -> elementSearchBox to be clickable");
    	driver.findElement(searchBoxElement).sendKeys(prodName);
    	logger.info("sending keys into webelement -> searchBoxElement");
    	driver.findElement(searchButtonElement).click();
    	logger.info("clicking on the search button");
    }
    
    public void validateUserIsOnLandingPage(String base_url)
    {
      driver.get(base_url);
      logger.info("Browser got invocked with URl as -> " + base_url);
      String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
      String actual = driver.getTitle();
      Assert.assertEquals("Page Title validation",expected,actual);
      logger.info("Assertion for Page title validation is passed with expected as -> " + expected + " and actual as -> " + actual);
    }
    
}
