@e2e @cash-order
Feature: Cash order placement

  Scenario: Verify cash order placement flow

    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-1-title} |
    * user captures store image
    * user clicks on take a remote order button
    * user add one random item to cart
    * user click on cart next button
    * user click on place order button
    * user click on cart remote order button

    

