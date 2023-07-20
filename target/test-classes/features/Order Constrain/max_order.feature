@bbb @pranali.mulay@elastic.run
Feature: Max order

  Scenario: Create Customer tag from document tag doctype

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "${customer-2-title}" customer details by api

    * user generate random value "${customer-2-title}" and store into session "customer-title"
    * user create "Document Tag" by api
      | tag_title | ${customer-2-title} | /default-payload.json |
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

  Scenario: Delete document tag

    * user delete doc type
      | Document Tag | ${Document Tag.name} |