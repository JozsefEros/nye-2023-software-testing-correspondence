Feature: Video page content

  Scenario Outline:
    Given the 'Videos' page is opened
    When I type '<input>' into the videosearch field
    Then I see <numberOfVideoCards> videocards
    Examples:
      | input       | numberOfVideoCards |
      | Unix        | 4             |
      | Android     | 14            |
