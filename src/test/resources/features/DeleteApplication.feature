@regression @HIPP_602
Feature: Delete Application

  Background:
    Given a user has signed-in and registers an application
    And the user chooses "Delete application" from the application left hand nav menu

  Scenario: Delete a registered application
    And deletes the application
    Then the previously registered application should no longer be listed in all applications