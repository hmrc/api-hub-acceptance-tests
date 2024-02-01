@regression
Feature: Development and Prod credential types

  @wip
  Scenario: Create client id for registered application
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    When the new user registers an application
    Then the client id should be added to the development environments credentials

  Scenario: Adding Dev and Prod Credentials
