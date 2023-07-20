@customer_tag_1

Feature: Customer Tag configuration functionality

  Scenario: create whitelisted tag if not available
    * user login to application by api
      | ${with-run-username} | ${with-run-password} |
    * user wait for 2 seconds
    * user create "WithRun App Global Config" by api
      | title | Whitelisted Customer Tag | /default-payload.json |
    * response status code should be 200

  Scenario: create a customer tag for SE
    * user get "${wh5-customer-1-title}" customer details by api
    * response status code should be 200
    * user create "Document Tag" by api
      | tag_title | Automation Gold Outlet | /customer_tag.json |
    * response status code should be 200
    * user create "Document Tag" by api
      | tag_title | Automation Silver Outlet | /customer_tag2.json |
    * response status code should be 200
    ########To set whitelisted Tag in withrun app config

  @create_tag
  Scenario: to set Whitelisted  Customer tag in withrun app config and reset
    * user get details by frappe client get api with filters
      | WithRun App Global Config | {"title":"Whitelisted Customer Tag"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "WhitelistedCustomerTag"
    * user set value by frappe client set value api with filters and fieldname
      | WithRun App Global Config | ${WhitelistedCustomerTag} | value | Automation Gold Outlet, Unbilled Outlet |
    * response status code should be 200

  Scenario: To verify that tag should not be shown in the app side if it is not present in the withrun app config and document tag is present
    * user login to the experience layer sales app with valid details
      | ${wh5-se1} | ${common-password} |
    * user wait for 5 seconds
    * user click on beat button
    * user clicks on locality "${wh5-sp1.locality}"
    * user searches store "${wh5-customer-1-title}"
    * user verifies presence of gold outlet tag
    * user verifies absence of silver outlet tag