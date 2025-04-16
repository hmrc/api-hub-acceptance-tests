Feature: admin page

  @smoke @regression
  Scenario: An Admin user can see the details of a managed application
    Given a user has signed-in with role "support" and registers an application
    And the user navigates from the application details page to the integration hub admin page
    And the user navigates to the Manage Applications page
    When the user the user enters the application id to filter
    Then the application link is visible