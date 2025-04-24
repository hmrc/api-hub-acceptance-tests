Feature: Produce API (V2 Journey)
  Scenario: User who is a member of an API producer team can create a new API
    Given a user has signed in and has an api producer team
    And the user elects to create a new api
    And the user clicks Get Started button
    And the user selects their team
    And the user acknowledges they have no egresses available
    And the user selects to use visual editor
    And the user clicks in the editor window
    And the user sets the oas title
    And the user sets the oas version to be "6.6.6"
    And the user selects continue on the oas editor page
    And the user enters a short description
    And the user confirms api name
    And the user chooses to configure no prefixes
    And the user selects a hod
    And the user selects domains
    And the user sets the api status
    And the user checks their answers the first time
    And the user selects no test egress
    And the user checks their answers the second time
    Then the create api journey is completed