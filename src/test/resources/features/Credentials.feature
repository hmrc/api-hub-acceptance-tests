@credentials
Feature: Credentials

  Background:
    Given a user has signed-in and registers an application

  Scenario: Create new test credential
    Given the user chooses "Test Environment" from the application left hand nav menu
    When the user creates a new test credential
    Then there are 2 test credentials

  Scenario: Create new production credential
    When the user swaps role to "privileged-user"
    And the user chooses "Production Environment" from the application left hand nav menu
    And the user starts the create new production credential journey
    Then the user confirms they meet the conditions to create a new production credential
    And the client secret successfully created page is displayed
    And the user returns to the production environment page
    Then there is 1 credential for "production"

  Scenario: Delete test credential
    Given the user chooses "Test Environment" from the application left hand nav menu
    And the user creates a new test credential
    When the user revokes the first test credential
    Then there is 1 credential for "test"
    And the recently created credential for "test" still exists

  Scenario: Delete production credential
    When the user swaps role to "privileged-user"
    And the user chooses "Production Environment" from the application left hand nav menu
    And the user creates TWO production credentials
    When the user revokes the first production credential
    Then there is 1 production credential
    And the recently created production credential still exists