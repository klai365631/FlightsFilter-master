package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Все полёты:\n" + flights + "\n");

        System.out.println("Все полёты, кроме тех, где вылет до текущего момента времени:\n" +
                FlightFilter.excludeOutdatedFlights(flights) + "\n");

        System.out.println("Все полёты, кроме тех, где имеются сегменты с датой прилёта раньше даты вылета:\n" +
                FlightFilter.excludeWhenArrivalIsBeforeDeparture(flights) + "\n");

        System.out.println("Все полёты, кроме тех, где общее время на земле превышает два часа:\n" +
                FlightFilter.excludeWhenGroundTimeIsLongerThanTwoHours(flights));
    }
}
