package io.codelex.flightplanner.service;

import io.codelex.flightplanner.controller.api.AddFlightRequest;
import io.codelex.flightplanner.model.Airport;
import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.model.Flight;
import io.codelex.flightplanner.controller.api.SearchFlightRequest;
import io.codelex.flightplanner.controller.api.SearchFlightPageResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportService airportService;

    @Transactional(readOnly = true)
    public Flight getFlight(Long id) {
        return flightRepository.findById(id)
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public SearchFlightPageResponse searchFlights(SearchFlightRequest req) {
        if (req.getFrom().equals(req.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        LocalDateTime dateStart = req.getDepartureDate().atStartOfDay();
        LocalDateTime dateEnd = dateStart.plusDays(1L);
        Pageable pageRequest = PageRequest.of(req.getPage(), req.getLimit());
        Page<Flight> result = flightRepository.searchFlights(req.getFrom(), req.getTo(), dateStart, dateEnd, pageRequest);
        return new SearchFlightPageResponse(result);
    }

    @Transactional
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
                              .from(airportService.getOrCreate(airportFrom))
                              .to(airportService.getOrCreate(airportTo))
                              .carrier(flightRequest.getCarrier())
                              .departureTime(flightRequest.getDepartureTime())
                              .arrivalTime(flightRequest.getArrivalTime())
                              .build();
        return flightRepository.save(flight);
    }

    @Transactional
    public void deleteFlight(Long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        }
    }

    @Transactional
    public void deleteAllFlights() {
        flightRepository.deleteAll();
    }

    private boolean isArrivalAndDepartureTimeValid(AddFlightRequest flightRequest) {
        return flightRequest.getArrivalTime().isAfter(flightRequest.getDepartureTime());
    }

}
