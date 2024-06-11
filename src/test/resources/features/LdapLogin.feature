@pra
Feature: Ldap Login

  Background:
    Given a user is on the sign in page
    And the user decides to login via ldap

  Scenario: Login via LDAP
    When the user submits valid LDAP details
    Then the user should be authenticated

  Scenario: Register first application for a new user account
    When the user submits valid LDAP details
    And the new user registers an application
    Then the application should be registered

  Scenario: View Application
    When the user submits valid LDAP details
    And the new user registers an application
    Then the application can be viewed

#  @regression @HIPP_531
#  Scenario: Email address is required for LDAP login
#    Given a user is on the sign in page
#    And the user decides to login via ldap
#    When the user attempts to login without providing an email address
#    Then the appropriate error should be displayed
