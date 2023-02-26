package vit.automation.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vit.automation.core.WebDriverFactory;
import vit.automation.pageobjects.LandingPageObjects;
import vit.automation.pageobjects.ProductDescPageObject;
import vit.automation.pageobjects.ProductsListingPageObject;

public class StepDefs {

	private static final Logger logger = LogManager.getLogger(StepDefs.class);

	Scenario scn;
	WebDriver driver;
	String base_url = "https://amazon.in";
	int implicit_wait_timeout_in_sec = 20;
	WebDriverWait webDriverWait;

	LandingPageObjects landingPageObjects;
	ProductsListingPageObject productsListingPageObject;
	ProductDescPageObject productDescPageObject;

	@Before
	public void setup(Scenario scn) throws Exception {
		
		this.scn = scn;
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		webDriverWait = new WebDriverWait(driver, 20);
		// Initialize Class objects
		landingPageObjects = new LandingPageObjects(driver, webDriverWait);
		productsListingPageObject = new ProductsListingPageObject(driver, webDriverWait);
		productDescPageObject = new ProductDescPageObject(driver, webDriverWait);

	}

	@After(order=1)
	public void tearDown() {
		WebDriverFactory.quitDriver();
		scn.log("Browser got closed");
	}
	
	@After(order=2)
	public void ScreeshotForFailure(Scenario scn) {
		 if (scn.isFailed()) {
		        TakesScreenshot scrnShot = (TakesScreenshot)driver;
		        byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
		        scn.attach(data, "image/png","Failed Step Name: " + scn.getName());
		    }else{
		        scn.log("Test case is passed, no screen shot captured");
		    }
	}

	@Given("User navigated to the landing page of the application")
	public void user_navigated_to_the_landing_page_of_the_application() {
		landingPageObjects.validateUserIsOnLandingPage(base_url);
		scn.log("User navigated to the landing page of the application");
	}

	@When("User Search for a product {string}")
	public void user_search_for_a_product(String productName) {
		landingPageObjects.searchProduct(productName);
		scn.log("User Searched for a product");
	}

	@Then("Search result is displayed {string}")
	public void search_result_is_displayed(String prodName) {
		productsListingPageObject.validateSearchResult(prodName);
		scn.log("Search result is displayed");

	}

	@When("User click on any product")
	public void user_click_on_any_product() {
		productDescPageObject.clickOnAnyProd();
		scn.log("user clicked on a product");
	}

	@Then("Product Description is displayed in new tab")
	public void product_description_is_displayed_in_new_tab() {
		// As product description click will open new tab, we need to switch the driver
		// to the new tab
		// If you do not switch, you can not access the new tab html elements
		// This is how you do it
		Set<String> handles = driver.getWindowHandles(); // get all the open windows
		Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
		String original = it.next();// gives the parent window id
		String prodDescp = it.next();// gives the child window id

		driver.switchTo().window(prodDescp); // switch to product Descp

		// Now driver can access new driver window, but can not access the orignal tab
		// Check product title is displayed
		WebElement productTitle = driver.findElement(By.id("productTitle"));
		Assert.assertEquals("Product Title", true, productTitle.isDisplayed());

		WebElement addToCartButton = driver.findElement(By.xpath("//input[@title='Add to Shopping Cart']"));
		Assert.assertEquals("Product Title", true, addToCartButton.isDisplayed());

		// Switch back to the Original Window, however no other operation to be done
		driver.switchTo().window(original);
		scn.log("Product Description is displayed in new tab");
	}
	
	@When("User Search for product {string}")
	public void user_search_for_product(String prodName) {
		landingPageObjects.searchProduct(prodName);
	}
	@Then("Search Result page is displayed as {string}")
	public void search_result_page_is_displayed_as(String prodName) {
		productsListingPageObject.validateSearchResult(prodName);
		scn.log("Search result is displayed");
	}

}
