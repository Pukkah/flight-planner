package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.controller.api.AddFlightRequest;
import io.codelex.flightplanner.service.FlightService;
import io.codelex.flightplanner.model.Flight;

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
    public synchronized Flight addFlight(@RequestBody @Valid AddFlightRequest flightRequest) {
        return flightService.addFlight(flightRequest);
    }

    @DeleteMapping("/{id}")
    public synchronized void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

}
