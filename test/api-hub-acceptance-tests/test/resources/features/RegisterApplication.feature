Feature: Register application

  Background:
    Given a user has signed-in
    And the user is in a team
    And the new user starts the register application journey

  Scenario: Register an application and check application details
    When the user enters an application name
    And the user selects an owning team for the application
    And the user registers the application on the check your answers page
    And the user clicks to view the new application on the success page
    Then the new application's details are correct
