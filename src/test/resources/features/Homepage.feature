@Homepage
Feature: Api hub home page displays necessary information

  Background:
    Given an unauthenticated user navigates to the homepage

  Scenario: Redirect an unauthenticated user to stride login page
    Then the user should be redirected to the stride login page

  Scenario: Redirect an authenticated user to the api hub home page
    When the user fills in the required stride information
    Then the user should be directed to the api hub home page

  Scenario: Register an application
    And the user fills in the required stride information
    When the user correctly registers an application
    Then the inputted application name should match the registered application name
