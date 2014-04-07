package com.bddinaction.chapter2.services;

import com.bddinaction.chapter2.model.Line;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static ch.lambdaj.Lambda.filter;

public class ItineraryService {

    private static final int MAX_ARRIVAL_TIMES = 4;
    private final InMemoryTimetableService timetableService;

    public ItineraryService(InMemoryTimetableService timetableService) {
        this.timetableService = timetableService;
    }

    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        List<Line> lines = timetableService.findLinesThrough(departure, destination);
        List<LocalTime> allArrivalTimes = getArrivalTimesOnLines(lines, departure);
        List<LocalTime> candidateArrivalTimes = findArrivalTimesAfter(startTime, allArrivalTimes);
        return atMost(MAX_ARRIVAL_TIMES, candidateArrivalTimes);
    }

    private List<LocalTime> getArrivalTimesOnLines(List<Line> lines, String station) {
        TreeSet<LocalTime> allArrivalTimes = Sets.newTreeSet();
        for(Line line : lines) {
            allArrivalTimes.addAll(timetableService.findArrivalTimes(line, station));
        }
        return new ArrayList<LocalTime>(allArrivalTimes);
    }

    private List<LocalTime> atMost(int maxTimes, List<LocalTime> candidateTimes) {
        return Lists.partition(candidateTimes, maxTimes).get(0);
    }

    private List<LocalTime> findArrivalTimesAfter(LocalTime startTime, List<LocalTime> allArrivalTimes) {
        List<LocalTime> viableArrivalTimes = Lists.newArrayList();
        for(LocalTime arrivalTime : allArrivalTimes) {
            if (arrivalTime.isAfter(startTime)) {
                viableArrivalTimes.add(arrivalTime);
            }
        }
        return viableArrivalTimes;
    }
}
