@order_time_happy

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
    * user captures store image
    * user clicks on take a remote order button
    * user verifies order timer
    * user reset store for "${wh2-sp1.locality}"
    * user click on locality and store
      | ${wh2-sp1.locality} | Sdfgrotuyb |
    * user click on take order button
    * user clicks on take a remote order button
    * user add items to cart
      | Automation-Category-2 | Bag | 7 |
    *


