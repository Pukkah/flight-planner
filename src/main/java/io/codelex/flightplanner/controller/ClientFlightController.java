package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.service.FlightService;
import io.codelex.flightplanner.model.Flight;
import io.codelex.flightplanner.controller.api.SearchFlightRequest;
import io.codelex.flightplanner.controller.api.SearchFlightPageResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public SearchFlightPageResponse searchFlights(@RequestBody @Valid SearchFlightRequest searchFlightRequest,
                                                  @RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer limit) {
        searchFlightRequest.setPage(page);
        searchFlightRequest.setLimit(limit);
        return flightService.searchFlights(searchFlightRequest);
    }

}
