@oc
Feature: Order constraint

  Scenario: oc-1

    * user login to the experience layer sales app with valid details
      | ${oc-wh1-se-1} | ${common-password} |
    * user click on beat button
    * user click on locality and store
      | ${wh1-oc.locality} | ${customer-1-title} |
    * user captures store image if available
    * user clicks on take a remote order button

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-1 | ${NUMBER-1-5} | Piece | 1 |
    * user click on cart next button
    * user click on order constraints strip
    * user click on order constraints title "0"

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Automation-Category-2 | ${NUMBER-1-5} | Piece | ${NUMBER-5-10} |
    * user click on cart back button
    * user click on cart next button
    * user verify absence of order constraint strip

    * user click on place order button
    * user click on cart local place order button


    

