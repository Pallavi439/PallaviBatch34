@product-bundle @regression1 @gajender.singh@elastic.run
Feature: Product Bundle End to End Flow

  Scenario: Update Product Bundle Bin
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get details by frappe client get api with filters
      | Product Bundle Bin | {"warehouse":"${warehouse-2}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "prod-bund-bin-name"
    * get response "message.item_code" string attribute and store into session "prod-bund-name"

    @ignore
  Scenario: Place Sales Order for Product Bundle
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
      | ${prod-bund-name} | 0 | Piece | ${NUMBER-5-10} |
    * user place remote order