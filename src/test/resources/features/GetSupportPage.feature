@smoke
Feature: Get Support Page


  Scenario: Navigation to Get Support Page
    Given the user navigate to get support page
    Then the get support page has the following headings "Having an issue using an API?" and "Having an issue registering an API?"
