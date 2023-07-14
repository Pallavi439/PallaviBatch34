@e2e-without-sa
Feature: Cash order placement without shop address

  Scenario: Verify cash order placement flow without shop address

    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-3-title} |
    * user captures store image
    * user clicks on take a remote order button
    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-1 | ${NUMBER-1-5} | Piece | ${NUMBER-5-10} |
    * user click on cart next button
    * user click on place order button
    * user click on cart local place order button


    

