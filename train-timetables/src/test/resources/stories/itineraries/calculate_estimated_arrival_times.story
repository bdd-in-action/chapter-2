Tell travellers when they will arrive at their destination

Narrative:
In order to plan my voyage more effectively
As a commuter
I want to know what time I will arrive at my destination

Scenario: Calculate arrival times
Given I want to go from <departure> to <destination>
And the next train is scheduled to leave at <departure-time> on the <line> line
When I ask for my arrival time
Then the estimated arrival time should be <arrival-time>
Examples:
| departure  | destination | departure-time 	| line      | arrival-time |
| Parramatta | Town Hall   | 8:02				| Western   | 8:29         |
| Epping     | Central     | 8:03				| Northern  | 8:48         |
| Epping     | Central	   | 8:07				| Newcastle	| 8:37         |
| Epping	 | Central	   | 8:13				| Epping	| 8:51         |


