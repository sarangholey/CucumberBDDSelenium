@ui @healthcheck

Feature: E-commerce Project Web Site Health Check

@Search
Scenario: User is able to open the application and able to perform a search operation for Tablet
#Given User Opened the browser
Given User navigated to the landing page of the application
When User Search for a product "Tablet"
Then Search result is displayed "Tablet Nokia"
#And browser is closed

@Search
Scenario: User is able to open the application and able to perform a search operation for Mobiles
#Given User Opened the browser
Given User navigated to the landing page of the application
When User Search for a product "Mobiles"
Then Search result is displayed "Mobiles"
#And browser is closed

@ProdDesc
Scenario: User is click on the Product and check the Product Details
#Given User Opened the browser
Given User navigated to the landing page of the application
And User Search for a product "Laptop"
When User click on any product
Then Product Description is displayed in new tab
#And browser is closed

