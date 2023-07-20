@nt @pranali.mulay@elastic.run
Feature: Min order for not applicable for customer tag

  Scenario: Create Customer tag from document tag doctype

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "${wh3.oc.customer-1-title}" customer details by api

    * user generate random value "${wh3.oc.customer-1-title}" and store into session "customer-title"

    * user create "Document Tag" by api
      | tag_title | ${wh3.oc.customer-1-title} | /default-payload.json |
    * response status code should be 200

  Scenario: Create Parameterized document metric
    * user create "Parameterized Document Metric" by api
      | NA | {"title":"Automation min order metric"} | /min_order_pdg1-default-payload.json |
    * response status code should be 200

    * get response "docs[0]" object and store into session "payload"

    * user hit get api call with query param
      | with-run | /api/method/frappe.client.get                         |
      | doctype  | Parameterized Document Metric                         |
      | filters  | {"title":"Automation min order metric","docstatus":1} |

    * user hit post api call with form param if record not available
      | with-run | /api/method/frappe.desk.form.save.savedocs |
      | doc      | ${payload}                                 |
      | action   | Submit                                     |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Parameterized Document Metric | {"title":"Automation min order metric"} |
    * get response "message.name" string attribute and store into session "min_pdm"

  Scenario: Create Constrain Group
    * user create "Constraint Group" by api
      | title | Automation Min Order CG2 | /min_order_CG2-default-payload.json |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Constraint Group | {"title":"Automation Min Order CG2"} |
    * get response "message.name" string attribute and store into session "min_CG2"

  Scenario: Create Constrain
    * user create "Constraint" by api
      | title | Automation Min Order C2 | /min_order_C2-default-payload.json |
    * response status code should be 200

  Scenario: Delete document tag
    * user delete doc type
      | Document Tag | ${Document Tag.name} |

