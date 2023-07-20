@oc_max
Feature: Order constraint

  Scenario: Create Customer tag from document tag doctype

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "${customer-11-title}" customer details by api

    * user generate random value "${customer-11-title}" and store into session "customer-title"
    * user create "Document Tag" by api
      | tag_title | ${customer-11-title} | /default-payload.json |
    * response status code should be 200

  Scenario: Create Parameterized document metric

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user create "Parameterized Document Metric" by api
      | NA | {"title":"Automation max order metric"} | /max_order_pdg1-default-payload.json |
    * response status code should be 200

    * get response "docs[0]" object and store into session "payload"

    * user hit get api call with query param
      | with-run | /api/method/frappe.client.get                         |
      | doctype  | Parameterized Document Metric                         |
      | filters  | {"title":"Automation max order metric","docstatus":1} |
    * get response "message.name" string attribute and store into session "max_pdm"

    * user hit post api call with form param if record not available
      | with-run | /api/method/frappe.desk.form.save.savedocs |
      | doc      | ${payload}                                 |
      | action   | Submit                                     |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Parameterized Document Metric | {"title":"Automation max order metric"} |
    * get response "message.name" string attribute and store into session "max_pdm"


  Scenario: Create Constrain Group

    * user create "Constraint Group" by api
      | title | Automation Max Order CG1 | /max_order_CG1-default-payload.json |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Constraint Group | {"title":"Automation Max Order CG1"} |
    * get response "message.name" string attribute and store into session "max_CG1"

  Scenario: Create Constrain

    * user create "Constraint" by api
      | title | Automation Max Order C1 | /max_order_C1-default-payload.json |
    * response status code should be 200

  Scenario: Order constarin for max order
    * user login to the experience layer sales app with valid details
      | ${oc-wh1-se-1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh1-oc.locality} | ${customer-11-title} |
    * user captures store image if available
    * user clicks on take a remote order button

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-1 | ${NUMBER-1-7} | Piece | 1 |
    * user click on cart next button
    * user get grand total
    * user verify final price on cart page with "${GRAND_TOTAL_AMOUNT}" for "lessmax" order
    * user verify absence of order constraint strip
    * user click on cart back button
    * user click on add more item button from cart page

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-4 | ${NUMBER-1-7} | Piece | 3 |
    * user click on cart next button
    * user get grand total
    * user verify final price on cart page with "${GRAND_TOTAL_AMOUNT}" for "max" order
    * user click on order constraints strip

    * user click on cart back button
    * user remove item from the cart page
    * user click on cart next button

    * user click on place order button

#   # * user click on cart local place order button
#    * click on place order button remote order popup


    

