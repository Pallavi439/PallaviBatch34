@regression2 @gajender.singh@elastic.run
Feature: When Location Geofencing is created only for Warehouse then it will be taken under consideration for distance measure

  Scenario: Set session data for scenario
    * user generate random value "${wh5-se1}" and store into session "sales_person_mobile"
    * user generate random value "${wh5-sp1.locality}" and store into session "sales_person_locality"
    * user generate random value "${wh5-customer-1-title}" and store into session "sales_customer_name"
    * user generate random value "${wh5-sales-person-1-name}" and store into session "sales_person_name"
    * user generate random value "${wh5-territory-1}" and store into session "geofencing_territory_name"
    * user generate random value "${wh5-territory-1}" and store into session "geofencing_location_name"

  Scenario: set the withrun app config ->outlet geofencing to 0
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Geofencing Config | {"parent":"WithRun App Config", "type":"Outlet Distance From Locality"} |
    * response status code should be 200

    * get response "message.name" string attribute and store into session "outlet_distance"

    * user set value by frappe client set value api with filters and fieldname
      | Geofencing Config | ${outlet_distance} | max_distance_requires | 0 |
    * response status code should be 200


  Scenario: Set Outlet Geofencing Value for Warehouse
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get details by frappe client get api with filters
      | Warehouse | {"name":"${sales_person_warehouse}"} |
    * get response "message.name" string attribute and store into session "wh_fieldname"
    * response status code should be 200
    * user set value by frappe client set value api with filters and fieldname
      | Warehouse | ${wh_fieldname} | outlet_geofencing | 1500 |
    * response status code should be 200

    ###############Billing address present ,difference <1500,local order#######################
  Scenario: Billing address present ,difference <1500,local order

    * user sets mobile geolocation
      | 18.5066 | 73.9400 |
    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user click on take order button

        ###############Billing address present ,same lat long,local order#######################
  Scenario: Billing address present ,same lat long ,local order
    * user sets mobile geolocation
      | 18.50 | 73.92 |
    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user click on take order button

        ###############Billing address present ,diffrence>1500,remote order#######################
  Scenario: Billing address present ,diffrence >1500,local order
    * user sets mobile geolocation
      | 18.5076 | 73.9900 |
    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user clicks on take a remote order button

    ###############Shop address present ,difference>1500,remote order#######################

  Scenario: Shop address present ,difference>1500,remote order
    * user sets mobile geolocation
      | 18.5076 | 73.9900 |
    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user clicks on take a remote order button

    ###############Shop address present ,difference<1500,local order #######################
  Scenario: Shop address present ,difference>1500,local order
    * user sets mobile geolocation
      | 18.5076 | 73.9400 |
    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user click on take order button

    ###############Shop address present ,same lat long,local order #######################
  Scenario: Shop address present ,same lat long,local order
    * user sets mobile geolocation
      | 18.5000 | 73.9200 |
    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user click on take order button