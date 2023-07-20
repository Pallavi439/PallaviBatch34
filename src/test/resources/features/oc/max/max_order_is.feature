@maxis @pranali.mulay@elastic.run
Feature: Max order validation section IS

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
      | payload pdm          | type         |
      | /max_order_pdm2.json | brand        |
#      | /max_order_pdm3.json | item group   |
#      | /max_order_pdm4.json | er item type |

  Scenario: Delete document tag

    * user delete doc type
      | Document Tag | ${Document Tag.name} |