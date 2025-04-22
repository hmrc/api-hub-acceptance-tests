@smoke
Feature: Logging in via Stride

  Background:
    Given a user navigates to the sign in page
    And chooses to login via stride

  Scenario: Successful Stride login
    When the user submits valid sign in credentials
    Then the user should be successfully signed in via stride

  Scenario: Display you do not have permissions page if user has an invalid role
    When the user fills in all fields except role
    Then the user should be on the unauthorised url page

  Scenario Outline: Verify top level navigation for stride roles <role>
    Given a user has signed in with Stride and role "<role>"
    Then your applications has the following header links "<link_one>" "<link_two>" "<link_three>" "<link_four>" "<link_five>"

    Examples:
      | role            | link_one  | link_two              | link_three   | link_four   | link_five   |
      | approver        | Dashboard | Explore APIs          | Help guide   | Get support | Admin       |
      | support         | Dashboard | Explore APIs          | Help guide   | Get support | Admin       |
      | user            | Dashboard | Explore APIs          | Help guide   | Get support |             |
      | privileged-user | Dashboard | Explore APIs          | Help guide   | Get support |             |
