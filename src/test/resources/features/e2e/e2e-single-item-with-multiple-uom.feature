@e2e-with-multi-uom @regression1 @gajender.singh@elastic.run
Feature: Cash order placement for single item and multiple uom

  Scenario: Verify cash order placement for single item and multiple uom


    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-1-title} |
    * user click on take order button
    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Test-Item-Name-17 | 0 | Piece | ${NUMBER-5-10} |
      | Automation-Test-Item-Name-17 | 0 | Bag | ${NUMBER-5-10} |
      | Automation-Test-Item-Name-17 | 0 | Case | ${NUMBER-5-10} |
    * user place local order


    

