@pra
Feature: Ldap Login

  Background:
    Given a user is on the sign in page
    And the user decides to login via ldap

  Scenario: Login via LDAP
    When the user submits valid LDAP details
    Then the user should be authenticated

  Scenario Outline: Verify top level navigation for LDAP roles <role>
    Given a user has signed in with LDAP and role "<role>"
    Then your applications has the following header links "<link_one>" "<link_two>" "<link_three>"

    Examples:
      | role            | link_one  | link_two              | link_three   |
      | approver        | Dashboard | Integration Hub Admin | Explore APIs |
      | support         | Dashboard | Integration Hub Admin | Explore APIs |
      | privileged-user | Dashboard | Explore APIs          |              |
