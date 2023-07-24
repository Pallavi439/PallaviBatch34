@oc_min_isnot @pranali.mulay@elastic.run
Feature: Min order validation section ISNOT

  Scenario Outline: Create Customer tag from document tag doctype

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "<customer>" customer details by api

    * user generate random value "<customer>" and store into session "customer-title"
    * user create "Document Tag" by api
      | tag_title | <customer> | /default-payload.json |
    * response status code should be 200

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user create "Parameterized Document Metric" by api
      | NA | {"title":"Automation min order metric isnot with <type>"} | <payload pdm> |
    * response status code should be 200

    * get response "docs[0]" object and store into session "payload"

    * user hit get api call with query param
      | with-run | /api/method/frappe.client.get                                           |
      | doctype  | Parameterized Document Metric                                           |
      | filters  | {"title":"Automation min order metric isnot with <type>","docstatus":1} |

    * user hit post api call with form param if record not available
      | with-run | /api/method/frappe.desk.form.save.savedocs |
      | doc      | ${payload}                                 |
      | action   | Submit                                     |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Parameterized Document Metric | {"title":"Automation min order metric isnot with <type>"} |
    * get response "message.name" string attribute and store into session "min_isnot_PDM"

    * user generate random value "<customer>" and store into session "customer"

    * user generate random value "Automation Min Order CG3 ISNOT with <type>" and store into session "Automation Min Order CG ISNOT"
    * user create "Constraint Group" by api
      | title | ${Automation Min Order CG ISNOT} | /min_order_isnot_CG3-default-payload.json |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Constraint Group | {"title":"${Automation Min Order CG ISNOT}"} |
    * get response "message.name" string attribute and store into session "min_isnot_CG"

    * user generate random value "Automation Min Order ISNOT C <type>" and store into session "Automation Min Order ISNOT C"

    * user generate random value "Min Order 200 with isnot <type>" and store into session "Min Order 200"

    * user create "Constraint" by api
      | title | ${Automation Min Order ISNOT C} | /min_order_isnot_C3-default-payload.json |
    * response status code should be 200

    * user login to the experience layer sales app with valid details
      | ${oc-wh1-se-1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh1-oc.locality} | <customer> |
    * user captures store image if available
    * user clicks on take a remote order button

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | <brand> | ${NUMBER-1-5} | Piece | 1 |
    * user click on cart next button
    * user get grand total
    * user verify final price on cart page with "${GRAND_TOTAL_AMOUNT}" for "less" order
    * user verify absence of order constraint strip

    * user click on cart back button
    * user click on add more item button from cart page
    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | <category> | ${NUMBER-1-5} | Piece | 1 |
    * user click on cart next button
    * user click on order constraints strip
    * user click on order constraints title "0"

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | <category> | ${NUMBER-1-5} | Case | 2 |
    * user click on cart next button
    * user get grand total
    * user verify final price on cart page with "${GRAND_TOTAL_AMOUNT}" for "min" order
    * click on place order button remote order popup

    Examples:
      | payload pdm                | type  | customer            | brand                   | category                |
      | /min_order_isnot_pdm2.json | brand | ${customer-9-title} | ${product-brand-name-1} | ${product-brand-name-2} |
      #| /min_order_isnot_pdm3.json | item group | ${customer-13-title} | ${category_3}           | ${category_1}           |
#      | /min_order_pdm4.json | er item type |
    

