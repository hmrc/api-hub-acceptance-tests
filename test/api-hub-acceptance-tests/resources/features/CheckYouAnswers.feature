@ignore @regression @HIPP-369 @HIPP-368 @hip-403
Feature: Check your answers

  Background:
    Given a user has signed-in
    And the new user starts the registration process
    When the user chooses to not add a new team member

  Scenario: Verify no team members added screen layout
    Then the check your answers page displays the correct information for no team members added

  Scenario: Rename application
    When the user changes the application name
    Then the application name should be changed

  Scenario: Change team member link redirects to team members overview page
    When the user chooses to change the team member
    Then the user should be redirected to the team members overview page
