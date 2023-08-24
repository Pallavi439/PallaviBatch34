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
      | sales person name           | sales person mobile |
      | ${wh2-sales-person-1-name}  | ${wh2-se1}          |
      | ${wh2-sales-person-2-name}  | ${wh2-se2}          |
      | ${wh3-sales-person-1-name}  | ${wh3-se1}          |
      | ${wh4-sales-person-1-name}  | ${wh4-se1}          |
      | ${wh5-sales-person-1-name}  | ${wh5-se1}          |
      | ${wh5-sales-person-4-name}  | ${wh5-se4}          |
      | ${wh5-sales-person-6-name}  | ${wh5-se6}          |
      | ${coin-sales-person-1-name} | ${coin-se-1}        |

  Scenario Outline: Configuring Customer <customer name> for new Sales App

    * user get details by frappe client get api with filters
      | Customer | {"customer_name":"<customer name>","locality":"<customer locality>"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "customer_id"

    * user set value by frappe client set value api with filters and fieldname
      | Customer Profile | ${customer_id} | is_customer_verified | 1 |
    * response status code should be 200

    Examples:
      | customer name              | customer locality    |
      | ${wh2-customer-1-title}    | ${wh2-sp1.locality}  |
      | ${wh2-customer-3-title}    | ${wh2-sp1.locality}  |
      | ${wh2-customer-4-title}    | ${wh2-sp1.locality}  |
      | ${wh2-customer-5-title}    | ${wh2-sp1.locality}  |
      |${wh2-customer-22-title}    | ${wh2-sp1.locality}  |
      | ${wh3-customer-1-title}    | ${wh3-sp1.locality}  |
      | ${wh4-customer-1-title}    | ${wh4-sp1.locality}  |
      | ${wh5-customer-1-title}    | ${wh5-sp1.locality}  |
      | ${wh5-customer-2-title}    | ${wh5-sp4.locality}  |
      | ${wh5-customer-6-title}    | ${wh5-sp6.locality}  |
      | ${customer-1-title}        | ${wh1-oc.locality}   |
      | ${customer-2-title}        | ${wh1-oc.locality}   |
      | ${customer-3-title}        | ${wh1-oc.locality}   |
      | ${customer-4-title}        | ${wh1-oc.locality}   |
      | ${customer-5-title}        | ${wh1-oc.locality}   |
      | ${customer-6-title}        | ${wh1-oc.locality}   |
      | ${customer-7-title}        | ${wh1-oc.locality}   |
      | ${customer-8-title}        | ${wh1-oc.locality}   |
      | ${customer-9-title}        | ${wh1-oc.locality}   |
      | ${customer-10-title}       | ${wh1-oc.locality}   |
      | ${customer-11-title}       | ${wh1-oc.locality}   |
      | ${customer-12-title}       | ${wh1-oc.locality}   |
      | ${customer-13-title}       | ${wh1-oc.locality}   |
      | ${customer-14-title}       | ${wh1-oc.locality}   |
      | ${customer-15-title}       | ${wh1-oc.locality}   |
      | ${customer-16-title}       | ${wh1-oc.locality}   |
      | ${customer-17-title}       | ${wh1-oc.locality}   |
      | ${customer-18-title}       | ${wh1-oc.locality}   |
      | ${wh2.oc.customer-1-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-2-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-3-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-4-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-5-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-6-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-7-title} | ${wh2-oc.locality}   |
      | ${wh3.oc.customer-1-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-2-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-3-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-4-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-5-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-6-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-7-title} | ${wh3-oc.locality}   |
      | ${coin-customer-1-title}   | ${coin.sp1.locality} |
      | ${coin-customer-2-title}   | ${coin.sp1.locality} |

  Scenario Outline: User syncs Billing address for customer <customer name>

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
      | customer name              | customer locality    |
      | ${wh2-customer-1-title}    | ${wh2-sp1.locality}  |
      | ${wh2-customer-3-title}    | ${wh2-sp1.locality}  |
      | ${wh2-customer-4-title}    | ${wh2-sp1.locality}  |
      | ${wh2-customer-5-title}    | ${wh2-sp1.locality}  |
      | ${wh2-customer-22-title}    | ${wh2-sp1.locality}  |
      | ${wh3-customer-1-title}    | ${wh3-sp1.locality}  |
      | ${wh4-customer-1-title}    | ${wh4-sp1.locality}  |
      | ${wh5-customer-1-title}    | ${wh5-sp1.locality}  |
      | ${wh5-customer-2-title}    | ${wh5-sp4.locality}  |
      | ${wh5-customer-6-title}    | ${wh5-sp6.locality}  |
      | ${customer-1-title}        | ${wh1-oc.locality}   |
      | ${customer-2-title}        | ${wh1-oc.locality}   |
      | ${customer-3-title}        | ${wh1-oc.locality}   |
      | ${customer-4-title}        | ${wh1-oc.locality}   |
      | ${customer-5-title}        | ${wh1-oc.locality}   |
      | ${customer-6-title}        | ${wh1-oc.locality}   |
      | ${customer-7-title}        | ${wh1-oc.locality}   |
      | ${customer-8-title}        | ${wh1-oc.locality}   |
      | ${customer-9-title}        | ${wh1-oc.locality}   |
      | ${customer-10-title}       | ${wh1-oc.locality}   |
      | ${customer-11-title}       | ${wh1-oc.locality}   |
      | ${customer-12-title}       | ${wh1-oc.locality}   |
      | ${customer-13-title}       | ${wh1-oc.locality}   |
      | ${customer-14-title}       | ${wh1-oc.locality}   |
      | ${customer-15-title}       | ${wh1-oc.locality}   |
      | ${customer-16-title}       | ${wh1-oc.locality}   |
      | ${customer-17-title}       | ${wh1-oc.locality}   |
      | ${customer-18-title}       | ${wh1-oc.locality}   |
      | ${wh2.oc.customer-1-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-2-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-3-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-4-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-5-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-6-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-7-title} | ${wh2-oc.locality}   |
      | ${wh3.oc.customer-1-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-2-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-3-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-4-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-5-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-6-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-7-title} | ${wh3-oc.locality}   |
      | ${coin-customer-1-title}   | ${coin.sp1.locality} |
      | ${coin-customer-2-title}   | ${coin.sp1.locality} |

  Scenario Outline: User syncs Shop address for customer <customer name>

    * user get details by frappe client get api with filters
      | Customer | {"customer_name":"<customer name>","locality":"<customer locality>"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "customer_id"

    * user get details by frappe client get api with filters
      | Address | {"address_title":"${customer_id}","address_type":"Shop"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "address_id"

    * user set value by frappe client set value api with filters and fieldname
      | Address | ${address_id} | address_title | ${customer_id} |
    * response status code should be 200

    Examples:
      | customer name              | customer locality    |
      | ${wh2-customer-1-title}    | ${wh2-sp1.locality}  |
      | ${wh2-customer-3-title}    | ${wh2-sp1.locality}  |
      | ${wh2-customer-4-title}    | ${wh2-sp1.locality}  |
      | ${wh2-customer-5-title}    | ${wh2-sp1.locality}  |
      | ${wh2-customer-22-title}   | ${wh2-sp1.locality}  |
      | ${wh3-customer-1-title}    | ${wh3-sp1.locality}  |
      | ${wh4-customer-1-title}    | ${wh4-sp1.locality}  |
      | ${wh5-customer-1-title}    | ${wh5-sp1.locality}  |
      | ${wh5-customer-2-title}    | ${wh5-sp4.locality}  |
      | ${wh5-customer-6-title}    | ${wh5-sp6.locality}  |
      | ${customer-1-title}        | ${wh1-oc.locality}   |
      | ${customer-2-title}        | ${wh1-oc.locality}   |
      | ${customer-3-title}        | ${wh1-oc.locality}   |
      | ${customer-4-title}        | ${wh1-oc.locality}   |
      | ${customer-5-title}        | ${wh1-oc.locality}   |
      | ${customer-6-title}        | ${wh1-oc.locality}   |
      | ${customer-7-title}        | ${wh1-oc.locality}   |
      | ${customer-8-title}        | ${wh1-oc.locality}   |
      | ${customer-9-title}        | ${wh1-oc.locality}   |
      | ${customer-10-title}       | ${wh1-oc.locality}   |
      | ${customer-11-title}       | ${wh1-oc.locality}   |
      | ${customer-12-title}       | ${wh1-oc.locality}   |
      | ${customer-13-title}       | ${wh1-oc.locality}   |
      | ${customer-14-title}       | ${wh1-oc.locality}   |
      | ${customer-15-title}       | ${wh1-oc.locality}   |
      | ${customer-16-title}       | ${wh1-oc.locality}   |
      | ${customer-17-title}       | ${wh1-oc.locality}   |
      | ${customer-18-title}       | ${wh1-oc.locality}   |
      | ${wh2.oc.customer-1-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-2-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-3-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-4-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-5-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-6-title} | ${wh2-oc.locality}   |
      | ${wh2.oc.customer-7-title} | ${wh2-oc.locality}   |
      | ${wh3.oc.customer-1-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-2-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-3-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-4-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-5-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-6-title} | ${wh3-oc.locality}   |
      | ${wh3.oc.customer-7-title} | ${wh3-oc.locality}   |
      | ${coin-customer-1-title}   | ${coin.sp1.locality} |
      | ${coin-customer-2-title}   | ${coin.sp1.locality} |

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
      | warehouse         |
      | ${warehouse-1}    |
      | ${warehouse-2}    |
      | ${warehouse-3}    |
      | ${warehouse-4}    |
      | ${warehouse-5}    |
      | ${coin-warehouse} |


  Scenario Outline: Syncing Marketplace Association for warehouse <warehouse name>
    * user get details by frappe client get api with filters
      | Marketplace Association | {"warehouse":"<warehouse name>"} |
    * get response "message.name" string attribute and store into session "association_name"

    * user set value by frappe client set value api with filters and fieldname
      | Marketplace Association | ${association_name} | warehouse | <warehouse name> |
    * response status code should be 200

    Examples:
      | warehouse name    |
      | ${warehouse-1}    |
      | ${warehouse-2}    |
      | ${warehouse-3}    |
      | ${warehouse-4}    |
      | ${warehouse-5}    |
      | ${coin-warehouse} |

  @ignore
  Scenario Outline: Remove shop address from <customer name>
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |

    * user get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"<customer name>"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "customer_id"

    * user get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Address | {"address_title":"${customer_id}","address_type":"shop"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "customer_shop_add_id"

    * user hit post api call with form param
      | experience-layer-onboarding-api | frappe_delete           |
      | doctype                         | Address                 |
      | name                            | ${customer_shop_add_id} |
    * response status code should be 200

    Examples:
      | customer name           |
      | ${wh2-customer-1-title} |