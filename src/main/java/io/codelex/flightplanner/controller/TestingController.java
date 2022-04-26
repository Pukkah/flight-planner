package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.airport.AirportService;
import io.codelex.flightplanner.flight.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
@RequiredArgsConstructor
public class TestingController {
    private final FlightService flightService;
    private final AirportService airportService;

    @PostMapping("/clear")
    public void clear() {
        flightService.deleteAllFlights();
        airportService.deleteAllAirports();
    }

}
