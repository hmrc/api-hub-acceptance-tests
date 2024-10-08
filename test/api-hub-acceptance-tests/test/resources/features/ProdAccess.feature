@smoke
Feature: Prod Access Request Types

  Scenario: Request Production Access
    Given an api is added to an application
    And the user chooses "Application APIs" from the application left hand nav menu
    When the user requests prod access
    And the user selects which API
    And the user supports the request with a reason
    And the user checks their answers
    Then the production access request is successful
