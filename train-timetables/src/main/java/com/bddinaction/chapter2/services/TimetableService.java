package com.bddinaction.chapter2.services;

import com.bddinaction.chapter2.model.Line;
import org.joda.time.LocalTime;

import java.util.List;


public interface TimetableService {
    List<LocalTime> findArrivalTimes(Line line, String targetStation);
    List<Line> findLinesThrough(String departure, String destination);
    void scheduleArrivalTime(String line, LocalTime departureTime);
    LocalTime getArrivalTime(String travellingOnLine, String destination);
}
