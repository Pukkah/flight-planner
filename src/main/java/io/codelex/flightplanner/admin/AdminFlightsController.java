package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.client.ClientFlightsService;
import io.codelex.flightplanner.entitys.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api/flights")
@RequiredArgsConstructor
public class AdminFlightsController {
    private final ClientFlightsService clientFlightsService;
    private final AdminFlightsService adminFlightsService;

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable Long id) {
        return clientFlightsService.getFlight(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Flight addFlight(@Valid @RequestBody Flight flight) {
        return adminFlightsService.addFlight(flight);
    }

    @DeleteMapping("/{id}")
    public synchronized void deleteFlight(@PathVariable Long id) {
        adminFlightsService.deleteFlight(id);
    }

}
