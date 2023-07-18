@add_to_cart
Feature: Add items to cart flow

  Scenario: User add multiple items, increase and decrease their quantity

    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-1-title} |
    * user captures store image if available
    * user clicks on take a remote order button
    * user add items to cart
      | Automation-Category-2       | Bag  | 7  |
      | Automation-Test-Item-Name-1 | Case | 10 |
    * user removes all item from cart
    * user add items to cart
      | Automation-Category-1        |
      | Automation-Test-Item-Name-17 |
    * user increase item quantity
      | Automation-Test-Item-Name-17 | Bag | ${NUMBER-5-10} |
    * user decrease item quantity
      | Automation-Test-Item-Name-17 | Bag |
    * user place remote order