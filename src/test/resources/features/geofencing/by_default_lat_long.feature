@gajender.singh@elastic.run @default_geofencing @regression1
Feature: When all config regarding geofencing are not available in the warehouse,territory,locality and withrun app config

  Scenario:Delete locality geofencing
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get and delete record if available
      | Location Geofencing | {"link_name":"AUTOMATION-WH-5-LOCALITY-4 - 411028"} |
      | Location Geofencing | ${name}                                             |

  Scenario: Delete Territory Geofencing
    * user get and delete record if available
      | Location Geofencing | {"link_name":"AUTOMATION-WH-5-TERRITORY-1"} |
      | Location Geofencing | ${name}                                     |

  Scenario: Set Warehouse Outlet geofencing to 0
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get details by frappe client get api with filters
      | Warehouse | {"name":"${warehouse-5}"} |
    * get response "message.name" string attribute and store into session "wh5_fieldname"
    * response status code should be 200
    * user set value by frappe client set value api with filters and fieldname
      | Warehouse | ${wh5_fieldname} | outlet_geofencing | 0 |
    * response status code should be 200

  Scenario: set withrun app config to 0
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get details by frappe client get api with filters
      | Geofencing Config | {"parent":"WithRun App Config", "type":"Outlet Distance From Locality","parentfield":"geofencing_config"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "outlet_distance"
    * user set value by frappe client set value api with filters and fieldname
      | Geofencing Config | ${outlet_distance} | max_distance_requires | 0 |
    * response status code should be 200
#    * user wait for 60 seconds

     ###############Billing address present ,difference <2000,local order#######################
  Scenario: Billing address present ,difference <2000,local order
    * user sets mobile geolocation
      | 19.074284 | 72.888656 |
    * user login to the experience layer sales app with valid details
      | ${wh5-se4} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${wh5-sp4.locality} | ${wh5-customer-2-title} |
    * user captures store image
    * user click on take order button
    * user add item to cart
      | Automation-Category-2 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
      | Automation-Category-3 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place order with local

  Scenario: Verify Visit log in experience layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"${wh5-customer-2-title}","locality":"${wh5-sp4.locality}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_customer_id"

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${sales_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-sales-api | frappe_get_report | Visit Log | {"visit_date":"${DATE-yyyy-MM-dd}","outlet_name":"${exp_customer_id}","sales_person":"${wh5-sales-person-4-name}","order_placed":"1"} |
    * response status code should be 200

  Scenario: To get the visit log in with-run in order to verify local order
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "${wh5-customer-2-title}" customer details by api
    * user retries and get details by frappe client get api with filters
      | Visit Log | {"sales_person":"${wh5-sales-person-4-name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":1"


        ###############Billing address present ,same lat long,local order#######################
  Scenario: Billing address present ,same lat long ,local order
    * user sets mobile geolocation
      | 19.075984 | 72.877656 |

    * user login to the experience layer sales app with valid details
      | ${wh5-se4} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${wh5-sp4.locality} | ${wh5-customer-2-title} |
    * user captures store image
    * user click on take order button
    * user add item to cart
      | Automation-Category-2 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
      | Automation-Category-3 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place order with local

  Scenario: Verify Visit log in experience layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"${wh5-customer-2-title}","locality":"${wh5-sp4.locality}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_customer_id"

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${sales_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-sales-api | frappe_get_report | Visit Log | {"visit_date":"${DATE-yyyy-MM-dd}","outlet_name":"${exp_customer_id}","sales_person":"${wh5-sales-person-4-name}","order_placed":"1"} |
    * response status code should be 200

  Scenario: To get the visit log in order to verify local order
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "$-wh5-customer-2-title}" customer details by api
    * user retries and get details by frappe client get api with filters
      | Visit Log | {"sales_person":"${wh5-sales-person-4-name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":1"

        ###############Billing address present ,difference>2000,remote order#######################
  Scenario: Billing address present ,difference >2000,local order
    * user sets mobile geolocation
      | 19.064284 | 72.898656 |

    * user login to the experience layer sales app with valid details
      | ${wh5-se4} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${wh5-sp4.locality} | ${wh5-customer-2-title} |
    * user captures store image
    * user clicks on take a remote order button
    * user add item to cart
      | Automation-Category-2 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
      | Automation-Category-3 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place remote order

  Scenario: Verify Visit log in experience layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"${wh5-customer-2-title}","locality":"${wh5-sp4.locality}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_customer_id"

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${sales_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-sales-api | frappe_get_report | Visit Log | {"visit_date":"${DATE-yyyy-MM-dd}","outlet_name":"${exp_customer_id}","sales_person":"${wh5-sales-person-4-name}","order_placed":"1"} |
    * response status code should be 200

  Scenario: To get the visit log in order to verify remote order
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "${wh5-customer-2-title}" customer details by api
    * user retries and get details by frappe client get api with filters
      | Visit Log | {"sales_person":"${wh5-sales-person-4-name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":0"

    ##############Shop address present ,difference>2000,remote order#######################
  Scenario: Shop address present ,difference>2000,remote order
    * user sets mobile geolocation
      | 18.5700 | 73.7570 |

    * user login to the experience layer sales app with valid details
      | ${wh5-se6} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${wh5-sp6.locality} | ${wh5-customer-6-title} |
    * user captures store image if available
    * user clicks on take a remote order button
    * user add item to cart
      | Automation-Category-2 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
      | Automation-Category-3 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place remote order
    * user wait for 10 seconds

  Scenario: To get the visit log in order to verify remote order
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "${wh5.customer-6-title}" customer details by api
    * user retries and get details by frappe client get api with filters
      | Visit Log | {"sales_person":"${wh5-sales-person-6-name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":0"

    ###############Shop address present ,difference<2000,local order #######################
  Scenario: Shop address present ,difference<2000,local order

    * user sets mobile geolocation
      | 18.5900 | 73.7970 |

    * user login to the experience layer sales app with valid details
      | ${wh5-se6} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${wh5-sp6.locality} | ${wh5-customer-6-title} |
    * user captures store image if available
    * user click on take order button
    * user add item to cart
      | Automation-Category-2 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
      | Automation-Category-3 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place order with local
    * user wait for 10 seconds

  Scenario: To get the visit log in order to verify local order
    * user wait for 30 seconds
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "${wh5.customer-6-title}" customer details by api
    * user verifies response attribute "message.coords" should contains values "\"is_local\":0"
      | Visit Log | {"sales_person":"${wh5-sales-person-6-name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":1"

    ###############Shop address present ,same lat long,local order #######################
  Scenario: Shop address present ,same lat long,local order

    * user sets mobile geolocation
  |18.5964049|73.7939332|

    * user login to the experience layer sales app with valid details
      | ${wh5-se6} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${wh5-sp6.locality} | ${wh5-customer-6-title} |
    * user captures store image if available
    * user click on take order button
    * user add item to cart
      | Automation-Category-2 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
      | Automation-Category-3 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place order with local
    * user wait for 10 seconds


  Scenario: To get the visit log in order to verify local order
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "${wh5.customer-6-title}" customer details by api
    * user get details by frappe client get api with filters
      | Visit Log | {"sales_person":"${wh5-sales-person-6-name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":1"


  Scenario: Delete Territory Geofencing doctype
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user delete doc type
      | Location Geofencing | Territory-AUTOMATION-WH-5-TERRITORY-1 |
    * response status code should be 202


  Scenario: Reset the withrun app config ->outlet geofencing to 2000
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get details by frappe client get api with filters
      | Geofencing Config | {"parent":"WithRun App Config", "type":"Outlet Distance From Locality"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "outlet_distance"
    * user set value by frappe client set value api with filters and fieldname
      | Geofencing Config | ${outlet_distance} | max_distance_requires | 2000 |
    * response status code should be 200