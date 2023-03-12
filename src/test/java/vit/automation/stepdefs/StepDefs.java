package vit.automation.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	// Following code opted when dryrun made true for "@YourList" tag scenario
	//	@Given("signin Accounts and lists options is avavible on landing page of application")
	//	public void signin_accounts_and_lists_options_is_avavible_on_landing_page_of_application() {
	//	    // Write code here that turns the phrase above into concrete actions
	//	    throw new io.cucumber.java.PendingException();
	//	}
	//
	//
	//	Some other steps were also undefined:
	//
	//	@When("user mousehover on hello signin Accounts and lists")
	//	public void user_mousehover_on_hello_signin_accounts_and_lists() {
	//	    // Write code here that turns the phrase above into concrete actions
	//	    throw new io.cucumber.java.PendingException();
	//	}
	//	@Then("under Your Lists section following options are available")
	//	public void under_your_lists_section_following_options_are_available(io.cucumber.datatable.DataTable dataTable) {
	//	    // Write code here that turns the phrase above into concrete actions
	//	    // For automatic transformation, change DataTable to one of
	//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	//	    //
	//	    // For other transformations you can register a DataTableType.
	//	    throw new io.cucumber.java.PendingException();
	//	}

	@Given("signin Accounts and lists options is avavible on landing page of application")
	public void signin_accounts_and_lists_options_is_avavible_on_landing_page_of_application() {
		WebElement accnListElement = driver.findElement(By.id("nav-link-accountList"));
		Assert.assertEquals(true, accnListElement.isDisplayed());
	}

	@When("user mousehover on hello signin Accounts and lists")
	public void user_mousehover_on_hello_signin_accounts_and_lists() {
		WebElement accnListElement = driver.findElement(By.id("nav-link-accountList"));
		Actions act = new Actions(driver);
		act.moveToElement(accnListElement).build().perform();
	}
	@Then("under Your Lists section following options are available")
	public void under_your_lists_section_following_options_are_available(List<String> expectedYourListsOptions) {

		List<WebElement> yourListActElement = driver.findElements(By.xpath("//div[text()='Your Lists']//parent::div[@id='nav-al-wishlist']//a/span"));

		for (int i = 0; i < expectedYourListsOptions.size(); i++) {
			if (expectedYourListsOptions.get(i).equals(yourListActElement.get(i).getText())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}
		}
	}
	
	// Following code opted when dryrun made true for "@YourAccountOptionsList" tag scenario
//	@Then("under Your Account section following options are available")
//	public void under_your_account_section_following_options_are_available(io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//	    throw new io.cucumber.java.PendingException();
//	}
	
	@Then("under Your Account section following options are available")
	public void under_your_account_section_following_options_are_available(List<String> expectedYourAccountOptions) {
		
		List<WebElement> yourAccountActElement = driver.findElements(By.xpath("//div[text()='Your Account']//parent::div[@id='nav-al-your-account']//a/span"));

		for (int i = 0; i < expectedYourAccountOptions.size(); i++) {
			if (expectedYourAccountOptions.get(i).equals(yourAccountActElement.get(i).getText())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}
			
		}
	}
	
	// Following code opted when dryrun made true for "@FooterLinksLists" tag scenario
