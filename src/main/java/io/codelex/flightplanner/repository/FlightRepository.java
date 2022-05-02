package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.model.Airport;
import io.codelex.flightplanner.model.Flight;
import io.codelex.flightplanner.controller.api.SearchFlightRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private Long counter = 0L;
    private volatile List<Flight> flights = new ArrayList<>();

    public Flight getFlight(Long id) {
        return flights.stream()
                      .filter(flight -> flight.getId().equals(id))
                      .findAny()
                      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public synchronized Flight addFlight(Flight flight) {
        flight.setId(++counter);
        flights.add(flight);
        return flight;
    }

    public boolean flightExists(
            Airport from,
            Airport to,
            String carrier,
            LocalDateTime departureTime,
            LocalDateTime arrivalTime) {
        return flights.stream()
                      .anyMatch(flight -> flight.getFrom().equals(from)
                              && flight.getTo().equals(to)
                              && flight.getCarrier().equals(carrier)
                              && flight.getDepartureTime().equals(departureTime)
                              && flight.getArrivalTime().equals(arrivalTime)
                      );
    }

    public synchronized void deleteFlight(Long id) {
        flights.stream()
               .filter(flight -> flight.getId().equals(id))
               .findAny()
               .ifPresent(flights::remove);
    }

    public List<Flight> searchFlights(SearchFlightRequest req) {
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
