package vit.automation.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDescPageObject {

	private static final Logger logger = LogManager.getLogger(ProductDescPageObject.class);

	// Section1: Declare a driver object
	private WebDriver driver;
	private WebDriverWait webDriverWait;

	// Section 2: Paratmerize the constuctor
	public ProductDescPageObject(WebDriver driver, WebDriverWait webDriverWait) {
		this.driver = driver;
		this.webDriverWait = webDriverWait;
	}

	// Section 3 : Define the locators
	// private By searchBoxElement = By.id("twotabsearchtextbox");
	private By listOfProducts = By.xpath(
	"//span[text()=\"RESULTS\"]//ancestor::div[@class='s-main-slot s-result-list s-search-results sg-row']//span[@class='a-size-medium a-color-base a-text-normal']");

	// Section 4 : Write Business Methods (methods to be exposed) agent
	public void clickOnAnyProd() {
		// listOfProducts will have all the links displayed in the search box
		List<WebElement> firstProd = driver.findElements(listOfProducts);
		// But as this step asks click on any link, we can choose to click on Index 0 of
		// the list
		firstProd.get(0).click();
	}

}