//	@Given("user scroldown to the botton of the landing page of the application")
//	public void user_scroldown_to_the_botton_of_the_landing_page_of_the_application() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//
//	Some other steps were also undefined:
//
//	@When("user is able to see {int} main options categories")
//	public void user_is_able_to_see_main_options_categories(Integer int1) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//	@When("the categories having the option {string}, {string}, {string} and {string}")
//	public void the_categories_having_the_option_and(String string, String string2, String string3, String string4) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//	@Then("under Get to Know Us category below options are visible")
//	public void under_get_to_know_us_category_below_options_are_visible(io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//	    throw new io.cucumber.java.PendingException();
//	}
//	@Then("under Connect with Us category below options are visible")
//	public void under_connect_with_us_category_below_options_are_visible(io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//	    throw new io.cucumber.java.PendingException();
//	}
//	@Then("under Make Money with Us category below options are visible")
//	public void under_make_money_with_us_category_below_options_are_visible(io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//	    throw new io.cucumber.java.PendingException();
//	}
//	@Then("under Let Us Help You category below options are visible")
//	public void under_let_us_help_you_category_below_options_are_visible(io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//	    throw new io.cucumber.java.PendingException();
//	}
	
	@Given("user scroldown to the botton of the landing page of the application")
	public void user_scroldown_to_the_botton_of_the_landing_page_of_the_application() throws InterruptedException {

		WebElement getToKnowUsElement = driver.findElement(By.xpath("//div[text()='Get to Know Us']"));

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", getToKnowUsElement);

		// This below line is just for demo, can be removed as well
		Thread.sleep(5000);
	}

	@When("user is able to see {int} main options categories")
	public void user_is_able_to_see_main_options_categories(int footerMainCatCount) {
		//div[@class='navFooterVerticalRow navAccessibility']/div[@class='navFooterLinkCol navAccessibility']/div[text()]
	    List<WebElement> footerMainCatListEle = driver.findElements(By.xpath("//div[@class='navFooterVerticalRow navAccessibility']/div[@class='navFooterLinkCol navAccessibility']/div[text()]"));
	    Assert.assertEquals(footerMainCatCount, footerMainCatListEle.size());
	}
	@When("the categories having the option {string}, {string}, {string} and {string}")
	public void the_categories_having_the_option_and(String categoryOneNameExp, String categoryTwoNameExp, String categoryThreeNameExp, String categoryFourNameExp) {
		List<WebElement> footerMainCatListEle = driver.findElements(By.xpath("//div[@class='navFooterVerticalRow navAccessibility']/div[@class='navFooterLinkCol navAccessibility']/div[text()]"));
		Assert.assertEquals(categoryOneNameExp, footerMainCatListEle.get(0).getText());
		Assert.assertEquals(categoryTwoNameExp, footerMainCatListEle.get(1).getText());
		Assert.assertEquals(categoryThreeNameExp, footerMainCatListEle.get(2).getText());
		Assert.assertEquals(categoryFourNameExp, footerMainCatListEle.get(3).getText());
	}
	@Then("under Get to Know Us category below options are visible")
	public void under_get_to_know_us_category_below_options_are_visible(List<String> expectedGettoKnowUsOptions) {
		
		List<WebElement> GettoKnowUsOptionsActElement = driver.findElements(By.xpath("//div[text()='Get to Know Us']//parent::div//ul/li/a[text()]"));

		for (int i = 0; i < expectedGettoKnowUsOptions.size(); i++) {
			if (expectedGettoKnowUsOptions.get(i).equals(GettoKnowUsOptionsActElement.get(i).getText())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}
			
		}
	}
	@Then("under Connect with Us category below options are visible")
	public void under_connect_with_us_category_below_options_are_visible(List<String> expectedConnectWithUsOptions) {
		
		List<WebElement> ConnectWithUsOptionsActElement = driver.findElements(By.xpath("//div[text()='Connect with Us']//parent::div//ul/li/a[text()]"));

		for (int i = 0; i < expectedConnectWithUsOptions.size(); i++) {
			if (expectedConnectWithUsOptions.get(i).equals(ConnectWithUsOptionsActElement.get(i).getText())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}
			
		}
	    
	}
	@Then("under Make Money with Us category below options are visible")
	public void under_make_money_with_us_category_below_options_are_visible(List<String> expectedMakeMoneyWithUsOptions) {
		
		List<WebElement> MakeMoneyWithUsOptionsActElement = driver.findElements(By.xpath("//div[text()='Make Money with Us']//parent::div//ul/li/a[text()]"));

		for (int i = 0; i < expectedMakeMoneyWithUsOptions.size(); i++) {
			if (expectedMakeMoneyWithUsOptions.get(i).equals(MakeMoneyWithUsOptionsActElement.get(i).getText())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}
			
		}
	   
	}
	@Then("under Let Us Help You category below options are visible")
	public void under_let_us_help_you_category_below_options_are_visible(List<String> expectedLetUsHelpYouOptions) {
		
		List<WebElement> LetUsHelpYouOptionsActElement = driver.findElements(By.xpath("//div[text()='Let Us Help You']//parent::div//ul/li/a[text()]"));

		for (int i = 0; i < expectedLetUsHelpYouOptions.size(); i++) {
			if (expectedLetUsHelpYouOptions.get(i).equals(LetUsHelpYouOptionsActElement.get(i).getText())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}
			
		}
	}

}
