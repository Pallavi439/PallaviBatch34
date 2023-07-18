@sales-mark-visit

Feature: User verifies sales person mark visit functionality

  Scenario: User mark visit the customer
    * user sets mobile geolocation
      |58.7464|23.4137|

    * user login to the experience layer sales app with valid details
      | ${wh3-se1} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user mark visit "${wh3-sp1.locality}" locality "${wh3-customer-1-title}" store with reason "${NUMBER-0-7}"


