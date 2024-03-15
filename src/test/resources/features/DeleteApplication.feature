@regression @HIPP_602
Feature: Delete Application

  Background:
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user registers an application
    And the application can be viewed
    And the user chooses "Delete application" from the left hand nav menu

  Scenario: Delete a registered application
    And deletes the application
    Then the previously registered application should no no longer be listed in all applications

  Scenario: Display error message when attempting to delete an application without confirming
    When the user attempts to delete the application without confirming
    Then the error make a selection error is displayed

  Scenario: Cancel deletion of application redirects to the application details page
    When the user chooses to cancel the deletion of the application
    Then the user is redirected to the "Application details" page