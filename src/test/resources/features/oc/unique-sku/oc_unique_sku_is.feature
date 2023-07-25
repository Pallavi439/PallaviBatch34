@oc_unique_is @pranali.mulay@elastic.run @oc
Feature: Min unique no of SKU validation section IS

  Scenario Outline: Create Customer tag from document tag doctype

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "<customer>" customer details by api

    * user generate random value "<customer>" and store into session "customer-title"
    * user create "Document Tag" by api
      | tag_title | <customer> | /default-payload.json |
    * response status code should be 200

    * user create "Parameterized Document Metric" by api
      | NA | {"title":"Automation min no of unique SKU metric with <type>"} | <payload pdm> |
    * response status code should be 200

    * get response "docs[0]" object and store into session "payload"

    * user hit get api call with query param
      | with-run | /api/method/frappe.client.get                                                |
      | doctype  | Parameterized Document Metric                                                |
      | filters  | {"title":"Automation min no of unique SKU metric with <type>","docstatus":1} |

    * user hit post api call with form param if record not available
      | with-run | /api/method/frappe.desk.form.save.savedocs |
      | doc      | ${payload}                                 |
      | action   | Submit                                     |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Parameterized Document Metric | {"title":"Automation min no of unique SKU metric with <type>"} |
    * get response "message.name" string attribute and store into session "unique_PDM"

    * user generate random value "<customer>" and store into session "customer"

    * user generate random value "Automation Min No of unique SKU CG2 with <type>" and store into session "Automation Min No of unique SKU CG"
    * user create "Constraint Group" by api
      | title | ${Automation Min No of unique SKU CG} | /unique_SKU_CG2-default-payload.json |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Constraint Group | {"title":"${Automation Min No of unique SKU CG}"} |
    * get response "message.name" string attribute and store into session "unique_CG"

    * user generate random value "Automation Min No of unique SKU C <type>" and store into session "Automation Min No of unique SKU C"

    * user generate random value "Min no of unique SKU IS with <type>" and store into session "Min Unique SKU 5"

    * user create "Constraint" by api
      | title | ${Automation Min No of unique SKU C} | /unique_SKU_C2-default-payload.json |
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
    * user verify absence of order constraint strip

    * user go back to previous screen
    * user click on add more item button from cart page
    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | <category1> | ${NUMBER-4-7} | Piece | 1 |
      | <category2> | ${NUMBER-0-3} | Piece | 1 |
      | <category3> | ${NUMBER-0-3} | Piece | 1 |
    * user click on cart next button
    * user click on order constraints strip
    * user click on order constraints title "0"

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | <category1> | ${NUMBER-0-3} | Piece | 1 |
      | <category3> | ${NUMBER-4-7} | Piece | 1 |
    * user click on cart next button
    * user get grand total
    * user verify absence of order constraint strip
    * click on place order button remote order popup

    Examples:
      | payload pdm           | type       | customer             | brand                   | category1               | category2               | category3             |
      | /unique_SKU_pdm2.json | brand      | ${customer-7-title}  | ${product-brand-name-2} | ${product-brand-name-1} | Automation-Category-4   | Automation-Category-3 |
      | /unique_SKU_pdm3.json | item group | ${customer-14-title} | ${category_1}           | ${category_3}           | ${product-brand-name-2} | Automation-Category-4 |
#      | /unique_SKU_pdm4.json | er item type |
    

