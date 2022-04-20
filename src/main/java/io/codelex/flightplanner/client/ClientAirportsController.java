package io.codelex.flightplanner.client;

import io.codelex.flightplanner.entitys.Airport;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class ClientAirportsController {
    private final ClientAirportsService clientAirportsService;

    @GetMapping
    public List<Airport> searchAirports(@RequestParam String search) {
        return clientAirportsService.searchAirports(search);
    }

}
