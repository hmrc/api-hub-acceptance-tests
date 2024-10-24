Feature: Cancel access request

  Scenario: The user cancels an access request
    Given a user has signed-in and registers an application
    And the user adds an API to the application
    And the user chooses "Application APIs" from the application left hand nav menu
    And the user requests production access
    When the user selects the cancel access requests link
    And the user selects to cancel the access request for the first API
    And the user confirms the access request cancellations
    Then the user is shown the cancel access request success page
    And the user can select to return to the application details page
