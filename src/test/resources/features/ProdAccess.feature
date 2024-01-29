@smoke
Feature: Prod Access Request Types

  Scenario: Request Production Access
    Given an api is added to an application
    And from the left hand menu the user chooses "Application APIs"
    When the user requests prod access
    And the user supports the request with a reason
    Then the pending request is logged

  Scenario: Request and approve prod access
