@oc_min_is
Feature: Min order validation section IS

  Scenario: Create Customer tag from document tag doctype

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "${customer-6-title}" customer details by api

    * user generate random value "${customer-6-title}" and store into session "customer-title"
    * user create "Document Tag" by api
      | tag_title | ${customer-6-title} | /default-payload.json |
    * response status code should be 200

  Scenario Outline: Create Parameterized document metric

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user create "Parameterized Document Metric" by api
      | NA | {"title":"Automation min order metric with <type>"} | <payload pdm> |
    * response status code should be 200

    * get response "docs[0]" object and store into session "payload"

    * user hit get api call with query param
      | with-run | /api/method/frappe.client.get                                     |
      | doctype  | Parameterized Document Metric                                     |
      | filters  | {"title":"Automation min order metric with <type>","docstatus":1} |

    * user hit post api call with form param if record not available
      | with-run | /api/method/frappe.desk.form.save.savedocs |
      | doc      | ${payload}                                 |
      | action   | Submit                                     |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Parameterized Document Metric | {"title":"Automation min order metric with <type>"} |
    * get response "message.name" string attribute and store into session "min_PDM"

    * user create "Constraint Group" by api
      | title | Automation Min Order CG3 | /min_order_CG3-default-payload.json |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Constraint Group | {"title":"Automation Min Order CG3"} |
    * get response "message.name" string attribute and store into session "min_CG"

    * user generate random value "Automation Min Order C <type>" and store into session "Automation Min Order C"

    * user create "Constraint" by api
      | title | ${Automation Min Order C} | /min_order_C3-default-payload.json |
    * response status code should be 200

    Examples:
      | payload pdm          | type  |
      | /min_order_pdm2.json | brand |
#      | /min_order_pdm3.json | item group   |
#      | /min_order_pdm4.json | er item type |

  Scenario: oc-1
    * user login to the experience layer sales app with valid details
      | ${oc-wh1-se-1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh1-oc.locality} | ${customer-6-title} |
    * user captures store image if available
    * user clicks on take a remote order button

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | ${product-brand-name-2} | ${NUMBER-1-5} | Piece | 1 |
    * user click on cart next button
    * user get grand total
    * user verify final price on cart page with "${GRAND_TOTAL_AMOUNT}" for "less" order
    * user verify absence of order constraint strip

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | ${product-brand-name-1} | ${NUMBER-1-5} | Piece | 1 |
    * user click on cart next button
    * user click on order constraints strip
    * user click on order constraints title "0"

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | ${product-brand-name-1} | ${NUMBER-1-5} | Case | 2 |
    * user click on place order button
    * user get grand total
    * user verify final price on cart page with "${GRAND_TOTAL_AMOUNT}" for "min" order
    * click on place order button remote order popup


    

