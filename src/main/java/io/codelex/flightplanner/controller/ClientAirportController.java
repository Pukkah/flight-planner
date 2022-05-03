package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.model.Airport;
import io.codelex.flightplanner.service.AirportService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class ClientAirportController {
    private final AirportService airportService;

    @GetMapping
    public List<Airport> searchAirports(@RequestParam @NotEmpty String search) {
        return airportService.searchAirports(search);
    }

}
