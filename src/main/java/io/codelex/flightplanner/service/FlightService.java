package io.codelex.flightplanner.service;

import io.codelex.flightplanner.controller.api.AddFlightRequest;
import io.codelex.flightplanner.model.Airport;
import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.model.Flight;
import io.codelex.flightplanner.controller.api.SearchFlightRequest;
import io.codelex.flightplanner.controller.api.SearchFlightResponse;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportService airportService;

    public Flight getFlight(Long id) {
        return flightRepository.findById(id)
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public SearchFlightResponse searchFlights(SearchFlightRequest req, Integer page) {
        if (req.getFrom().equals(req.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        LocalDateTime dateStart = req.getDepartureDate().atStartOfDay();
        LocalDateTime dateEnd = dateStart.plusDays(1L);
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<Flight> result = flightRepository.searchFlights(req.getFrom(), req.getTo(), dateStart, dateEnd, pageRequest);
        return new SearchFlightResponse(result);
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
        Flight flight = Flight.builder()
                              .from(airportFrom)
                              .to(airportTo)
                              .carrier(flightRequest.getCarrier())
                              .departureTime(flightRequest.getDepartureTime())
                              .arrivalTime(flightRequest.getArrivalTime())
                              .build();
        if (flightRepository.exists(Example.of(flight))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        airportService.add(airportFrom);
        airportService.add(airportTo);
        return flightRepository.save(flight);
    }

    private boolean isArrivalAndDepartureTimeValid(AddFlightRequest flightRequest) {
        return flightRequest.getArrivalTime().isAfter(flightRequest.getDepartureTime());
    }

    public void deleteFlight(Long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        }
    }

    public void deleteAllFlights() {
        flightRepository.deleteAll();
    }

}
