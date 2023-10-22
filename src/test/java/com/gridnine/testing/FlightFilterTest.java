package com.gridnine.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Class for testing FlightFilter
 *
 * @see FlightFilter
 */
public class FlightFilterTest {
    private List<Flight> flights;
    private List<Flight> flightsFilter1;
    private List<Flight> flightsFilter2;
    private List<Flight> flightsFilter3;
    private List<Flight> actual1;
    private List<Flight> actual2;
    private List<Flight> actual3;

    @BeforeEach
    public void setUp() {

        flights = FlightBuilder.createFlights();

        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

        actual1 = FlightFilter.excludeOutdatedFlights(flights);
        flightsFilter1 = Arrays.asList(
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));

        actual2 = FlightFilter.excludeWhenArrivalIsBeforeDeparture(flights);
        flightsFilter2 = Arrays.asList(
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                FlightBuilder.createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));

        actual3 = FlightFilter.excludeWhenGroundTimeIsLongerThanTwoHours(flights);
        flightsFilter3 = Arrays.asList(
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                FlightBuilder.createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)));
    }

    /**
     * Test for method <b>FlightFilter.excludeOutdatedFlights()</b>
     */
    @Test
    public void testExcludingOutdatedFlights() {
        Assertions.assertEquals(flightsFilter1, actual1);
    }

    /**
     * Test for method <b>FlightFilter.excludeWhenArrivalIsBeforeDeparture()</b>
     */
    @Test
    public void testExcludingWhenArrivalIsBeforeDeparture() {
        Assertions.assertEquals(flightsFilter2, actual2);
    }

    /**
     * Test for method <b>FlightFilter.excludeWhenGroundTimeIsLongerThanTwoHours()</b>
     */
    @Test
    public void testExcludingWhenGroundTimeIsLongerThanTwoHours() {
        Assertions.assertEquals(flightsFilter3, actual3);
    }
}
