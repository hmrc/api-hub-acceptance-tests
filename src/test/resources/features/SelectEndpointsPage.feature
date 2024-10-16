@regression @hipp-753
Feature: Select Endpoints Page

  Background:
    Given a user has signed-in and registers an application

  Scenario: Display error if checkbox is not checked before continuing
    When the user attempts to continue without selecting an endpoint
    Then an error 'Select at least one endpoint' should be displayed

  Scenario: Display correct scopes for endpoint
    When the user adds a particular api to an application
    Then the correct scopes for that api should be displayed
