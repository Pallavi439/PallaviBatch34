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

#    * user set value by frappe client set value api with filters and fieldname
#      | Product Bundle Bin | ${prod-bund-bin-name} | actual_qty | ${IntegerNum} |
#    * response status code should be 200

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
      | ${bundle_name} | 0 | Piece | ${NUMBER-5-10} |
    * user place remote order

  Scenario: Verify Bundle SO Quotation

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${order_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Quotation | {"app_source":"ER Sales App","transaction_date":"${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}", "customer_name": "${wh2-customer-1-title}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "Quotation_Id"


  Scenario: Verify Bundle Sales Order
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${order_api_token} |

    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Sales Order | {"quotation":"${Quotation_Id}"} |
    * response status code should be 200
    * get response "message.status" string attribute and store into session "Closed"

  Scenario: Verify Bundle SO Quotation in with-run
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * user retries and get details by frappe client get api with filters
      | Quotation | {"transaction_date": "${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}", "customer_name": "${wh2-customer-1-title}","status":"Ordered"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "Quotation_Id"

  Scenario: Verify Bundle Sales Order in with-run
    * user retries and get details by frappe client get api with filters
      | Sales Order Item | {"prevdoc_docname":"${Quotation_Id}"} |
    * response status code should be 200
    * get response "message.parent" string attribute and store into session "SALES_ORDER_ID"

    * user retries and get details by frappe client get api with filters
      | Sales Order | {"name":"${SALES_ORDER_ID}"} |
    * response status code should be 200
    * get response "message.status" string attribute and store into session "Closed"

#    Create and verify Sales Invoice
  Scenario: Create Bundle Sales Invoice
    * user creates sales invoice by api for below data
      | ${warehouse-2} | ["${SALES_ORDER_ID}"] |
    * response status code should be 200

  Scenario: Verify Bundle Sales Invoice
    * user retries and get details by frappe client get api with filters
      | Sales Invoice Item | {"sales_order":"${SALES_ORDER_ID}"} |
    * response status code should be 200
    * get response "message.parent" string attribute and store into session "SALES_ORDER_INVOICE_ID"

    * user retries and get details by frappe client get api with filters
      | Sales Invoice | {"name":"${SALES_ORDER_INVOICE_ID}"} |
    * response status code should be 200
    * get response "message.status" string attribute and store into session "Sales_Invoice_Status"
    * get response "message.shipping_status" string attribute and store into session "Sales_Invoice_Shipping_Status"
    * get response "message.source_warehouse" string attribute and store into session "si_warehouse"
    * get response "message.collection_due_date" string attribute and store into session "si_collection_due_date"
    * user compares actual "${Sales_Invoice_Status}" and expected "Unpaid" data
    * user compares actual "${Sales_Invoice_Shipping_Status}" and expected "Order Confirmed" data