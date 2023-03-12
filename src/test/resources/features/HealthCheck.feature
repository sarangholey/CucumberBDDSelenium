@ui @healthcheck
Feature: E-commerce Project Web Site Health Check

  Background: Navigation to Apprication BASE URL
    Given User navigated to the landing page of the application

  @Search1
  Scenario: User is able to open the application and able to perform a search operation for Tablet
    When User Search for a product "Tablet"
    Then Search result is displayed "Tablet"

  @Search2
  Scenario: User is able to open the application and able to perform a search operation for Mobiles
    When User Search for a product "Mobiles"
    Then Search result is displayed "Mobiles"

  @MultiSearch
  Scenario Outline: User is able to search multiple products
    When User Search for product "<product_name>"
    Then Search Result page is displayed as "<prod_result>"
    Examples: 
      | product_name | prod_result |
      | mouse        | mouse       |
      | earphone     | earphone    |
      | computer     | computer    |

  @ProdDesc
  Scenario: User is click on the Product and check the Product Details
    And User Search for a product "Laptop"
    When User click on any product
    Then Product Description is displayed in new tab
    
    
  @YourList
  Scenario: Validate Your Lists Options on Landing page under Accounts & Lists Section
    Given signin Accounts and lists options is avavible on landing page of application
    When user mousehover on hello signin Accounts and lists
    Then under Your Lists section following options are available
    | Create a Wish List 		|
    | Wish from Any Website |
    | Baby Wishlist					|
    | Discover Your Style		|
    | Explore Showroom			|
    
  @YourAccountOptionsList
  Scenario: Validate Your Account Options on Landing page under Accounts & Lists Section
    Given signin Accounts and lists options is avavible on landing page of application
    When user mousehover on hello signin Accounts and lists
    Then under Your Account section following options are available
    | Your Account											|
		| Your Orders												|
		| Your Wish List										|
		| Your Recommendations							|
		| Your Prime Membership							|
    | Your Prime Video									|
		| Your Subscribe & Save Items				|
		| Memberships & Subscriptions				|
    | Your Free Amazon Business Account	|
		| Your Seller Account								|
		| Manage Your Content and Devices		|
		
	@FooterLinksLists
	Scenario: Validate all footer links on landing page of the application
    Given user scroldown to the botton of the landing page of the application
    When user is able to see 4 main options categories
    And the categories having the option "Get to Know Us", "Connect with Us", "Make Money with Us" and "Let Us Help You"
    Then under Get to Know Us category below options are visible
    | About Us				|
    | Careers					|
    | Press Releases	|
    | Amazon Science	|   
		And under Connect with Us category below options are visible
		| Facebook 	|
		| Twitter		|
		| Instagram	| 
		And under Make Money with Us category below options are visible
		| Sell on Amazon 								|
		| Sell under Amazon Accelerator |
		| Protect and Build Your Brand 	|
		| Amazon Global Selling 				|
		| Become an Affiliate 					|
		| Fulfilment by Amazon 					|
		| Advertise Your Products				|
		| Amazon Pay on Merchants 			|
		And under Let Us Help You category below options are visible
		| COVID-19 and Amazon 			|
		| Your Account 							|
		| Returns Centre 						|
		| 100% Purchase Protection 	|
		| Amazon App Download 			|
		| Amazon Assistant Download |
		| Help 											|
		
