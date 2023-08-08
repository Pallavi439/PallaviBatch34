Feature: Location Geofencing is created only for Withrun App config then it will be taken under consideration for distance measure

  Scenario: set the withrun app config ->outlet geofencing to 0
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get details by frappe client get api with filters
      | Geofencing Config | {"parent":"WithRun App Config", "type":"Outlet Distance From Locality"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "outlet_distance"
    * user set value by frappe client set value api with filters and fieldname
      | Geofencing Config | ${outlet_distance} | max_distance_requires | 500 |
    * response status code should be 200

    * user wait for 330 seconds


    ###############Billing address present ,diffrence <500,local order#######################

  Scenario: Billing address present ,difference <1000,local order

    * user sets mobile geolocation
      | 18.5026 | 73.9200 |
    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user click on take order button
    * user add item to cart
      | Automation-Category-2 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place order with local
