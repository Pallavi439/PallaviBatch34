@e2e @order-placement
Feature: Order placements

  Scenario: Verify order placement flow

    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user click on beat button
    * user reset store for "${wh2-sp1.locality}"
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-1-title} |
    * user captures store image
    * user click on take order button
    * user clicks on take a remote order button
    * user add item to cart
      | Automation-Category-2       | Bag  | 7  |
      | Automation-Test-Item-Name-1 | Case | 10 |
    * user removes all item from cart
    * user add item to cart
      | Automation-Category-1        |
      | Automation-Test-Item-Name-17 |
    * user increase item quantity
      | Automation-Test-Item-Name-17 | Bag |4|
    * user decrease item quantity
      | Automation-Test-Item-Name-17 | Bag |
    * user place remote order
    * user logs out of sales app


