package io.codelex.flightplanner.client;

import io.codelex.flightplanner.entitys.Flight;
import io.codelex.flightplanner.entitys.SearchFlightsRequest;
import io.codelex.flightplanner.entitys.SearchFlightsResponse;
import io.codelex.flightplanner.repository.FlightsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ClientFlightsService {
    private final FlightsRepository flightsRepository;

    public Flight getFlight(Long id) {
        return flightsRepository.getFlight(id);
    }

    public SearchFlightsResponse searchFlights(SearchFlightsRequest req) {
        if (req.getFrom().equals(req.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new SearchFlightsResponse(flightsRepository.searchFlights(req));
    }

}
