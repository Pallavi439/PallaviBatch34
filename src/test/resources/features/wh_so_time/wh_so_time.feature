@wh_so_time @regression1

Feature: WH SO time

  Scenario Outline: Validate WH SO Start Time: <StartTime> and End Time: <EndTime>

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * User set value by frappe client set value api with filters and fieldname
      | Warehouse | ${warehouse-3} | sales_order_start_time | <StartTime> |
    And response status code should be 200

    * User set value by frappe client set value api with filters and fieldname
      | Warehouse | ${warehouse-3} | sales_order_end_time | <EndTime> |
    And response status code should be 200

    And user refresh the cache
    And user wait for 5 seconds

#    verify start and end time by api
    * user hit get_beat api and get time
      | ${wh3-sales-person-1-username} | ${wh3-sales-person-1-password} |

    * user verifies response attribute "message.warehouse_config_map.AUTOMATION-WH-3 - ER.sales_order_start_time" value should be "<StartTime>"
    * user verifies response attribute "message.warehouse_config_map.AUTOMATION-WH-3 - ER.sales_order_end_time" value should be "<EndTime>"

    * user get details by frappe client get api with filters
      | Marketplace Association | {"warehouse":"${warehouse-3}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "association_name"

    * User set value by frappe client set value api with filters and fieldname
      | Marketplace Association | ${association_name} | warehouse | ${warehouse-3} |
    * response status code should be 200

#    * user wait for 60 seconds

    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user click on beat button
    * user reset store for "${wh3-sp1.locality}"
    * user click on locality and store
      | ${wh3-sp1.locality} | ${wh3-customer-1-title} |
    * user captures store image
    * user clicks on take a remote order button
    * user add item to cart
      | Automation-Category-1       | ${NUMBER-1-5} | Bag  | ${NUMBER-5-10} |
      | Automation-Test-Item-Name-1 | 0             | Case | ${NUMBER-5-10} |
    * user click on cart next button
    * user click on place order button
    * user verifies presence of cart warehouse cutoff time
    * user removes all item from cart


    Examples:
      | Location | StartTime | EndTime  |
      | Start    | 20:00:00  | 21:00:00 |
#      | End      | 2:00:00   | 3:00:00  |
#      | Start    | 20:00:00  | 0:00:00  |
#      | End      | 0:00:00   | 3:00:00  |
#      | Zero     | 0:00:00   | 0:00:00  |
#      | Mid      | 6:00:00   | 21:30:00 |


  Scenario Outline: Validate WH SO Start Time: <StartTime> and End Time: <EndTime>

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |

    * User set value by frappe client set value api with filters and fieldname
      | Warehouse | ${warehouse-3} | sales_order_start_time | <StartTime> |
    And response status code should be 200

    * User set value by frappe client set value api with filters and fieldname
      | Warehouse | ${warehouse-3} | sales_order_end_time | <EndTime> |
    And response status code should be 200

    And user refresh the cache
    And user wait for 5 seconds

#    verify start and end time by api
    * user hit get_beat api and get time
      | ${wh3-sales-person-1-username} | ${wh3-sales-person-1-password} | ${warehouse-3} |
    * user verifies response "message.warehouse_config_map.AUTOMATION-WH-3 - ER.sales_order_start_time" attribute value should be null
    * user verifies response "message.warehouse_config_map.AUTOMATION-WH-3 - ER.sales_order_end_time" attribute value should be null

    * user wait for 60 seconds

    * user click on beat button
    * user reset store for "${wh3-sp1.locality}"
    * user click on locality and store
      | ${wh3-sp1.locality} | ${wh3-customer-1-title} |
    * user captures store image
    * user clicks on take a remote order button
    * user add item to cart
      | Automation-Category-2       | Bag  | 7  |
      | Automation-Test-Item-Name-1 | Case | 10 |
    * user place remote order

    Examples:
      | Location | StartTime | EndTime |
      | Mid      |           |         |