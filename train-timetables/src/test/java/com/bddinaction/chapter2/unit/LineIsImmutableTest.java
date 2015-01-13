package com.bddinaction.chapter2.unit;

import com.bddinaction.chapter2.model.Line;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LineIsImmutableTest {

    @Test
    public void stationsCanNotBeModifiedAfterLineCreation() throws Exception {
        String[] stations = {"station 1", "station 2", "station 3"};
        String[] stationsSnapshot = Arrays.copyOf(stations, stations.length);
        Line sampleLine = Line.named("favouriteLine").departingFrom("station 0").withStations(stations);
        stations[0] = "station 99";

        assertThat(sampleLine.getStations(),is(Arrays.asList(stationsSnapshot)));
    }
}
