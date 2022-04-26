package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.flight.FlightService;
import io.codelex.flightplanner.flight.domain.Flight;
import io.codelex.flightplanner.flight.domain.SearchFlightRequest;
import io.codelex.flightplanner.flight.domain.SearchFlightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class ClientFlightController {
    private final FlightService flightService;

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable Long id) {
        return flightService.getFlight(id);
    }

    @PostMapping("/search")
    public SearchFlightResponse searchFlights(@Valid @RequestBody SearchFlightRequest searchFlightRequest) {
        return flightService.searchFlights(searchFlightRequest);
    }

}
