@prod-sanity
Feature: Cash order placement for single item and multiple uom

  Scenario: Verify cash order placement for single item and multiple uom

    * user login to the experience layer sales app with valid mobile number
      | 9879315696 |
    * user click on beat button
    * user click on locality and store
      | ${PROD_LOCALITY_1} | ${PROD_STORE_1} |
    * user clicks on take a remote order button
    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-1 | ${NUMBER-1-5} | Piece | ${NUMBER-1-5} |
    * user click on cart next button
    * user click on place order button
    * user click on cart local place order button


    

