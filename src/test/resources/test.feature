# new feature
# Tags: optional

Feature: A description

  Scenario Outline: A scenario
    When I get to stackoverflow webpage
    And In search field enter "<keywords>"
    Then All relevant topics with "<keywords>" are saved in the textfile
    Examples:
      | keywords    |
      | 2013        |
#      | python      |
#      | java        |
#      | android     |
#      | api         |
#      | ggdgdsghrdh |



