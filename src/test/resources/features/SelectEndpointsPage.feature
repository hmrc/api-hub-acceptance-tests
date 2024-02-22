@regression @hipp-753
Feature: Select Endpoints Page

  Scenario: Display error if checkbox is not checked before continuing
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user registers an application
    And the application can be viewed
    When the attempts to continue without selecting an endpoint
    Then an error 'Please select at least one end point' should be displayed

  Scenario: Display correct scopes for endpoint
    Given a user is on the sign in page
    And the user decides to login via ldap
    And an approver with write privileges logs in
    And the new user registers an application
    And the application can be viewed
    When the user adds a particular api to an application
    Then the correct scopes for that api should be displayed
