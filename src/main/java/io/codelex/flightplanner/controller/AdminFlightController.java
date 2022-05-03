package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.controller.api.AddFlightRequest;
import io.codelex.flightplanner.service.FlightService;
import io.codelex.flightplanner.model.Flight;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        return flightService.addFlight(addFlightRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable @NotEmpty Long id) {
        flightService.deleteFlight(id);
    }

}
