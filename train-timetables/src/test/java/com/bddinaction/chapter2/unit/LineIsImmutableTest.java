package com.bddinaction.chapter2.unit;

import com.bddinaction.chapter2.model.Line;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LineIsImmutableTest {
    private String[] inputStations = new String[]{"station 1", "station 2", "station 3"};
    private String[] inputStationsCopy = Arrays.copyOf(inputStations, inputStations.length);
    private Line sampleLine;

    @Before
    public void setUp() throws Exception {
        sampleLine = Line.named("favouriteLine").departingFrom("station 0").withStations(inputStations);
    }

    @Test
    public void stationsCanNotBeModifiedAfterLineCreation() throws Exception {
        inputStations[0] = "station 99";

        assertThat(sampleLine.getStations(),is(Arrays.asList(inputStationsCopy)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void returnedLineStationCanNotBeModified() throws Exception {
        sampleLine.getStations().add("station 99");
    }
}
