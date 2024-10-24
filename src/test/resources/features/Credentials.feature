@credentials
Feature: Credentials

  Background:
    Given a user has signed-in and registers an application
    And the user chooses "Environments and credentials" from the application left hand nav menu

  Scenario: Create new test credential
    When the user creates a new test credential
    Then there are 2 test credentials

  Scenario: Create new production credential
    When the user swaps role to "privileged-user"
    And the user starts the create new production credential journey
    Then the user confirms they meet the conditions to create a new production credential
    And the client secret successfully created page is displayed
    And the user returns to the environments and credentials page
    Then there is 1 production credential

  Scenario: Delete test credential
    Given the user creates a new test credential
    When the user revokes the first test credential
    Then there is 1 test credential
    And the recently created test credential still exists
