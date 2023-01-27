@Homepage
Feature: Api hub home page displays necessary information

  Scenario: Register an application
    Given an authenticated user navigates to the homepage
    When the user correctly registers an application
    Then the inputted application name should match the registered application name
