@e2e @login-logout @regression1
Feature: Login Logout

  Scenario: Verify Login Logout flow
    * user login to the experience layer sales app with valid details
      | ${wh2-se1} | ${common-password} |
    * user log out of sales app
    * user wait for 10 seconds