package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.entitys.Flight;
import io.codelex.flightplanner.entitys.SearchFlightsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightsRepository {
    private Long counter = 0L;
    private final List<Flight> flights = new ArrayList<>();

    public Flight getFlight(Long id) {
        return flights.stream()
                      .filter(flight -> flight.getId().equals(id))
                      .findAny()
                      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Flight addFlight(Flight flight) {
        flight.setId(++counter);
        flights.add(flight);
        return flight;
    }

    public boolean flightExists(Flight flight) {
        return flights.stream()
                      .anyMatch(f -> f.equals(flight));
    }

    public void deleteFlight(Long id) {
        flights.stream()
               .filter(flight -> flight.getId().equals(id))
               .findAny()
               .ifPresent(flights::remove);
    }

    public List<Flight> searchFlights(SearchFlightsRequest req) {
        return flights.stream()
                      .filter(flight -> flight.getFrom().getAirport().equals(req.getFrom())
                              && flight.getTo().getAirport().equals(req.getTo())
                              && flight.getDepartureTime().toLocalDate().equals(req.getDepartureDate())
                      ).toList();
    }

    public void clear() {
        flights.clear();
    }

}
