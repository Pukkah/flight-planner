package io.codelex.flightplanner.airport.domain;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.stream.Collectors;

@Data
public class Airport {
    @NotEmpty
    private final String country;
    @NotEmpty
    private final String city;
    @NotEmpty
    private final String airport;

    public Airport(String airport, String country, String city) {
        this.airport = airport.toUpperCase().trim();
        this.country = capitalize(country);
        this.city = capitalize(city);
    }

    private String capitalize(String s) {
        return Arrays.stream(s.trim().split(" "))
                     .map(String::toLowerCase)
                     .map(StringUtils::capitalize)
                     .collect(Collectors.joining(" "));
    }

}
