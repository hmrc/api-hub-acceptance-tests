Feature: Create team

  Background:
    Given a user has signed-in
    And the new user starts the create team journey

  Scenario: Create a team and check team details
    When the user enters a team name
    And the user selects the team to be an api producer
    And selects to enter an additional team member
    And enters the new team member's email address
    And selects to continue to the create team check your answers page
    And creates the team on the check your answers page
    And clicks to view the manage my teams page from the success page
    And clicks to view the new team
    Then the new team's details are correct
