Feature: Development and Prod credential types

  @wip
  Scenario: Create client id for registered application
    Given a user has signed-in and registers an application
    And the user chooses "Environments and credentials" from the application left hand nav menu
    Then the client id should be added to the development environments credentials

  Scenario: Adding Dev and Prod Credentials
