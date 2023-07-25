@gajender.singh@elastic.run @location_change @regression1
Feature:Changing sp location at store location page

  Scenario: Changing sp location at store location page
    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user click on locality and store
      | ${wh2-sp1.locality} | ${wh2-customer-5-title} |
    * user captures store image if available
    * user sets mobile geolocation
      | 18.5068726 | 73.9299712 |

    * user wait for 10 seconds
    * user clicks on retry location capture button
    * user click on take order button
    * user add item to cart
      | Automation-Category-2 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
      | Automation-Category-3 | ${NUMBER-1-5} | Bag | ${NUMBER-5-10} |
    * user place order with local