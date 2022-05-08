package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.controller.api.AddFlightRequest;
import io.codelex.flightplanner.service.FlightService;
import io.codelex.flightplanner.model.Flight;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/admin-api/flights")
@RequiredArgsConstructor
public class AdminFlightController {
    private final FlightService flightService;

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable @NotEmpty Long id) {
        return flightService.getFlight(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flight addFlight(@RequestBody @Valid AddFlightRequest addFlightRequest) {
        try {
            return flightService.addFlight(addFlightRequest);
        } catch (DataIntegrityViolationException ignored) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable @NotEmpty Long id) {
        flightService.deleteFlight(id);
    }

}
