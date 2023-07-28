@new-sales-md @gajender.singh@elastic.run

Feature: Configuring Sales person, Warehouse, Customer and Marketplace for new flutter sales app.

  Scenario: User login to with-run
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    And response status code should be 200

  Scenario Outline: Configuring Sales person <sales person name> for new Sales App
    * user retries and get details by frappe client get api with filters
      | Sales Person Config | {"sales_person":"<sales person name>","mobile_no":"<sales person mobile>"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "sales_person_config_name"

    * user set value by frappe client set value api with filters and fieldname
      | Sales Person Config | ${sales_person_config_name} | enabled | 1 |
    * response status code should be 200

    * user set value by frappe client set value api with filters and fieldname
      | User | ${sales_person_config_name} | enabled | 1 |
    * response status code should be 200

    * user set value by frappe client set value api with filters and fieldname
      | Sales Person | <sales person name> | enabled | 1 |
    * response status code should be 200

    Examples:
      | sales person name          | sales person mobile |
      | ${wh2-sales-person-1-name} | ${wh2-se1}          |
      | ${wh2-sales-person-2-name} | ${wh2-se2}          |
      | ${wh3-sales-person-1-name} | ${wh3-se1}          |
      | ${wh5-sales-person-1-name} | ${wh5-se1}          |
      | ${wh5-sales-person-4-name} | ${wh5-se4}          |
      | ${wh5-sales-person-6-name} | ${wh5-se6}          |

  Scenario Outline: Configuring Customer <customer name> for new Sales App

    * user get details by frappe client get api with filters
      | Customer | {"customer_name":"<customer name>","locality":"<customer locality>"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "customer_id"

    * user set value by frappe client set value api with filters and fieldname
      | Customer Profile | ${customer_id} | is_customer_verified | 1 |
    * response status code should be 200

    Examples:
      | customer name           | customer locality   |
      | ${wh2-customer-1-title} | ${wh2-sp1.locality} |
      | ${wh2-customer-3-title} | ${wh2-sp1.locality} |
      | ${wh2-customer-4-title} | ${wh2-sp1.locality} |
      | ${wh2-customer-5-title} | ${wh2-sp1.locality} |
      | ${wh3-customer-1-title} | ${wh3-sp1.locality} |
      | ${wh5-customer-1-title} | ${wh5-sp1.locality} |
      | ${wh5-customer-2-title} | ${wh5-sp4.locality} |
      | ${wh5-customer-6-title} | ${wh5-sp6.locality} |

  Scenario Outline: User syncs address for customer <customer name>

    * user get details by frappe client get api with filters
      | Customer | {"customer_name":"<customer name>","locality":"<customer locality>"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "customer_id"

    * user get details by frappe client get api with filters
      | Address | {"address_title":"${customer_id}","address_type":"Billing"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "address_id"

    * user set value by frappe client set value api with filters and fieldname
      | Address | ${address_id} | address_title | ${customer_id} |
    * response status code should be 200

    Examples:
      | customer name           | customer locality   |
      | ${wh2-customer-1-title} | ${wh2-sp1.locality} |
      | ${wh2-customer-3-title} | ${wh2-sp1.locality} |
      | ${wh2-customer-4-title} | ${wh2-sp1.locality} |
      | ${wh2-customer-5-title} | ${wh2-sp1.locality} |
      | ${wh3-customer-1-title} | ${wh3-sp1.locality} |
      | ${wh5-customer-1-title} | ${wh5-sp1.locality} |
      | ${wh5-customer-2-title} | ${wh5-sp4.locality} |
      | ${wh5-customer-6-title} | ${wh5-sp6.locality} |

  Scenario Outline: User updates basic warehouse configs for warehouse <warehouse>
    * user generate random value "<warehouse>" and store into session "warehouse_id"
    * user generate random value "MIN_ORDER_TIME_IN_MINS" and store into session "conf_name"

    * user create "Warehouse Config" by api
      | NA | {"warehouse":"${warehouse_id}","config_name":"${conf_name}"} | /new-sales-md-config.json |

    * user set value by frappe client set value api with filters and fieldname
      | Warehouse Config | ${Warehouse Config.name} | config_data | 0 |
    * response status code should be 200

    * user generate random value "<warehouse>" and store into session "warehouse_id"
    * user generate random value "First Order Local" and store into session "conf_name"

    * user create "Warehouse Config" by api
      | NA | {"warehouse":"${warehouse_id}","config_name":"${conf_name}"} | /new-sales-md-config.json |

    * user set value by frappe client set value api with filters and fieldname
      | Warehouse Config | ${Warehouse Config.name} | config_data | 0 |
    * response status code should be 200
    Examples:
      | warehouse      |
      | ${warehouse-1} |
      | ${warehouse-2} |
      | ${warehouse-3} |
      | ${warehouse-4} |
      | ${warehouse-5} |


  Scenario Outline: Syncing Marketplace Association for warehouse <warehouse name>
    * user get details by frappe client get api with filters
      | Marketplace Association | {"warehouse":"<warehouse name>"} |
    * get response "message.name" string attribute and store into session "association_name"

    * user set value by frappe client set value api with filters and fieldname
      | Marketplace Association | ${association_name} | warehouse | <warehouse name> |
    * response status code should be 200

    Examples:
      | warehouse name |
      | ${warehouse-1} |
      | ${warehouse-2} |
      | ${warehouse-3} |
      | ${warehouse-4} |
      | ${warehouse-5} |