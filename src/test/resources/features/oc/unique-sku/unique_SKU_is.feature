@uniqueis @pranali.mulay@elastic.run
Feature: Min unique no of SKU validation section IS

  Scenario: Create Customer tag from document tag doctype

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user get "${customer-7-title}" customer details by api

    * user generate random value "${customer-7-title}" and store into session "customer-title"
    * user create "Document Tag" by api
      | tag_title | ${customer-7-title} | /default-payload.json |
    * response status code should be 200

  Scenario Outline: Create Parameterized document metric

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

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

    * user create "Constraint Group" by api
      | title | Automation Min No of unique SKU CG2 | /unique_SKU_CG2-default-payload.json |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Constraint Group | {"title":"Automation Min No of unique SKU CG2"} |
    * get response "message.name" string attribute and store into session "unique_CG"

    * user generate random value "Automation Min No of unique SKU C <type>" and store into session "Automation Min No of unique SKU C"

    * user create "Constraint" by api
      | title | ${Automation Min No of unique SKU C} | /unique_SKU_C2-default-payload.json |
    * response status code should be 200

    Examples:
      | payload pdm           | type         |
      | /unique_SKU_pdm2.json | brand        |
      | /unique_SKU_pdm3.json | item group   |
      | /unique_SKU_pdm4.json | er item type |

  Scenario: Delete document tag

    * user delete doc type
      | Document Tag | ${Document Tag.name} |