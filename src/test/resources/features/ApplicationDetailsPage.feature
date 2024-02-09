@regression
Feature: Application Details Page

  Scenario: Verify application details content is correct
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user registers an application
    And the application can be viewed
    Then the application details, application apis as well as the team members sections should be correct
