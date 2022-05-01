package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.model.Airport;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AirportRepository {
    private final List<Airport> airports = new ArrayList<>();

    public void add(Airport airport) {
        if (!airports.contains(airport)) {
            airports.add(airport);
        }
    }

    public List<Airport> find(String search) {
        return airports.stream()
                       .filter(airport -> airport.getAirport().toLowerCase().contains(search)
                               || airport.getCity().toLowerCase().contains(search)
                               || airport.getCountry().toLowerCase().contains(search))
                       .toList();
    }

    public void clear() {
        airports.clear();
    }

}
