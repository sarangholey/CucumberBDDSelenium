package vit.automation.stepdefs;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {
	
	 WebDriver driver;
	 String base_url = "https://amazon.in";
	 int implicit_wait_timeout_in_sec = 20;
	
	@Given("User Opened the browser")
	public void user_opened_the_browser() {
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
	}

	@Given("User navigated to the landing page of the application")
	public void user_navigated_to_the_landing_page_of_the_application() {
		driver.get(base_url);
        String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        String actual = driver.getTitle();
        Assert.assertEquals("Page Title validation",expected,actual);
	    
	}
	
	@When("User Search for a product {string}")
	public void user_search_for_a_product(String productName) {
		
		 //Wait and Search for product
        WebDriverWait webDriverWait = new WebDriverWait(driver,20);
        WebElement elementSearchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));

        elementSearchBox.sendKeys(productName);
        driver.findElement(By.xpath("//input[@value='Go']")).click();
	    
	}

	@Then("Search result is displayed")
	public void search_result_is_displayed() {
		 //Wait for titile
        WebDriverWait webDriverWait1 = new WebDriverWait(driver,20);
        webDriverWait1.until(ExpectedConditions.titleIs("Amazon.in : Mobiles"));

        //Assertion for Page Title
        Assert.assertEquals("Page Title validation","Amazon.in : Laptop", driver.getTitle());
	    
	}

}
