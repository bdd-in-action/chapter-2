package com.bddinaction.chapter2.jbehave.steps;

import com.bddinaction.chapter2.services.ItineraryService;
import com.bddinaction.chapter2.services.TimetableService;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.joda.time.LocalTime;

import static org.fest.assertions.Assertions.assertThat;


public class EstimatedArrivalTimeSteps {

    TimetableService timetable = new TimetableService();

    String departure;
    String destination;
    String travellingOnLine;

    @Given("I want to go from <departure> to <destination>")
    public void givenIWantToGoFrom(String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
    }

    @Given("the next train is scheduled to leave at <departure-time> on the <line> line")
    public void givenTheNextTrainIsScheduledToLeave(@Named("departure-time") LocalTime departureTime,
                                                    String line) {
        timetable.scheduleArrivalTime(line, departureTime);
        travellingOnLine = line;
    }

    LocalTime arrivalTime;

    @When("I ask for my arrival time")
    public void whenIAskForMyArrivalTime() {
        arrivalTime = timetable.getArrivalTime(travellingOnLine, destination);
    }

    @Then("the estimated arrival time should be <arrival-time>")
    public void thenTheEstimatedArrivalTimeShouldBe(@Named("arrival-time") LocalTime expectedArrivalTime) {
        //assertThat(arrivalTime).isEqualTo(expectedArrivalTime);
    }
}

