@HIPP-489
Feature: LDAP Roles

  Scenario: Verify top level navigation for LDAP roles

  Scenario Outline: Verify top level navigation for LDAP roles <role>
    Given a user is on the sign in page
    And the user decides to login via ldap
    When a user logs in with role "<role>"
    Then your applications has the following header links "<link_one>" "<link_two>" "<link_three>"

    Examples:
      | role             | link_one         | link_two      | link_three |
      | approvals        | All Applications | API Hub Admin | HIP APIs   |
      | support          | All Applications | API Hub Admin | HIP APIs   |
      | privileged-usage | All Applications | HIP APIs      |            |