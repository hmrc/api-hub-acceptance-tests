@regression @hipp-1252
Feature: Application Environments

  Background:
    Given a user has signed-in with role "privileged-user" and registers an application
    And the user chooses "Environments and credentials" from the application left hand nav menu

  Scenario: Add Test credentials - HIPP-1251
    When the user adds a test credential
    Then there are 2 test environment credentials

  Scenario: Add Prod credentials - HIPP-1252
    When the user adds a production credential
    Then the user confirms generation production credentials
    And the user sees the generate production credentials success page
    And the user selects to return to the environments and credentials page
    Then the client id should be added to the Production environments credentials with count 1

  Scenario: Delete test credential
    Given the user adds a test credential
    When the user revokes a test credential
    Then there are 1 test environment credentials
