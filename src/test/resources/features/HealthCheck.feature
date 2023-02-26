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
