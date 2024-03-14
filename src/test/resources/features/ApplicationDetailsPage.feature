Feature: Application Details Page

  @regression
  Scenario: Verify application details content is correct
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user registers an application
    And the application can be viewed
    Then the application details, application apis as well as the team members sections should be correct

  @wip
  Scenario: Display application not found for invalid application id
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    When the navigates to an invalid application id "123456"
    Then the the application not found header message should be displayed
    And the message "Cannot find an application with Id 123456"
