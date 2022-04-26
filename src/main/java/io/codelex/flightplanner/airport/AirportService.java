package io.codelex.flightplanner.airport;

import io.codelex.flightplanner.airport.domain.Airport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    public void add(Airport airport) {
        airportRepository.add(airport);
    }

    public List<Airport> searchAirports(String search) {
        return airportRepository.find(search.trim().toLowerCase());
    }

    public void deleteAllAirports() {
        airportRepository.clear();
    }

}
