package io.codelex.flightplanner.client;

import io.codelex.flightplanner.entitys.Airport;
import io.codelex.flightplanner.repository.AirportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientAirportsService {
    private final AirportsRepository airportsRepository;

    public List<Airport> searchAirports(String search) {
        return airportsRepository.find(search.trim().toLowerCase());
    }

}
