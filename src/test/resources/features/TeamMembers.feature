@regression
Feature: Team Members

  Scenario: Add multiple team members
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user starts the registration process
    When "10" additional team members are added
    Then "11" team members exist
    And the count of team members on the check your answers page is "11"

  Scenario: Change team member
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user starts the registration process
    And "1" additional team members are added
    When the user changes the team members email address
    Then the team members email is changed

  @HIPP-27
  Scenario: Remove team member
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user starts the registration process
    And "1" additional team members are added
    When the user removes the last added team member
    Then "1" team members exist

  Scenario: Display warning when both checkboxes are unchecked for add team member options
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user starts the registration process
    When the user attempts to add a new team member with no radio button option chosen
    Then the problem alert box displayed

  Scenario: Display warning when team member email is not from the expected domain (digital.hmrc.gov.uk or hmrc.gov.uk)
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user starts the registration process
    When the user attempts to add a new team member using an unaccepted domain
    Then the problem alert box displayed
    And the email alert message is displayed
