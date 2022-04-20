package io.codelex.flightplanner.testing;

import io.codelex.flightplanner.repository.AirportsRepository;
import io.codelex.flightplanner.repository.FlightsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestingService {
    private final FlightsRepository flightsRepository;
    private final AirportsRepository airportsRepository;

    public void clear() {
        flightsRepository.clear();
        airportsRepository.clear();
    }

}
