package com.bddinaction.chapter2.model;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line {

    private final String line;
    private final String departingFrom;
    private final List<String> stations;

    private Line(String line, String departingFrom) {
        this.line = line;
        this.departingFrom = departingFrom;
        this.stations = null;
    }

    private Line(String line, String departingFrom, List<String> stations) {
        this.line = line;
        this.departingFrom = departingFrom;
        this.stations = ImmutableList.copyOf(stations);
    }

    public String getDepartingFrom() {
        return departingFrom;
    }

    public String getLine() {
        return line;
    }

    public List<String> getStations() {
        return ImmutableList.copyOf(stations);
    }

    public Line withStations(String... stations) {
        return new Line(this.line, this.departingFrom, Arrays.asList(stations));
    }

    public static LineBuilder named(String lineName) {
        return new LineBuilder(lineName);
    }

    public static final class LineBuilder {
        private final String lineName;

        public LineBuilder(String lineName) {
            this.lineName = lineName;
        }

        public Line departingFrom(String station) {
            return new Line(lineName, station);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line1 = (Line) o;

        if (departingFrom != null ? !departingFrom.equals(line1.departingFrom) : line1.departingFrom != null)
            return false;
        if (line != null ? !line.equals(line1.line) : line1.line != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = line != null ? line.hashCode() : 0;
        result = 31 * result + (departingFrom != null ? departingFrom.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Line{" +
                "line='" + line + '\'' +
                ", departingFrom='" + departingFrom + '\'' +
                '}';
    }
}
