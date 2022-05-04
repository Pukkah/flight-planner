package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.service.FlightService;
import io.codelex.flightplanner.model.Flight;
import io.codelex.flightplanner.controller.api.SearchFlightRequest;
import io.codelex.flightplanner.controller.api.SearchFlightResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class ClientFlightController {
    private final FlightService flightService;

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable @NotEmpty Long id) {
        return flightService.getFlight(id);
    }

    @PostMapping("/search")
    public SearchFlightResponse searchFlights(@RequestBody @Valid SearchFlightRequest searchFlightRequest,
                                              @RequestParam(defaultValue = "0") Integer page) {
        return flightService.searchFlights(searchFlightRequest, page);
    }

}
