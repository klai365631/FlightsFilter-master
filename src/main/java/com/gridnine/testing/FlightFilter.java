package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to exclude some flights from the list according to certain parameters.
 */
public class FlightFilter {

    /**
     * Method to exclude flights with expired departure date.
     * @param flights
     */
    public static List<Flight> excludeOutdatedFlights(List<Flight> flights) {
        List<Flight> suitableFlights = flights.stream()
                .filter(flight -> !flight.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
        return suitableFlights;
    }

    /**
     * Method to exclude flights with segments where arrival date is before departure date.
     * @param flights
     */
    public static List<Flight> excludeWhenArrivalIsBeforeDeparture(List<Flight> flights) {
        List<Flight> suitableFlights = new ArrayList<>();
        for (Flight f : flights) {
            for (Segment s: f.getSegments()) {
                if (!s.getArrivalDate().isBefore(s.getDepartureDate())) {
                    suitableFlights.add(f);
                    break;
                }
            }
        }
        return suitableFlights;

    }

    /**
     * Method to exclude flights where overall ground time is more than 2 hours.
     * @param flights
     */
    public static List<Flight> excludeWhenGroundTimeIsLongerThanTwoHours(List<Flight> flights) {
        List<Flight> suitableFlights = new ArrayList<>();
        int groundTime = 0;
        for (Flight f : flights) {
            for (int i = 0; i < (f.getSegments().size() - 1); i++) {
                if (f.getSegments().get(i).getArrivalDate().getHour() < f.getSegments().get(i + 1).getDepartureDate().getHour()) {
                    groundTime += f.getSegments().get(i + 1).getDepartureDate().getHour() -
                            f.getSegments().get(i).getArrivalDate().getHour();
                } else {
                    groundTime += f.getSegments().get(i + 1).getDepartureDate().getHour() + 24 -
                            f.getSegments().get(i).getArrivalDate().getHour();
                }
            }
            if (groundTime <= 2) {
                suitableFlights.add(f);
            }
        }
        return suitableFlights;
    }
}
