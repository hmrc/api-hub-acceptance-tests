Feature: admin page

  @smoke @regression
  Scenario: An Admin user can see the details of a managed application
    Given a user has signed-in with role "support" and registers an application
    And the user navigates to the Integration Hub Admin page -> Manage Applications
    When the user the user enters the application id to filter
    Then the application link is visible