@pra
Feature: Ldap Login

  Scenario: Login via LDAP
    Given a user is on the sign in page
    And the user decides to login via ldap
    When an approver with write privileges logs in
    Then the user should be authenticated

