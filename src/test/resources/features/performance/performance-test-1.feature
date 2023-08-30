Feature: Cash order placement for single item and multiple uom

  @performance-test1
  Scenario Outline: Verify cash order placement for single item and multiple uom <No>
    * user login to the experience layer sales app with valid mobile number
      | 9879315696 |
    * user click on beat button
    * user click on locality and store
      | ${PROD_LOCALITY_1} | ${PROD_STORE_1} |
    * user click on take order button
    * user wait for visibility of catalogue

    * user add item to cart
      #| Item or Category Name | Index No | Uom |Quantity|
      | Ayurved Akash Soap @ 50          | 0 | Piece | ${NUMBER-1-5} |
      | Dabur Lal Dant Manjan @10        | 0 | Piece | ${NUMBER-1-5} |
      | Nisha Brown Mehendi @100         | 0 | Piece | ${NUMBER-1-5} |
      | Ujala Supreme Fabric Whitener @8 | 0 | Piece | ${NUMBER-1-5} |
      | Santoor Talcum Powder @10        | 0 | Piece | ${NUMBER-1-5} |
    * user place remote order

    * user generate random value " " and store into session "cookie"
    * user set api headers
      | Authorization | ${order_api_token} |
    * user retries and get details by frappe client get api with filters
      | experience-layer-order-api | frappe_get_report | Quotation | {"app_source":"ER Sales App","transaction_date":"${DATE-yyyy-MM-dd}","rounded_total":"${GRAND_TOTAL_AMOUNT}"} |
    * response status code should be 200
    * get response "message.name" string attribute and store into session "exp_quotation_id"
    * user verifies response attribute "message.owner" value should be "${PROD_UAT_SE_NAME_1}"

    Examples:
      | No |
      | 1  |
      | 2  |
      | 3  |
      | 4  |
      | 5  |
      | 6  |
      | 7  |
      | 8  |
      | 9  |
      | 10 |
      | 11 |
      | 12 |
      | 13 |
      | 14 |
      | 15 |





