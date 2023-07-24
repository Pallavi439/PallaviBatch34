@sales-mark-visit @regression1

Feature: User verifies sales person mark visit functionality

  Scenario: User mark visit the customer
    * user sets mobile geolocation
      | 18.6011408 | 73.7807528 |

    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user mark visit "${wh2-sp1.locality}" locality store
      | ${wh2-customer-1-title} | ${NUMBER-0-7} |
    * user wait for 10 seconds

    Scenario: Resets sales person geolocation
      * user sets mobile geolocation
        | 98.6011408 | 13.7807528 |


  Scenario: Verify Visit log in experience layer
    * user generate random value " " and store into session "cookie"

    * user set api headers
      | Authorization | ${onboarding_api_token} |

    * user retries and get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"${wh2-customer-1-title}","locality":"${wh2-sp1.locality}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_customer_id"

    * user generate random value " " and store into session "cookie"

    * user set api headers
      | Authorization | ${sales_api_token} |

    * user retries and get details by frappe client get api with filters
      | experience-layer-sales-api | frappe_get_report | Visit Log | {"visit_date":"${DATE-yyyy-MM-dd}","ref_docname":"${exp_customer_id}","reason":"${mark_visit_reason}","sales_person":"${wh2-sales-person-1-name}"} |
    * response status code should be 200

  Scenario: verify visit log in with-run
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    And response status code should be 200

    * user get "${wh2-customer-1-title}" customer details by api

    * user retries and get details by frappe client get api with filters
      | Visit Log | {"reason":"${mark_visit_reason}":"${wh2-sales-person-1-name}","visit_date":"${DATE-yyyy-MM-dd}","outlet_name":"${customer_id}"} |
    * response status code should be 200
