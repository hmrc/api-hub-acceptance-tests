@ignore @regression
Feature: Team Members

  Background:
    Given a user has signed-in

  Scenario: Add multiple team members
    And the new user starts the registration process
    When 10 additional team members are added
    Then 11 team members exist
    And the count of team members on the check your answers page is 11

  Scenario: Change team member
    And the new user starts the registration process
    And 1 additional team members are added
    When the user changes the team members email address
    Then the team members email is changed

  @HIPP-27
  Scenario: Remove team member
    And the new user starts the registration process
    And 1 additional team members are added
    When the user removes the last added team member
    Then 1 team members exist

  Scenario: Display warning when both checkboxes are unchecked for add team member options
    And the new user starts the registration process
    When the user attempts to add a new team member with no radio button option chosen
    Then the add team members page displays an error summary

  Scenario: Display warning when team member email is not from the expected domain (digital.hmrc.gov.uk or hmrc.gov.uk)
    And the new user starts the registration process
    When the user attempts to add a new team member using an unaccepted domain
    Then the add team member details page displays an error summary
    And the email alert message is displayed
