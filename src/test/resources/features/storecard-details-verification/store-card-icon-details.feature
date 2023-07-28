@store_card_icon @regression1 @gajender.singh@elastic.run
Feature: To verify icons displayed on store cart

  Scenario: User gets lat and long of customer
    * user generate random value "${wh2-customer-1-title}" and store into session "customer_name"
    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |

    * user get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"${customer_name}"} |
    * response status code should be 200
    * get response "message.latitude" string attribute and store into session "customer_latitude"
    * get response "message.longitude" string attribute and store into session "customer_longitude"
    * get response "message.mobile_no" string attribute and store into session "customer_mobile_no"

    @ignore
  Scenario: To verify Icons displayed on store card.
    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user click on beat button
    * user clicks on locality "${wh2-sp1.locality}"
    * user searches store "${customer_name}"
    * user verifies store card icons visibility
    * user wait for 5 seconds

  @ignore
  Scenario: To verify direction icon of store card.
    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user click on beat button
    * user clicks on locality "${wh2-sp1.locality}"
    * user searches store "${customer_name}"
    * user verifies store direction icon
    * user wait for 5 seconds

    Scenario: To verify Call icon of store card
      * user login to the experience layer sales app with valid details
        | ${wh2-se1} | ${common-password} |
      * user click on beat button
      * user clicks on locality "${wh2-sp1.locality}"
      * user searches store "${customer_name}"
      * user verifies store call icon