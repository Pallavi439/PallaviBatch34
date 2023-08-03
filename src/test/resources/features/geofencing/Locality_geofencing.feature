@regression1 @gajender.singh@elastic.run @locality_geofencing

Feature: When Location Geofencing is created only for Locality then it will be taken under consideration for distance measure

  Scenario: Set session data for scenario
    * user generate random value "${wh2-se1}" and store into session "sales_person_mobile"
    * user generate random value "${wh2-sp1.locality}" and store into session "sales_person_locality"
    * user generate random value "${wh2-customer-1-title}" and store into session "sales_customer_name"
    * user generate random value "${wh2-sales-person-1-name}" and store into session "sales_person_name"
    * user generate random value "${wh2-sp1.locality}" and store into session "geofencing_locality_name"

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

  Scenario: Create Locality Geofencing if not available for wh2.sp1 SE
    * user create "Location Geofencing" by api
      | link_name | ${geofencing_locality_name} | /locality_geofencing.json |
    * response status code should be 200

    * user set value by frappe client set value api with filters and fieldname
      | Location Geofencing | ${Location Geofencing.name} | outlet_geofencing | 800 |

    ###############Billing address present ,diffrence <800,local order#######################

  Scenario: Billing address present ,difference <1000,local order
    * user sets mobile geolocation
      | 18.505100 | 73.9270 |
    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user click on take order button
    * user add item to cart
      | Automation-Category-2 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place order with local

  Scenario: Get Quotation Log in exp Layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${order_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Quotation | {"app_source":"ER Sales App","transaction_date":"${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_quotation_id"

  Scenario: Verify Visit log in experience layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"${sales_customer_name}","locality":"${sales_person_locality}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_customer_id"

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${sales_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-sales-api | frappe_get_report | Visit Log | {"visit_date":"${DATE-yyyy-MM-dd}","outlet_name":"${exp_customer_id}","sales_person":"${sales_person_name}","order_placed":"1","ref_docname":"${exp_quotation_id}"} |
    * response status code should be 200

  Scenario: To get the visit log in order to verify local order
    * user wait for 60 seconds
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "${sales_customer_name}" customer details by api
    * user retries and get details by frappe client get api with filters
      | Visit Log | {"sales_person":"${sales_person_name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":1"

        ###############Billing address present ,same lat long,local order#######################
  Scenario: Billing address present ,same lat long ,local order
    * user sets mobile geolocation
      | 18.5068726 | 73.9299712 |

    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user click on take order button
    * user add item to cart
      | Automation-Category-3 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place order with local

  Scenario: Get Quotation Log in exp Layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${order_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Quotation | {"app_source":"ER Sales App","transaction_date":"${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_quotation_id"

  Scenario: Verify Visit log in experience layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"${sales_customer_name}","locality":"${sales_person_locality}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_customer_id"

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${sales_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-sales-api | frappe_get_report | Visit Log | {"visit_date":"${DATE-yyyy-MM-dd}","outlet_name":"${exp_customer_id}","sales_person":"${sales_person_name}","order_placed":"1","ref_docname":"${exp_quotation_id}"} |
    * response status code should be 200

  Scenario: To get the visit log in order to verify local order
    * user wait for 60 seconds
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "${sales_customer_name}" customer details by api
    * user retries and get details by frappe client get api with filters
      | Visit Log | {"sales_person":"${sales_person_name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":1"


        ###############Billing address present ,difference>800,remote order#######################
  Scenario: Billing address present ,difference >1000,local order

    * user sets mobile geolocation
      | 18.5086 | 73.9186 |

    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image
    * user click on take order button
    * user add item to cart
      | Automation-Category-3 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place order with local

  Scenario: Get Quotation Log in exp Layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${order_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Quotation | {"app_source":"ER Sales App","transaction_date":"${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_quotation_id"

  Scenario: Verify Visit log in experience layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"${sales_customer_name}","locality":"${sales_person_locality}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_customer_id"

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${sales_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-sales-api | frappe_get_report | Visit Log | {"visit_date":"${DATE-yyyy-MM-dd}","outlet_name":"${exp_customer_id}","sales_person":"${sales_person_name}","order_placed":"1","ref_docname":"${exp_quotation_id}"} |
    * response status code should be 200

  Scenario: To get the visit log in order to verify local order
    * user wait for 60 seconds
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "${sales_customer_name}" customer details by api
    * user retries and get details by frappe client get api with filters
      | Visit Log | {"sales_person":"${sales_person_name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":0"

    ###############Shop address present ,difference>800,remote order#######################
  Scenario: Set session data for scenario
    * user generate random value "${wh2-se1}" and store into session "sales_person_mobile"
    * user generate random value "${wh2-sp1.locality}" and store into session "sales_person_locality"
    * user generate random value "${wh2-customer-3-title}" and store into session "sales_customer_name"
    * user generate random value "${wh2-sales-person-1-name}" and store into session "sales_person_name"

  Scenario: Shop address present ,difference>2000,remote order
    * user sets mobile geolocation
      | 18.5700 | 73.7570 |

    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image if available
    * user clicks on take a remote order button
    * user add item to cart
      | Automation-Category-2 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place remote order
    * user wait for 10 seconds


  Scenario: To get the visit log in order to verify remote order
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "${wh5.customer-6-title}" customer details by api
    * user get details by frappe client get api with filters
      | Visit Log | {"sales_person":"${wh5-sales-person-6-name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":0"

    ###############Shop address present ,difference<800,local order #######################
    * user sets mobile geolocation
      | 18.50900 | 73.9270 |

    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image if available
    * user click on take order button
    * user add item to cart
      | Automation-Category-3 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place order with local
    * user wait for 10 seconds

  Scenario: Get Quotation Log in exp Layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${order_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Quotation | {"app_source":"ER Sales App","transaction_date":"${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_quotation_id"


  Scenario: Verify Visit log in experience layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"${sales_customer_name}","locality":"${sales_person_locality}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_customer_id"

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${sales_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-sales-api | frappe_get_report | Visit Log | {"visit_date":"${DATE-yyyy-MM-dd}","outlet_name":"${exp_customer_id}","sales_person":"${sales_person_name}","order_placed":"1","ref_docname":"${exp_quotation_id}"} |
    * response status code should be 200

  Scenario: To get the visit log in order to verify local order
    * user wait for 60 seconds
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "${sales_customer_name}" customer details by api
    * user verifies response attribute "message.coords" should contains values "\"is_local\":0"
      | Visit Log | {"sales_person":"${sales_person_name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":1"

    ###############Shop address present ,same lat long,local order #######################
  Scenario: Shop address present ,same lat long,local order

    * user sets mobile geolocation
      | 18.5068726 | 73.9299712 |

    * user login to the experience layer sales app with valid details
      | ${sales_person_mobile} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${sales_person_locality} | ${sales_customer_name} |
    * user captures store image if available
    * user click on take order button
    * user add item to cart
      | Automation-Category-2 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place order with local
    * user wait for 10 seconds

  Scenario: Get Quotation Log in exp Layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${order_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Quotation | {"app_source":"ER Sales App","transaction_date":"${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_quotation_id"

  Scenario: Verify Visit log in experience layer
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"${sales_customer_name}","locality":"${sales_person_locality}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_customer_id"

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${sales_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-sales-api | frappe_get_report | Visit Log | {"visit_date":"${DATE-yyyy-MM-dd}","outlet_name":"${exp_customer_id}","sales_person":"${sales_person_name}","order_placed":"1","ref_docname":"${exp_quotation_id}"} |
    * response status code should be 200

  Scenario: To get the visit log in order to verify local order
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200
    * user get "${sales_customer_name}" customer details by api
    * user get details by frappe client get api with filters
      | Visit Log | {"sales_person":"${sales_person_name}","outlet_name":"${customer_id}","distance_difference":"0","visit_date":"${${DATE-yyyy-MM-dd}}"} |
    * response status code should be 200
    * user verifies response attribute "message.coords" should contains values "\"is_local\":1"


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

  Scenario:Set locality geofencing to 0
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * response status code should be 200

    * user get details by frappe client get api with filters
      | Location Geofencing | {"link_name":"${sales_person_locality}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "locality_geo_id"

    * user set value by frappe client set value api with filters and fieldname
      | Location Geofencing | ${locality_geo_id} | outlet_geofencing | 0 |
    * user set value by frappe client set value api with filters and fieldname
      | Location Geofencing | ${locality_geo_id} | outlet_onboarding_geofencing | 0 |