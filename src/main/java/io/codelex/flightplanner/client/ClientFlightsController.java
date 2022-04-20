package io.codelex.flightplanner.client;

import io.codelex.flightplanner.entitys.Flight;
import io.codelex.flightplanner.entitys.SearchFlightsRequest;
import io.codelex.flightplanner.entitys.SearchFlightsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class ClientFlightsController {
    private final ClientFlightsService clientFlightsService;

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable Long id) {
        return clientFlightsService.getFlight(id);
    }

    @PostMapping("/search")
    public SearchFlightsResponse searchFlights(@Valid @RequestBody SearchFlightsRequest searchFlightsRequest) {
        return clientFlightsService.searchFlights(searchFlightsRequest);
    }

}
