@gajender.singh@elastic.run @bottom_icons @sanity
Feature: User verifies functionality of bottom icons

  Scenario:User verifies all bottom icons are working
    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-1-title} |
    * user captures store image if available
    * user click on take order button
    * user verifies all bottom icons