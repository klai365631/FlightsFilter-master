package com.gridnine.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class for testing FlightBuilder
 *
 * @see FlightBuilder
 */
public class FlightBuilderTest {
    private List<Flight> flights;
    private Flight flight;

    @BeforeEach
    public void setUp() {

        flights = FlightBuilder.createFlights();

        flight = FlightBuilder.createFlight(LocalDateTime.of(2023, 8, 6, 15, 0),
                LocalDateTime.of(2023, 8, 6, 17, 30),
                LocalDateTime.of(2023, 8, 6, 19, 0),
                LocalDateTime.of(2023, 8, 6, 20, 30));

    }

    /**
     * Test for method <b>FlightBuilder.createFlights()</b>
     */
    @Test
    public void testCreatingFlightsList() {
        Assertions.assertNotNull(flights);
    }

    /**
     * Test for comparing the expected size of the flights list with the actual one
     */
    @Test
    public void testSizeOfTheList() {
        Assertions.assertEquals(6, flights.size());
    }

    /**
     * Test for method <b>FlightBuilder.createFlight()</b>
     */
    @Test
    public void testCreatingOneFlight() {
        Assertions.assertNotNull(flight);
    }

    /**
     * Test for throwing an exception
     * @throws IllegalArgumentException
     */
    @Test
    public void testThrowingExceptionWithUnevenDatesAmount() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> FlightBuilder.createFlight(LocalDateTime.of(2023, 8, 6, 15, 0),
                        LocalDateTime.of(2023, 8, 6, 17, 30),
                        LocalDateTime.of(2023, 8, 6, 19, 0),
                        LocalDateTime.of(2023, 8, 6, 20, 30),
                        LocalDateTime.of(2023, 8, 6, 21, 0)));

        Assertions.assertEquals("you must pass an even number of dates", exception.getMessage());
    }

    /**
     * Test for not throwing an exception
     */
    @Test
    public void testNotThrowingExceptionWithEvenDatesAmount() {

        Assertions.assertDoesNotThrow(() -> FlightBuilder.createFlight(LocalDateTime.of(2023, 8, 6, 15, 0),
                LocalDateTime.of(2023, 8, 6, 17, 30),
                LocalDateTime.of(2023, 8, 6, 19, 0),
                LocalDateTime.of(2023, 8, 6, 20, 30)));
    }
}
