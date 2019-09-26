Feature: Show next departing trains

  As a commuter travelling between two stations on the same line
  I want to know what time the next trains for my destination will leave
  So that I can spend less time waiting at the station

  Scenario: Next train going to the requested destination on the same line
    Given the T1 train to Central leaves Hornsby at 08:02, 08:15, 08:21
    When Travis want to travel from Hornsby to Chatswood at 08:00
    Then he should be told about the trains at: 08:02, 08:15
