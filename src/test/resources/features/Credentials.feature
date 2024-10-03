Feature: Application Environments

  Background:
    Given a user has signed-in and registers an application
    And the user chooses "Environments and credentials" from the application left hand nav menu

  Scenario: Create client id for registered application
    Then the client id should be added to the test environments credentials with count 1

  Scenario: Add Test credentials
    When the user adds Test credentials
    Then the client id should be added to the test environments credentials with count 2