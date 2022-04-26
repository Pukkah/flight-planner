package io.codelex.flightplanner.flight;

import io.codelex.flightplanner.airport.AirportService;
import io.codelex.flightplanner.flight.domain.Flight;
import io.codelex.flightplanner.flight.domain.SearchFlightRequest;
import io.codelex.flightplanner.flight.domain.SearchFlightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportService airportService;

    public Flight getFlight(Long id) {
        return flightRepository.getFlight(id);
    }

    public SearchFlightResponse searchFlights(SearchFlightRequest req) {
        if (req.getFrom().equals(req.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new SearchFlightResponse(flightRepository.searchFlights(req));
    }

    public Flight addFlight(Flight flight) {
        if (flightRepository.flightExists(flight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (flight.getFrom().equals(flight.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!flight.getArrivalTime().isAfter(flight.getDepartureTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        airportService.add(flight.getFrom());
        airportService.add(flight.getTo());
        return flightRepository.addFlight(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteFlight(id);
    }

    public void deleteAllFlights() {
        flightRepository.clear();
    }

}
