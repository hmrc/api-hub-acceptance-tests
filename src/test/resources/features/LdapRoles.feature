@HIPP-489
Feature: LDAP Roles

  Scenario: Verify top level navigation for LDAP roles

  Scenario Outline: Verify top level navigation for LDAP roles <role>
    Given a user has signed in with LDAP and role "<role>"
    Then your applications has the following header links "<link_one>" "<link_two>" "<link_three>"

    Examples:
      | role            | link_one  | link_two              | link_three |
      | approver        | Dashboard | Integration Hub Admin | HIP APIs   |
      | support         | Dashboard | Integration Hub Admin | HIP APIs   |
      | privileged-user | Dashboard | HIP APIs              |            |
