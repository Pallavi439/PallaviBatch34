@prod-sanity
Feature: Cash order placement for single item and multiple uom

  Scenario: Verify cash order placement for single item and multiple uom

    * user login to the experience layer sales app with valid mobile number
      | 9879315696 |
    * user click on beat button
    * user click on locality and store
      | ${PROD_LOCALITY_1} | ${PROD_STORE_1} |
    * user clicks on take a remote order button
    * user wait for visibility of catalogue
    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Ayurved Akash Soap @ 50          | 0 | Piece | ${NUMBER-1-5} |
      | Dabur Lal Dant Manjan @10        | 0 | Piece | ${NUMBER-1-5} |
      | Nisha Brown Mehendi @100         | 0 | Piece | ${NUMBER-1-5} |
      | Ujala Supreme Fabric Whitener @8 | 0 | Piece | ${NUMBER-1-5} |
      | Santoor Talcum Powder @10        | 0 | Piece | ${NUMBER-1-5} |
    * user click on cart next button
    * user click on place order button
    * user click on cart local place order button


    

