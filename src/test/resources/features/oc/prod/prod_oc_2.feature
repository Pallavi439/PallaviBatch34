@prod_2 @pranali.mulay@elastic.run
Feature: Prod OC 2

  Scenario: Create Customer tag from document tag doctype

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "${wh2.oc.customer-2-title}" customer details by api
    * user generate random value "${wh2.oc.customer-2-title}" and store into session "customer-title"

    * user create "Document Tag" by api
      | tag_title | ${wh2.oc.customer-2-title} | /default-payload.json |
    * response status code should be 200

  Scenario: Create Parameterized document metric
    * user create "Parameterized Document Metric" by api
      | NA | {"title":"Automation prod_oc_2"} | /prod_oc_2_pdm.json |
    * response status code should be 200

    * get response "docs[0]" object and store into session "payload"

    * user hit get api call with query param
      | with-run | /api/method/frappe.client.get                  |
      | doctype  | Parameterized Document Metric                  |
      | filters  | {"title":"Automation prod_oc_2","docstatus":1} |

    * user hit post api call with form param if record not available
      | with-run | /api/method/frappe.desk.form.save.savedocs |
      | doc      | ${payload}                                 |
      | action   | Submit                                     |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Parameterized Document Metric | {"title":"Automation prod_oc_2"} |
    * get response "message.name" string attribute and store into session "prod_oc_2_pdm"

  Scenario: Create Constrain Group
    * user create "Constraint Group" by api
      | title | Automation Prod oc 2 CG1 | /prod_oc_2_CG1-default-payload.json |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Constraint Group | {"title":"Automation Prod oc 2 CG1"} |
    * get response "message.name" string attribute and store into session "prod_oc_2_CG1"

  Scenario: Create Constrain
    * user create "Constraint" by api
      | title | Automation Prod oc 2 C1 | /prod_oc_2_C1-default-payload.json |
    * response status code should be 200

  Scenario: Delete document tag
    * user delete doc type
      | Document Tag | ${Document Tag.name} |