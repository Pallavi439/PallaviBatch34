@upload_profile_image @regression1 @gajender.singh@elastic.run
Feature: Upload profile image flow

  Scenario: Edit and Upload Supplier Profile image
    * add image to downloads folder
    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user wait for 5 seconds
    * user edits sales person profile and uploads image
    * user wait for 10 seconds
