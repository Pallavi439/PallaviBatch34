@gajender.singh@elastic.run @territory_geofencing @regression2
Feature: When Location Geofencing is created only for Territory then it will be taken under consideration for distance measure

  @somee
  Scenario: Set session data for scenario
    * user generate random value "${wh4-se1}" and store into session "sales_person_mobile"
    * user generate random value "${wh4-sp1.locality}" and store into session "sales_person_locality"
    * user generate random value "${wh4-customer-1-title}" and store into session "sales_customer_name"
    * user generate random value "${wh4-sales-person-1-name}" and store into session "sales_person_name"
    * user generate random value "${wh4-territory-1}" and store into session "geofencing_territory_name"
    * user generate random value "${wh4-territory-1}" and store into session "geofencing_location_name"

  @somee
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

  @somee
  Scenario: Create Territory Geofencing if not available
    * user generate random value "Territory" and store into session "geo_link_type"
    * user generate random value "1000" and store into session "geo_distance"
    * user create "Location Geofencing" by api
      | link_name | ${geofencing_territory_name} | /location_geofencing.json |
    * response status code should be 200

    * user set value by frappe client set value api with filters and fieldname
      | Location Geofencing | ${Location Geofencing.name} | outlet_geofencing | 800 |


 ###############Billing address present ,difference <1000,local order#######################
  @somee
  Scenario: Billing address present ,difference <1000,local order

    * user sets mobile geolocation
      | 18.5016 | 73.9270 |
    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user click on take order button

    ###############Billing address present ,difference>1000,remote order#######################
  Scenario: Billing address present ,difference >1000,remote order
    * user sets mobile geolocation
      | 18.5016 | 73.9370 |

    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user clicks on take a remote order button

        ##############Shop address present ,difference>1000,remote order#######################
  Scenario: Set session data for scenario
    * user generate random value "${wh4-se1}" and store into session "sales_person_mobile"
    * user generate random value "${wh4-sp1.locality}" and store into session "sales_person_locality"
    * user generate random value "${wh4-customer-1-title}" and store into session "sales_customer_name"
    * user generate random value "${wh4-sales-person-1-name}" and store into session "sales_person_name"
    * user generate random value "${wh4-territory-1}" and store into session "geofencing_territory_name"
    * user generate random value "${wh4-territory-1}" and store into session "geofencing_location_name"

  Scenario: Shop address present ,difference>1000,remote order
    * user sets mobile geolocation
      | 18.5016 | 73.9370 |

    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user clicks on take a remote order button

    ###############Shop address present ,difference<1000,local order #######################
  Scenario: Shop address present ,difference>1000,local order

    * user sets mobile geolocation
      | 18.5016 | 73.9270 |

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