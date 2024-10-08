@smoke @ZAP
Feature: Adding APIs to applications

  Scenario: Add an API to an application
    Given a user has signed-in and registers an application
    When the user attempts to add an api to the application
    Then the api is added to the application
