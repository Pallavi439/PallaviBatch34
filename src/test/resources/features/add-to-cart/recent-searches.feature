@recent-search @regression1 @gajender.singh@elastic.run
Feature: Recent Search flow

  Scenario: User add an item and verify recent search

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
      | ${product-name-1}     | 0             | Case | ${NUMBER-5-10} |
    * user verify recent search for "Case"