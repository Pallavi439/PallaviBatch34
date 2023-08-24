@all_items_icon @regression1 @gajender.singh@elastic.run
Feature: Verify All Items Icon functionality

  Scenario: User verifies All Items Icon functionality

    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-1-title} |
    * user captures store image if available
    * user click on take order button
    * user verify all items icon functionality
    * user wait for 10 seconds
