@sales-mark-visit

Feature: User verifies sales person mark visit functionality

  Scenario: User gets latitude and longitude for Customer
    * user generate random value " " and store into session "cookie"

    * user set api headers
      | Authorization | token ${onboarding_api_key}:${onboarding_api_secret} |

    * user retries and get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"${wh3-customer-1-title}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_customer_id"
    * get response "message.latitude" string attribute and store into session "exp_customer_latitude"
    * get response "message.longitude" string attribute and store into session "exp_customer_longitude"
    * user verifies response "message.latitude" attribute value should not be null
    * user verifies response "message.longitude" attribute value should not be null

  Scenario: User mark visit the customer
    * user sets mobile geolocation
      |${exp_customer_latitude}|${exp_customer_longitude}|

    * user login to the experience layer sales app with valid details
      | ${wh3-se1} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user mark visit "${wh3-sp1.locality}" locality "${wh3-customer-1-title}" store with reason "${NUMBER-0-7}"
    * user wait for 10 seconds