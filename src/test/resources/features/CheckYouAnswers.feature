@HIPP-369 @HIPP-368 @hip-403
Feature: Check your answers

  Scenario: Verify no team members added screen layout
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user starts the registration process
    When the user chooses to not add a new team member
    Then the check your answers page displays the correct information for no team members added

  Scenario: Rename application
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user starts the registration process
    And the user chooses to not add a new team member
    When the user changes the application name
    Then the application name should be changed

  Scenario: Change team member link redirects to team members overview page
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user starts the registration process
    And the user chooses to not add a new team member
    When the user chooses to change the team member
    Then the user should be redirected to the team members overview page
