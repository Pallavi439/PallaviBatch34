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
#      | Cadbury Halls @112             | 0 | Piece | ${NUMBER-1-5} |
#      | Mortein Air @94                | 0 | Piece | ${NUMBER-1-5} |
#      | Mortein Natural Coil @ 26      | 0 | Piece | ${NUMBER-1-5} |
#      | Maxo Coil @ 27                 | 0 | Piece | ${NUMBER-1-5} |
#      | Lokmangal Coriander Powder @60 | 0 | Piece | ${NUMBER-1-5} |
    * user place remote order
    * user verify visibility of order placed icon for store "${PROD_STORE_1}"
    * user goes back to home screen

  Scenario: Verify Quotation in with-run
    * user wait for 10 seconds
    * user retries and get details by frappe client get api with filters
      | Quotation | {"transaction_date": "${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}", "customer_name": "${PROD_STORE_1}", "owner": "${PROD_UAT_SE_NAME_1}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "Quotation_Id"
    * user wait for 30 seconds

  Scenario: Verify Quotation Log in exp Layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${order_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Quotation | {"app_source":"ER Sales App","transaction_date":"${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_quotation_id"
    * user verifies response attribute "message.owner" value should be "${PROD_UAT_SE_NAME_1}"

  Scenario: Verify visit log in with-run
    * user retries and get details by frappe client get api with filters
      | Visit Log | {"ref_docname":"${Quotation_Id}"} |
    * response status code should be 200

  Scenario: Verify visit log in  exp Layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${sales_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-sales-api | frappe_get_report | Visit Log | {"ref_docname":"${exp_quotation_id}"} |
    * response status code should be 200

  Scenario: Verify Sales Order
    * user retries and get details by frappe client get api with filters
      | Sales Order Item | {"prevdoc_docname":"${Quotation_Id}"} |
    * response status code should be 200
    * get response "message.parent" string attribute and store into session "SALES_ORDER_ID"

    * user retries and get details by frappe client get api with filters
      | Sales Order | {"name":"${SALES_ORDER_ID}"} |
    * response status code should be 200
    * user verifies response attribute "message.status" value should be "To Deliver and Bill"
    * get response "message.rounded_total" string attribute and store into session "ROUNDED_GT"

  Scenario: Get Sales Order in exp layer
    * user set api headers
      | Authorization | ${order_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Sales Order | {"quotation":"${exp_quotation_id}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_sales_order_id"
    * get response "message.remote_order" string attribute and store into session "withrun_sales_order_id"
    * get response "message.remote_quotation" string attribute and store into session "withrun_quotation_id"

  Scenario: Create and verify Sales Invoice for partial delivery
    * user creates sales invoice by api for below data
      | PROD-UAT - IN | ["${SALES_ORDER_ID}"] |
    * response status code should be 200

    * user retries and get details by frappe client get api with filters
      | Sales Invoice Item | {"sales_order":"${SALES_ORDER_ID}"} |
    * response status code should be 200
    * get response "message.parent" string attribute and store into session "SALES_ORDER_INVOICE_ID"
    * get response "message.parent" string attribute and store into session "OLD_SALES_ORDER_INVOICE_ID"

    * user retries and get details by frappe client get api with filters
      | Sales Invoice | {"name":"${SALES_ORDER_INVOICE_ID}"} |
    * response status code should be 200
    * get response "message.status" string attribute and store into session "Sales_Invoice_Status"
    * get response "message.shipping_status" string attribute and store into session "Sales_Invoice_Shipping_Status"
    * get response "message.source_warehouse" string attribute and store into session "si_warehouse"
    * get response "message.collection_due_date" string attribute and store into session "si_collection_due_date"
    * user compares actual "${Sales_Invoice_Status}" and expected "Unpaid" data
    * user compares actual "${Sales_Invoice_Shipping_Status}" and expected "Order Confirmed" data

  Scenario: Assign Sales Invoice to DA for partial delivery
    * user assigns sales invoice to DA
      | ["${SALES_ORDER_INVOICE_ID}"] | ${PROD_DA_1} | /sanity-payload/assign-invoice-filters.json |
    Then response status code should be 200
    * user wait for 5 seconds

  Scenario: Verify Sales Invoice status after assigned to DA for partial delivery
    * user retries and get details by frappe client get api with filters
      | Sales Invoice | {"name":"${SALES_ORDER_INVOICE_ID}"} |
    * response status code should be 200
    * get response "message.status" string attribute and store into session "Sales_Invoice_Status"
    * get response "message.shipping_status" string attribute and store into session "Sales_Invoice_Shipping_Status"
    * user compares actual "${Sales_Invoice_Status}" and expected "Unpaid" data
    * user compares actual "${Sales_Invoice_Shipping_Status}" and expected "Ready For Pickup" data

  @ignore
  Scenario: Deliver Sales Invoice for partial delivery
    * user sets "chromeEmulator" browser for execution
    * user mock geolocation
      | -4.448784 | -171.24832 | 10 |
    * user login to the delivery app by valid mobile number "${DA_MOBILE_NUMBER_1}"
    * DA delivers the Sales Invoice partially by "updating" item from DA app
    * user wait for 10 seconds

  @ignore
  Scenario: Verify Payment Entry for partial delivery
    * "User" retries and get details by frappe client get api with filters
      | Payment Entry Reference | {"reference_name":"${SALES_ORDER_INVOICE_ID}"} |
    * response status code should be 200
    * get response "message.parent" string attribute and store into session "Payment_Entry_ID"

    * "User" retries and get details by frappe client get api with filters
      | Payment Entry | {"name":"${Payment_Entry_ID}"} |
    * response status code should be 200
    * get response "message.cash_status" string attribute and store into session "Cash_Status"
    * user compares actual "${Cash_Status}" and expected "With Associate" data

    @ignore
  Scenario: close emulator
    * user sets "" browser for execution
