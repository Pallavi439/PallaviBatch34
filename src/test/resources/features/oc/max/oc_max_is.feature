@oc_max_is
Feature: Order constraint max order validation for IS

  Scenario: Create Customer tag from document tag doctype

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "${customer-5-title}" customer details by api

    * user generate random value "${customer-5-title}" and store into session "customer-title"
    * user create "Document Tag" by api
      | tag_title | ${customer-5-title} | /default-payload.json |
    * response status code should be 200

  Scenario Outline: Create Parameterized document metric

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user create "Parameterized Document Metric" by api
      | NA | {"title":"Automation max order metric with <type>"} | <payload pdm> |
    * response status code should be 200

    * get response "docs[0]" object and store into session "payload"

    * user hit get api call with query param
      | with-run | /api/method/frappe.client.get                                     |
      | doctype  | Parameterized Document Metric                                     |
      | filters  | {"title":"Automation max order metric with <type>","docstatus":1} |

    * user hit post api call with form param if record not available
      | with-run | /api/method/frappe.desk.form.save.savedocs |
      | doc      | ${payload}                                 |
      | action   | Submit                                     |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Parameterized Document Metric | {"title":"Automation max order metric with <type>"} |
    * get response "message.name" string attribute and store into session "max_PDM"

    * user create "Constraint Group" by api
      | title | Automation Max Order CG2 | /max_order_CG2-default-payload.json |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Constraint Group | {"title":"Automation Max Order CG2"} |
    * get response "message.name" string attribute and store into session "max_CG"

    * user generate random value "Automation Max Order C <type>" and store into session "Automation Max Order C"

    * user create "Constraint" by api
      | title | ${Automation Max Order C} | /max_order_C2-default-payload.json |
    * response status code should be 200

    Examples:
      | payload pdm          | type  |
      | /max_order_pdm2.json | brand |
#      | /max_order_pdm3.json | item group   |
#      | /max_order_pdm4.json | er item type |

  Scenario: Order constarin for max order validation IS
    * user login to the experience layer sales app with valid details
      | ${oc-wh1-se-1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh1-oc.locality} | ${customer-5-title} |
    * user captures store image if available
    * user clicks on take a remote order button

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | ${product-brand-name-2} | ${NUMBER-1-7} | Case | 1 |
    * user click on cart next button
    * user get grand total
    * user verify final price on cart page with "${GRAND_TOTAL_AMOUNT}" for "max" order
    * user verify absence of order constraint strip
    * user click on cart back button
    * user click on add more item button from cart page

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | ${product-brand-name-1} | ${NUMBER-1-7} | Case | 3 |
    * user click on cart next button
    * user click on order constraints strip

#    * user click on order constraints title "0"
#    * user add item to cart
#      #| Item or Category Name | Index No | Uom |Quantity|
#      | ${product-brand-name-1} | ${NUMBER-1-7} | Piece | 1 |
#    * user click on cart next button
    * user click on cart back button
    * user wait for 5 seconds
    * user remove item from the cart page
    * user click on cart next button
    * user verify absence of order constraint strip
    * user click on place order button
#   # * user click on cart local place order button
#    * click on place order button remote order popup


    

