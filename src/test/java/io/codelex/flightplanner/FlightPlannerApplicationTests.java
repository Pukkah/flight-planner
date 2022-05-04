package io.codelex.flightplanner;

import io.codelex.flightplanner.controller.AdminFlightController;
import io.codelex.flightplanner.controller.api.AddFlightRequest;
import io.codelex.flightplanner.model.Airport;
import io.codelex.flightplanner.model.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class FlightPlannerApplicationTests {

    @Autowired
    AdminFlightController adminFlightController;

    @Test
    void addFlightTest() {
        Airport from = new Airport("RIX", "Latvia", "Riga");
        Airport to = new Airport("LAX", "USA", "Los Angeles");
        String carrier = "WizAir";
        LocalDateTime departureTime = LocalDateTime.now();
        LocalDateTime arrivalTime = departureTime.plusHours(9L);

        AddFlightRequest addFlightRequest = new AddFlightRequest(from, to, carrier, departureTime, arrivalTime);

        Flight flight = adminFlightController.addFlight(addFlightRequest);
        Assertions.assertNotNull(flight.getId());
        Assertions.assertEquals(from, flight.getFrom());
        Assertions.assertEquals(to, flight.getTo());
        Assertions.assertEquals(carrier, flight.getCarrier());
        Assertions.assertEquals(departureTime, flight.getDepartureTime());
        Assertions.assertEquals(arrivalTime, flight.getArrivalTime());
    }

}
