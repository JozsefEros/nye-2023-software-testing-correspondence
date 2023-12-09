Feature: Search for communities

  Scenario Outline:
    Given the 'Communities' page is opened
    When I type '<input>' into the search field
    Then I see <numberOfCards> cards
    Examples:
      | input       | numberOfCards |
      | Budapest    | 4             |
      | China       | 1             |
      | Minsk       | 7             |
      | Nyiregyhaza | 0             |