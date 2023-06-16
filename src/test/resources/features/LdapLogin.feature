Feature: Ldap Login
  @pra
  Scenario: Login via LDAP
    Given a user is on the sign in page
    And the user decides to login via ldap
    When an approver with write privileges logs in
    Then the user should be authenticated

  Scenario: Register first application for a new user account
    Given a user is on the sign in page
    And the user decides to login via ldap
    And a new user with approver resource type with write privileges logs in
    When the new user registers an application
    Then the application should be registered

  Scenario: View Application
    Given a user is on the sign in page
    And the user decides to login via ldap
    And a new user with approver resource type with write privileges logs in
    When the new user registers an application
    Then the application can be viewed