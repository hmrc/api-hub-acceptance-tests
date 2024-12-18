@smoke @productionaccess
Feature: Production access requests

  Scenario: Request production access
    Given a user has signed-in and registers an application
    And the user adds an API to the application
    And the user chooses "Production Environment" from the application left hand nav menu
    When the user requests prod access
    And the user selects which API
    And the user supports the request with a reason
    And the user checks their answers
    Then the production access request is successful

  Scenario: Approve production access request
    Given a user has signed-in and registers an application
    And the user adds an API to the application
    And the user chooses "Production Environment" from the application left hand nav menu
    And the user requests prod access
    And the user selects which API
    And the user supports the request with a reason
    And the user checks their answers
    And the production access request is successful
    And the user swaps role to "approver"
    And views the access requests page
    And opens the first access request
    When the user approves the access request
    Then the access request approved page is displayed
    And returns to the access requests page

  Scenario: Reject production access request
    Given a user has signed-in and registers an application
    And the user adds an API to the application
    And the user chooses "Production Environment" from the application left hand nav menu
    And the user requests prod access
    And the user selects which API
    And the user supports the request with a reason
    And the user checks their answers
    And the production access request is successful
    And the user swaps role to "approver"
    And views the access requests page
    And opens the first access request
    When the user rejects the access request with reason "Not Valid"
    Then the access request rejected page is displayed
    And returns to the access requests page after rejection