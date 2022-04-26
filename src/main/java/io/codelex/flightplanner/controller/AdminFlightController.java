package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.flight.FlightService;
import io.codelex.flightplanner.flight.domain.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api/flights")
@RequiredArgsConstructor
public class AdminFlightController {
    private final FlightService flightService;

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable Long id) {
        return flightService.getFlight(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Flight addFlight(@Valid @RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @DeleteMapping("/{id}")
    public synchronized void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

}
