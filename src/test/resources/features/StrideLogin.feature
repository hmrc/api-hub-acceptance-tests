@smoke
Feature: Logging in via Stride

  Scenario: Successful Stride login
    Given a user navigates to the sign in page
    And chooses to login via stride
    When the user submits valid sign in credentials
    Then the user should be successfully signed in via stride

  Scenario: Display you do not have permissions page if user has an invalid role
    Given a user navigates to the sign in page
    And chooses to login via stride
    When user fills in all fields except role
    Then user should be on the "unauthorised" url page


