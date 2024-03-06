@smoke
Feature: Adding APIs to applications

  Scenario: Add an API to an application
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And selects start now from the api hub intro page
    And the new user registers an application
    And the application can be viewed
    When the user attempts to add an api to the application
    Then the api is added to the application

