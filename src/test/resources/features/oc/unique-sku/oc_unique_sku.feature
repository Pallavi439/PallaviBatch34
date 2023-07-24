@oc_unique @pranali.mulay@elastic.run
Feature: Min no. of unique SKU

  Scenario: Create Customer tag from document tag doctype

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "${customer-3-title}" customer details by api
    * user generate random value "${customer-3-title}" and store into session "customer-title"

    * user create "Document Tag" by api
      | tag_title | ${customer-3-title} | /default-payload.json |
    * response status code should be 200

  Scenario: Create Parameterized document metric

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user create "Parameterized Document Metric" by api
      | title | Automation min no of unique SKU metric | /unique_SKU_pdg1-default-payload.json |
    * response status code should be 200

    * get response "docs[0]" object and store into session "payload"

    * user hit get api call with query param
      | with-run | /api/method/frappe.client.get                                    |
      | doctype  | Parameterized Document Metric                                    |
      | filters  | {"title":"Automation min no of unique SKU metric","docstatus":1} |

    * user hit post api call with form param if record not available
      | with-run | /api/method/frappe.desk.form.save.savedocs |
      | doc      | ${payload}                                 |
      | action   | Submit                                     |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Parameterized Document Metric | {"title":"Automation min no of unique SKU metric"} |
    * get response "message.name" string attribute and store into session "unique_pdm"

  Scenario: Create Constrain Group
    * user create "Constraint Group" by api
      | title | Automation Min No of unique SKU CG1 | /unique_SKU_CG1-default-payload.json |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Constraint Group | {"title":"Automation Min No of unique SKU CG1"} |
    * get response "message.name" string attribute and store into session "unique_CG1"

  Scenario: Create Constrain
    * user create "Constraint" by api
      | title | Automation Min No of unique SKU C1 | /unique_SKU_C1-default-payload.json |
    * response status code should be 200

  Scenario: Minimum no of unique SKU oc

    * user login to the experience layer sales app with valid details
      | ${oc-wh1-se-1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh1-oc.locality} | ${customer-3-title} |
    * user captures store image if available
    * user clicks on take a remote order button

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-1 | ${NUMBER-0-3} | Piece | 1 |
      | Automation-Category-2 | ${NUMBER-1-7} | Piece | 1 |
    * user click on cart next button
    * user get grand total
    * user click on order constraints strip
    * user click on order constraints title "0"

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-4 | ${NUMBER-1-7} | Piece | 1 |
      | Automation-Category-3 | ${NUMBER-1-7} | Piece | 1 |
      | Automation-Category-1 | ${NUMBER-4-7} | Piece | 1 |
    * user click on cart next button
    * user get grand total
    * user verify absence of order constraint strip
    * click on place order button remote order popup