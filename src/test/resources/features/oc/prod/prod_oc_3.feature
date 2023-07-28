@prod_3 @pranali.mulay@elastic.run
Feature: Prod OC 3 for min no of unique SKU

  Scenario: Create Customer tag from document tag doctype

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "${wh2.oc.customer-3-title}" customer details by api
    * user generate random value "${wh2.oc.customer-3-title}" and store into session "customer-title"

    * user create "Document Tag" by api
      | tag_title | ${wh2.oc.customer-3-title} | /default-payload.json |
    * response status code should be 200

  Scenario: Create Parameterized document metric
    * user create "Parameterized Document Metric" by api
      | NA | {"title":"Automation prod_oc_3"} | /prod_oc_3_pdm.json |
    * response status code should be 200

    * get response "docs[0]" object and store into session "payload"

    * user hit get api call with query param
      | with-run | /api/method/frappe.client.get                  |
      | doctype  | Parameterized Document Metric                  |
      | filters  | {"title":"Automation prod_oc_3","docstatus":1} |

    * user hit post api call with form param if record not available
      | with-run | /api/method/frappe.desk.form.save.savedocs |
      | doc      | ${payload}                                 |
      | action   | Submit                                     |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Parameterized Document Metric | {"title":"Automation prod_oc_3"} |
    * get response "message.name" string attribute and store into session "prod_oc_3_pdm"

  Scenario: Create Constrain Group
    * user create "Constraint Group" by api
      | title | Automation Prod oc 3 CG1 | /prod_oc_3_CG1-default-payload.json |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Constraint Group | {"title":"Automation Prod oc 3 CG1"} |
    * get response "message.name" string attribute and store into session "prod_oc_3_CG1"

  Scenario: Create Constrain
    * user create "Constraint" by api
      | title | Automation Prod oc 3 C1 | /prod_oc_3_C1-default-payload.json |
    * response status code should be 200

  Scenario: Prod oc-3 for 1st order validation
    * user login to the experience layer sales app with valid details
      | ${oc-wh2-se-1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh2-oc.locality} | ${wh2.oc.customer-3-title} |
    * user captures store image if available
    * user clicks on take a remote order button

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-1 | ${NUMBER-0-3} | Piece | 0 |
    * user click on cart next button
    * user get grand total
    * user verify absence of order constraint strip

    * user click on cart back button
    * user click on add more item button from cart page
    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-3 | ${NUMBER-0-3} | Piece | 0 |
    * user click on cart next button
    * user click on order constraints strip
    * user click on order constraints title "0"

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-3 | ${NUMBER-4-7} | Piece | 0 |
    * user click on cart next button
    * user verify absence of order constraint strip

    * user get grand total
    * click on place order button remote order popup
    * user wait for 5 seconds

  Scenario: Prod oc-3 for 2nd order oc should not get applicable
    * user login to the experience layer sales app with valid details
      | ${oc-wh2-se-1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh2-oc.locality} | ${wh2.oc.customer-3-title} |
    * user captures store image if available
    * user clicks on take a remote order button

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-3 | ${NUMBER-1-5} | Piece | 0 |
    * user click on cart next button
    * user verify absence of order constraint strip

    * user get grand total
    * user verify final price on cart page with "${GRAND_TOTAL_AMOUNT}" for "less" order
    * click on place order button remote order popup
    * user wait for 5 seconds
