Feature: Application Details Page

  @regression
  Scenario: Verify application details content is correct
    Given a user has signed-in and registers an application
    Then the application details, application apis as well as the team members sections should be correct

  @wip
  Scenario: Display application not found for invalid application id
    Given a user has signed-in
    When the user navigates to an invalid application id "123456"
    Then the application not found header message should be displayed
    And the error message should be "Cannot find an application with ID 123456"
