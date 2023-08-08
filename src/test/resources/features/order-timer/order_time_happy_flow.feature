@order_time_happy @regression1 @gajender.singh@elastic.run

Feature: Happy flow for order time by placing order with specific time

  Scenario: Set warehouse config name
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * user generate random value "MIN_ORDER_TIME_IN_MINS" and store into session "conf_name"

    * user create "Warehouse Config Name" by api
      | config_name | MIN_ORDER_TIME_IN_MINS | /default-payload.json |

  Scenario: Set warehouse config
    * user generate random value "${warehouse-2}" and store into session "warehouse_id"
    * user create "Warehouse Config" by api
      | NA | {"warehouse":"${warehouse_id}","config_name":"${conf_name}"} | /new-sales-md-config.json |
    * user generate random value "${Warehouse Config.name}" and store into session "wh_config"

  Scenario: Set Timer to 2

    * User set value by frappe client set value api with filters and fieldname
      | Warehouse Config | ${wh_config} | config_data | 2 |

    * user wait for 330 seconds

  Scenario: Verify order time and order placement from app side for 1st order

    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-4-title} |
    * user captures store image if available
    * user clicks on take a remote order button
    * user verifies order timer
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-4-title} |
    * user captures store image if available
    * user clicks on take a remote order button
    * user add item to cart
      | Automation-Category-1 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user click on cart next button
    * user click on place order button
    * user verifies presence of minimum time spent

    * user wait for 120 seconds on cart page
    * user place remote order


  Scenario: Verify order time and order placement from app side for 2nd order

    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-4-title} |
    * user captures store image if available
    * user clicks on take a remote order button
    * user add item to cart
      | Automation-Category-1 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user click on cart next button
    * user click on place order button
    * user click on cart remote order button
    * user verifies visibility of Store list page

  Scenario: Set warehouse config
    * user create "Warehouse Config" by api
      | NA | {"warehouse":"${warehouse_id}","config_name":"${conf_name}"} | /new-sales-md-config.json |
    * user generate random value "${Warehouse Config.name}" and store into session "wh_config"


  Scenario: Set Timer to 0

    * User set value by frappe client set value api with filters and fieldname
      | Warehouse Config | ${wh_config} | config_data | 0 |

    * user wait for 330 seconds