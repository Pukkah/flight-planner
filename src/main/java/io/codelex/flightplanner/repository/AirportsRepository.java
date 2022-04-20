package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.entitys.Airport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AirportsRepository {
    private final List<Airport> airports = new ArrayList<>();

    public void save(Airport airport) {
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
