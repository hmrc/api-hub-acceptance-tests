@regression
Feature: Delete Application

  @wip
  Scenario: Delete a registered application
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user registers an application
    And the application can be viewed
    And the user chooses "Delete application" from the left hand nav menu
    When deletes the application
    Then the message "You have no registered applications" is displayed showing no registered applications are present