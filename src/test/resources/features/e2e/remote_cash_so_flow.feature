@e2e @remote_cash_so_flow @regression1
Feature: E2E Remote Cash Flow


  Scenario: Verify remote order placement flow

    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-1-title} |
    * user captures store image if available
    * user clicks on take a remote order button
    * user add item to cart
      | Automation-Test-Item-Name-17 | 0 | Bag | ${NUMBER-5-10} |
    * user place remote order

  Scenario: Get Quotation Log in exp Layer
    * user generate random value " " and store into session "cookie"

    * user set api headers
      | Authorization | ${order_api_token} |

    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Quotation | {"app_source":"ER Sales App","transaction_date":"${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_quotation_id"
    * user verifies response attribute "message.owner" value should be "${wh2-sales-person-1-username}"

  Scenario: Get Sales Order in exp layer
    * user set api headers
      | Authorization | ${order_api_token} |

    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Sales Order | {"quotation":"${exp_quotation_id}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_sales_order_id"
    * get response "message.remote_order" string attribute and store into session "withrun_sales_order_id"
    * get response "message.remote_quotation" string attribute and store into session "withrun_quotation_id"

  Scenario: Verify Quotation in with-run
    * user wait for 10 seconds
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "${wh2-customer-1-title}" customer details by api

    * "User" retries and get details by frappe client get api with filters
#      | Quotation | {"transaction_date": "${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}", "customer_name": "${wh2-customer-1-title}", "owner": "${wh2-sales-person-1-username}"} |
      | Quotation | {"transaction_date": "${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}", "customer_name": "${wh2-customer-1-title}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "Quotation_Id"

  Scenario: Verify Sales Order in with-run
    * "User" retries and get details by frappe client get api with filters
      | Sales Order Item | {"prevdoc_docname":"${Quotation_Id}"} |
    * response status code should be 200
    * get response "message.parent" string attribute and store into session "SalesOrder_Id"
    * "User" retries and get details by frappe client get api with filters
      | Sales Order | {"name":"${SalesOrder_Id}"} |
    * response status code should be 200
    * user verifies response attribute "message.status" value should be "To Deliver and Bill"

#  Scenario: Verify Sales invoice in with-run
#    * user creates sales invoice by api for below data
#      | ${warehouse-2} | ["${SalesOrder_Id}"] |
#    * response status code should be 200
#
#    * user retries and get details by frappe client get api with filters
#      | Sales Invoice Item | {"sales_order":"${SalesOrder_Id}"} |
#    * response status code should be 200
#    * get response "message.parent" string attribute and store into session "SALES_ORDER_INVOICE_ID"
#    * get response "message.name" string attribute and store into session "SALES_INVOICE_ITEM_ID"
#    * get response "message.item_code" string attribute and store into session "SALES_INVOICE_ITEM_CODE"
#
#    * user retries and get details by frappe client get api with filters
#      | Sales Invoice | {"name":"${SALES_ORDER_INVOICE_ID}"} |
#    * response status code should be 200
#    * get response "message.status" string attribute and store into session "Sales_Invoice_Status"
#    * get response "message.shipping_status" string attribute and store into session "Sales_Invoice_Shipping_Status"
#    * get response "message.source_warehouse" string attribute and store into session "si_warehouse"
#    * get response "message.collection_due_date" string attribute and store into session "si_collection_due_date"
#    * get response "message.rounded_total" string attribute and store into session "GRAND_TOTAL_AMOUNT"
#    * user compares actual "${Sales_Invoice_Status}" and expected "Unpaid" data
#    * user compares actual "${Sales_Invoice_Shipping_Status}" and expected "Order Confirmed" data
#
#  Scenario: Assign Sales Invoice to DA in with-run
#
#    * user assigns sales invoice to DA
#      | ["${SALES_ORDER_INVOICE_ID}"] | ${wh2-delivery-person-1-username} | /sanity-payload/assign-invoice-filters.json |
#    Then response status code should be 200
#    * user wait for 5 seconds
#
##  Verify Sales App Order status as RFP at backend
#  Scenario: Verify Sales Invoice status in with-run
#    * "User" retries and get details by frappe client get api with filters
#      | Sales Invoice | {"name":"${SALES_ORDER_INVOICE_ID}"} |
#    * response status code should be 200
#    * get response "message.status" string attribute and store into session "Sales_Invoice_Status"
#    * get response "message.shipping_status" string attribute and store into session "Sales_Invoice_Shipping_Status"
#    * user compares actual "${Sales_Invoice_Status}" and expected "Unpaid" data
#    * user compares actual "${Sales_Invoice_Shipping_Status}" and expected "Ready For Pickup" data
#
#  Scenario: Deliver Sales Invoice by API in with-run
#    * user login to application by api
#      | ${wh2-delivery-person-1-username} | ${wh2-delivery-person-1-password} |
#
##    make sales invoice oor
#    * DA makes Sales Invoice OOR by api
#      | /sanity-payload/oor-checkpoint-data.json | ${SALES_ORDER_INVOICE_ID} |
#    * response status code should be 200
#
##    deliver sales invoice
#    * DA delivers the "non-credit" Sales Invoice by api
#      | /sanity-payload/delivery-checkpoint-data.json | /sanity-payload/payment-data.txt | ${SALES_ORDER_INVOICE_ID} |
#    * response status code should be 200
#
#  Scenario: Verify Payment Entry in with-run
#    * "User" retries and get details by frappe client get api with filters
#      | Payment Entry Reference | {"reference_name":"${SALES_ORDER_INVOICE_ID}"} |
#    * response status code should be 200
#    * get response "message.parent" string attribute and store into session "Payment_Entry_ID"
#
#    * "User" retries and get details by frappe client get api with filters
#      | Payment Entry | {"name":"${Payment_Entry_ID}"} |
#    * response status code should be 200
#    * get response "message.cash_status" string attribute and store into session "Cash_Status"
#    * user compares actual "${Cash_Status}" and expected "With Associate" data
