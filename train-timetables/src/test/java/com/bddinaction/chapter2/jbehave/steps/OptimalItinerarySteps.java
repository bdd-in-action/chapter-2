package com.bddinaction.chapter2.jbehave.steps;

import com.bddinaction.chapter2.services.InMemoryTimetableService;
import com.bddinaction.chapter2.services.ItineraryService;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.joda.time.LocalTime;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class OptimalItinerarySteps {

    InMemoryTimetableService timetableService;

    @Given("$line line trains from $lineStart leave $departure for $destination at $departureTimes")
    public void givenArrivingTrains(String line,
                                    String lineStart,
                                    String departure,
                                    String destination,
                                    List<LocalTime> departureTimes) {
        timetableService = new InMemoryTimetableService();
    }

    List<LocalTime> proposedTrainTimes;

    @When("I want to travel from $departure to $destination at $startTime")
    public void whenIWantToTravel(String departure, String destination, LocalTime startTime) {
        ItineraryService itineraryService = new ItineraryService(timetableService);
        proposedTrainTimes = itineraryService.findNextDepartures(departure, destination, startTime);
    }

    @Then("I should be told about the trains at: $expectedTrainTimes")
    public void shouldBeInformedAbout(List<LocalTime> expectedTrainTimes) {
        assertThat(proposedTrainTimes).isEqualTo(expectedTrainTimes);
    }
}

