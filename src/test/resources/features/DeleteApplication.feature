@regression @HIPP_602
Feature: Delete Application

  Background:
    Given a user has signed-in and registers an application
    And the user chooses "Delete application" from the application left hand nav menu

  Scenario: Delete a registered application
    And deletes the application
    Then the previously registered application should no longer be listed in all applications

  Scenario: Display error message when attempting to delete an application without confirming
    When the user attempts to delete the application without confirming
    Then the error make a selection error is displayed

  Scenario: Cancel deletion of application redirects to the application details page
    When the user chooses to cancel the deletion of the application
    Then the user is redirected to the "Application details" page
