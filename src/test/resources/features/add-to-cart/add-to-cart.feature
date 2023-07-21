@add_to_cart @regression1
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
    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-1 | ${NUMBER-1-5} | Bag  | ${NUMBER-5-10} |
      | ${product-name-1}     | 0             | Case | ${NUMBER-5-10} |
    * user removes all item from cart
    * user add item to cart
      | Automation-Category-1        | ${NUMBER-1-5} | Piece | 3 |
      | Automation-Test-Item-Name-17 | 0             | Case  | 5 |
    * user increase item quantity
      | Automation-Test-Item-Name-17 | Case | ${NUMBER-5-10} |
    * user decrease item quantity
      | Automation-Test-Item-Name-17 | Case |
    * user place remote order