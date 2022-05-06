package io.codelex.flightplanner.service;

import io.codelex.flightplanner.model.Airport;
import io.codelex.flightplanner.repository.AirportRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    public Airport getOrCreate(Airport airport) {
        return airportRepository.findById(airport.getAirport())
                                .orElseGet(() -> airportRepository.save(airport));
    }

    public List<Airport> searchAirports(String search) {
        search = search.trim();
        Airport airport = Airport.builder().airport(search).city(search).country(search).build();
        ExampleMatcher airportMatcher = ExampleMatcher.matchingAny()
                                                      .withIgnoreCase()
                                                      .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return airportRepository.findAll(Example.of(airport, airportMatcher));
    }

    public void deleteAllAirports() {
        airportRepository.deleteAll();
    }

}
