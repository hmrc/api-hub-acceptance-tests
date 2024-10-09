@hipp-1252
Feature: Application Environments

  Background:
    Given a user has signed-in and registers an application
    And the user chooses "Environments and credentials" from the application left hand nav menu

  Scenario: Add Test credentials - HIPP-1251
    When the user adds Test credentials
    Then the client id should be added to the test environments credentials with count 2

  Scenario: Add Prod credentials - HIPP-1252
    When the user adds Prod credentials
    Then the user confirms generation production credentials
    And the user sees "Client secret successfully created"
    Then the client id should be added to the Production environments credentials with count 1