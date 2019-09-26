package manning.bddinaction.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import manning.bddinaction.itineraries.ItineraryService;
import manning.bddinaction.timetables.InMemoryTimeTable;
import manning.bddinaction.timetables.TimeTable;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;

public class DepartingTrainsStepDefinitions {

    InMemoryTimeTable timeTable = new InMemoryTimeTable();
    ItineraryService itineraryService = new ItineraryService(timeTable);

    @Given("the (.*) train to (.*) leaves (.*) at (.*)")
    public void theTrainLeavesAt(String line,
                                 String to,
                                 String from,
                                 String departingAt) {
        List<LocalTime> departureTimes = localTimesFrom(departingAt);
        timeTable.scheduleService(line, departureTimes, from, to);
    }

    List<LocalTime> proposedDepartures;

    @When("Travis want to travel from (.*) to (.*) at (.*)")
    public void travelBetween(String from, String to, String departingAt) {
        LocalTime departureTime = LocalTime.parse(departingAt);
        proposedDepartures = itineraryService.findNextDepartures(departureTime,
                from,
                to);
    }

    @Then("he should be told about the trains at: (.*)")
    public void shouldBeToldAboutTheTrainsAt(String expectedDepartures) {
        List<LocalTime> expected = localTimesFrom(expectedDepartures);
        assertThat(proposedDepartures).isEqualTo(expected);
    }

    private List<LocalTime> localTimesFrom(String listOfDepartureTimes) {
        return stream(listOfDepartureTimes.split(","))
                .map(String::trim)
                .map(LocalTime::parse)
                .collect(Collectors.toList());
    }
}
