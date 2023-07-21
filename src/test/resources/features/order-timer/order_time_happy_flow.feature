@order_time_happy @regression1

Feature: Happy flow for order time by placing order with specific time

  Scenario: Set warehouse config name
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
  # doctype name
    * user create "Warehouse Config Name" by api
    #field name # name for filter
      | config_name | MIN_ORDER_TIME_IN_MINS | /default-payload.json |

    * user generate random value "${Warehouse Config Name.name}" and store into session "wh_config_name"

  Scenario: Set warehouse config
    * user create "Warehouse Config" by api
      | NA | {"warehouse":"${warehouse-2}","config_name":"${wh_config_name}"} | /default-payload.json |
    * user generate random value "${Warehouse Config.name}" and store into session "wh_config"

  Scenario: Set Timer to 2

    * User set value by frappe client set value api with filters and fieldname
      | Warehouse Config | ${wh_config} | config_data | 2 |

    * user wait for 60 seconds

  Scenario: Verify order time and order placement from app side for 1st order

    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh2-sp1.locality} | Sdfgrotuyb |
    * user captures store image if available
    * user clicks on take a remote order button
    * user verifies order timer
    * user click on locality and store
      | ${wh2-sp1.locality} | Sdfgrotuyb |
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
      | ${wh2-sp1.locality} | Sdfgrotuyb |
    * user captures store image if available
    * user clicks on take a remote order button
    * user add item to cart
      | Automation-Category-1 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user click on cart button
    * user click on cart next button
    * user click on place order button
    * user click on cart remote order button
    * user verifies visibility of Store list page

  Scenario: Set warehouse config
    * user create "Warehouse Config" by api
      | NA | {"warehouse":"${warehouse-2}","config_name":"${wh_config_name}"} | /default-payload.json |
    * user generate random value "${Warehouse Config.name}" and store into session "wh_config"

  Scenario: Set Timer to 0

    * User set value by frappe client set value api with filters and fieldname
      | Warehouse Config | ${wh_config} | config_data | 0 |
    



