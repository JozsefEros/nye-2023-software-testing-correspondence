Feature: Main page popular events

  Background:
    Given I visit the main page
    When I close the cookie disclaimer

  Scenario: Main page should show 4 cards
    Then I can see 4 event cards
    And I can see the "Popular" tab
    And the "Popular" tab is active
    And there are 5 tabs
    
  Scenario: User should be able to switch tabs
    And I click on the "My Events" tab
    Then I see the "You need to be logged in to see the events" text
    And I see the "Login" button
