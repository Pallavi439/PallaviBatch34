@product-bundle @regression1 @gajender.singh@elastic.run
Feature: Product Bundle End to End Flow

  Scenario: Get Product Bundle Bin
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200

    * user get details by frappe client get api with filters
    | Product Bundle | {"new_item_code":["like","Automation%%"]} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "bundle_name"

    * user get details by frappe client get api with filters
      | Product Bundle Bin | {"item_code":"${bundle_name}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "prod-bund-bin-name"
    * get response "message.item_code" string attribute and store into session "prod-bund-name"

    * user set value by frappe client set value api with filters and fieldname
      | Product Bundle Bin | ${prod-bund-bin-name} | actual_qty | ${NUMBER-20-40} |
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
      | ${bundle_name} | 0 | Piece | ${NUMBER-5-10} |
    * user place remote order

  Scenario: Verify Bundle SO Quotation

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${order_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Quotation | {"app_source":"ER Sales App","transaction_date":"${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}", "customer_name": "${wh2.customer-1-title}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "Quotation_Id"


  Scenario: Verify Bundle Sales Order
    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Sales Order | {"quotation":"${Quotation_Id}"} |
    * response status code should be 200
    * get response "message.status" string attribute and store into session "Closed"