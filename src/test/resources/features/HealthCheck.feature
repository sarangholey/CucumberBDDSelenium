@ui @healthcheck

Feature: E-commerce Project Web Site Health Check

Scenario: User is able to open the application and able to perform a search operation
Given User Opened the browser
And User navigated to the landing page of the application
When User Search for a product "Mobiles"
Then Search result is displayed


