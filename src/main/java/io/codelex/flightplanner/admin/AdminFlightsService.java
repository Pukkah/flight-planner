package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.entitys.Flight;
import io.codelex.flightplanner.repository.AirportsRepository;
import io.codelex.flightplanner.repository.FlightsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AdminFlightsService {
    private final FlightsRepository flightsRepository;
    private final AirportsRepository airportsRepository;

    public Flight addFlight(Flight flight) {
        if (flightsRepository.flightExists(flight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (flight.getFrom().equals(flight.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!flight.getArrivalTime().isAfter(flight.getDepartureTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        airportsRepository.save(flight.getFrom());
        airportsRepository.save(flight.getTo());
        return flightsRepository.addFlight(flight);
    }

    public void deleteFlight(Long id) {
        flightsRepository.deleteFlight(id);
    }

}
