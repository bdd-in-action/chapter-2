package com.bddinaction.chapter2.services

import com.bddinaction.chapter2.model.Line
import org.joda.time.LocalTime
import spock.lang.Specification

class WhenFindingArrivalTimeAtStations extends Specification {

    def timetableService = new InMemoryTimetableService()

    def "should find the correct arrival times between two stations"() {
        given: "the Western line departing from Emu Plains"
            def westernLine = Line.named("Western").departingFrom("Emu Plains")
        when: "we find the arrival times at Parramatta"
            def arrivalTimes = timetableService.findArrivalTimes(westernLine, "Parramatta")
        then: "we should get the following times"
            arrivalTimes == [at(7,58), at(8,00), at(8,02), at(8,11), at(8,14), at(8,21)]
    }

    def at(int hour, int minute) {
        new LocalTime(hour, minute)
    }
}

