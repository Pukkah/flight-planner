package io.codelex.flightplanner.service;

import io.codelex.flightplanner.controller.api.AddFlightRequest;
import io.codelex.flightplanner.model.Airport;
import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.model.Flight;
import io.codelex.flightplanner.controller.api.SearchFlightRequest;
import io.codelex.flightplanner.controller.api.SearchFlightResponse;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
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

    @Synchronized
    public Flight addFlight(AddFlightRequest flightRequest) {
        Airport airportFrom = flightRequest.getFrom();
        Airport airportTo = flightRequest.getTo();
        if (airportFrom.equals(airportTo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!isArrivalAndDepartureTimeValid(flightRequest)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (flightExists(flightRequest)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        airportService.add(airportFrom);
        airportService.add(airportTo);
        Flight flight = Flight.builder()
                              .from(airportFrom)
                              .to(airportTo)
                              .carrier(flightRequest.getCarrier())
                              .departureTime(flightRequest.getDepartureTime())
                              .arrivalTime(flightRequest.getArrivalTime())
                              .build();
        return flightRepository.addFlight(flight);
    }

    private boolean isArrivalAndDepartureTimeValid(AddFlightRequest flightRequest) {
        return flightRequest.getArrivalTime().isAfter(flightRequest.getDepartureTime());
    }

    private boolean flightExists(AddFlightRequest flightRequest) {
        return flightRepository.flightExists(
                flightRequest.getFrom(),
                flightRequest.getTo(),
                flightRequest.getCarrier(),
                flightRequest.getDepartureTime(),
                flightRequest.getArrivalTime()
        );
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteFlight(id);
    }

    public void deleteAllFlights() {
        flightRepository.clear();
    }

}
