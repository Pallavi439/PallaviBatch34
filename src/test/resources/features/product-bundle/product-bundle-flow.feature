@product-bundle @regression1 @gajender.singh@elastic.run
Feature: Product Bundle End to End Flow

  Scenario: Update Product Bundle Bin
* user get details by frappe client get api with filters
| Product Bundle Bin | {"warehouse":"${warehouse-2}","item_code":"Automation-Test-Item-Name-39 @190_Case25Bag12_3+Automation-Test-Item-Name-38 @180_Case25Bag12_1"} |
* response status code should be 200
* get response "message.name" string attribute and store into session "prod-bund-bin-name"
* user set value by frappe client set value api with filters and fieldname
| Product Bundle Bin | ${prod-bund-bin-name} | actual_qty | ${IntegerNum} |
* response status code should be 200

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
      | Automation-Category-1 | ${NUMBER-1-5} | Bag  | ${NUMBER-5-10} |
      | ${product-name-1}     | 0             | Case | ${NUMBER-5-10} |
    * user place remote order