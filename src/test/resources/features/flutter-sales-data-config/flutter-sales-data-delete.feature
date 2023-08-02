@new-sales-data-delete @gajender.singh@elastic.run

Feature: Deleting data from experience layer

  Scenario Outline: Deleting customer from experience layer

    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    And response status code should be 200

    * user get details by frappe client get api with filters
      | Customer | {"customer_name":"<customer name>","locality":"<customer locality>"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "customer_id"

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |

    * user hit post api call with form param
      | experience-layer-onboarding-api | frappe_delete          |
      | doctype                         | Address                |
      | name                            | ${customer_id}-Billing |

    * user hit post api call with form param
      | experience-layer-onboarding-api | frappe_delete       |
      | doctype                         | Address             |
      | name                            | ${customer_id}-Shop |

    * user wait for 5 seconds
    * user hit post api call with form param
      | experience-layer-onboarding-api | frappe_delete  |
      | doctype                         | Customer       |
      | name                            | ${customer_id} |
    * response status code should be 200

    Examples:
      | customer name           | customer locality   |
      | ${wh2-customer-1-title} | ${wh2-sp1.locality} |
      | ${wh2-customer-3-title} | ${wh2-sp1.locality} |
      | ${wh2-customer-4-title} | ${wh2-sp1.locality} |
      | ${wh2-customer-5-title} | ${wh2-sp1.locality} |
      | ${wh3-customer-1-title} | ${wh3-sp1.locality} |
      | ${wh5-customer-1-title} | ${wh5-sp1.locality} |
      | ${wh5-customer-2-title} | ${wh5-sp4.locality} |
      | ${wh5-customer-6-title} | ${wh5-sp6.locality} |

  Scenario Outline: Deleting addresses and contact for duplicate customer <customer name>

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |

    * user get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"<customer name>"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "customer_id"
    * get response "message.user_id" string attribute and store into session "customer_user_id"

    * user hit post api call with form param
      | experience-layer-onboarding-api | frappe_delete          |
      | doctype                         | Address                |
      | name                            | ${customer_id}-Billing |

    * user hit post api call with form param
      | experience-layer-onboarding-api | frappe_delete       |
      | doctype                         | Address             |
      | name                            | ${customer_id}-Shop |

    * user get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Contact | {"user":"${customer_user_id}"} |
    * get response "message.name" string attribute and store into session "customer_contact_id"

    * user hit post api call with form param
      | experience-layer-onboarding-api | frappe_delete          |
      | doctype                         | Contact                |
      | name                            | ${customer_contact_id} |
    * user wait for 5 seconds

    Examples:
      | customer name           |
      | ${wh2-customer-1-title} |
      | ${wh2-customer-3-title} |
      | ${wh2-customer-4-title} |
      | ${wh2-customer-5-title} |
      | ${wh3-customer-1-title} |
      | ${wh5-customer-1-title} |
      | ${wh5-customer-2-title} |
      | ${wh5-customer-6-title} |

  Scenario Outline: Deleting duplicate customer <customer name>

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${onboarding_api_token} |

    * user get details by frappe client get api with filters
      | experience-layer-onboarding-api | frappe_get_report | Customer | {"full_name":"<customer name>"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "customer_id"

    * user hit post api call with form param
      | experience-layer-onboarding-api | frappe_delete  |
      | doctype                         | Customer       |
      | name                            | ${customer_id} |

    Examples:
      | customer name           |
      | ${wh2-customer-1-title} |
      | ${wh2-customer-3-title} |
      | ${wh2-customer-4-title} |
      | ${wh2-customer-5-title} |
      | ${wh3-customer-1-title} |
      | ${wh5-customer-1-title} |
      | ${wh5-customer-2-title} |
      | ${wh5-customer-6-title} |

    Scenario Outline: Deleting User for customer <customer name>
      * user login to application by api
        | ${with-run-username} | ${with-run-password} |
      And response status code should be 200

      * user get details by frappe client get api with filters
        | Customer | {"customer_name":"<customer name>"} |
      * response status code should be 200
      * get response "message.mobile_no" string attribute and store into session "customer_mobile"

      * user generate random value " " and store into session "cookie"
      * user set api headers
        | Authorization | ${onboarding_api_token} |

      * user get details by frappe client get api with filters
        | experience-layer-onboarding-api | frappe_get_report | User | {"mobile_no":"${customer_mobile}"} |
      * response status code should be 200
      * get response "message.name" string attribute and store into session "customer_user_id"

      * user hit post api call with form param
        | experience-layer-onboarding-api | frappe_delete       |
        | doctype                         | User                |
        | name                            | ${customer_user_id} |
      * response status code should be 200

      Examples:
        | customer name           |
        | ${wh2-customer-1-title} |
        | ${wh2-customer-3-title} |
        | ${wh2-customer-4-title} |
        | ${wh2-customer-5-title} |
        | ${wh3-customer-1-title} |
        | ${wh5-customer-1-title} |
        | ${wh5-customer-2-title} |
        | ${wh5-customer-6-title} |