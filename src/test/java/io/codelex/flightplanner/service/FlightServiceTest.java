package io.codelex.flightplanner.service;

import io.codelex.flightplanner.controller.api.AddFlightRequest;
import io.codelex.flightplanner.model.Airport;
import io.codelex.flightplanner.model.Flight;
import io.codelex.flightplanner.repository.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @InjectMocks
    FlightService flightService;

    @Mock
    FlightRepository flightRepository;
    @Mock
    AirportService airportService;

    @Test
    public void testAddFlight() {
        Airport from = new Airport("RIX", "Riga", "Latvia");
        Airport to = new Airport("LAX", "Los Angeles", "USA");
        String carrier = "WizAir";
        LocalDateTime departureTime = LocalDateTime.now();
        LocalDateTime arrivalTime = departureTime.plusHours(9L);

        AddFlightRequest addFlightRequest = new AddFlightRequest(from, to, carrier, departureTime, arrivalTime);

        Flight expectedFlight = Flight.builder()
                                      .from(from)
                                      .to(to)
                                      .carrier(carrier)
                                      .departureTime(departureTime)
                                      .arrivalTime(arrivalTime)
                                      .build();

        Mockito.doAnswer(invocation -> {
            Flight req = invocation.getArgument(0);
            Assertions.assertEquals(expectedFlight, req);
            return req.toBuilder().id(1L).build();
        }).when(flightRepository).save(Mockito.any());

        Flight flight = flightService.addFlight(addFlightRequest);

        Mockito.verify(flightRepository, Mockito.times(1)).exists(Mockito.any());
        Mockito.verify(flightRepository, Mockito.times(1)).save(Mockito.any(Flight.class));
        Mockito.verify(airportService, Mockito.times(2)).getOrCreate(Mockito.any(Airport.class));
        Assertions.assertEquals(1L, flight.getId());

    }

}
