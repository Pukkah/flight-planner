package io.codelex.flightplanner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.stream.Collectors;

@Entity
@Table(name = "airports")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Airport {
    @Id
    @NotEmpty
    private String airport;
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;

    public void setAirport(String airport) {
        this.airport = airport.toUpperCase().trim();
    }

    public void setCity(String city) {
        this.city = capitalize(city);
    }

    public void setCountry(String country) {
        this.country = capitalize(country);
    }

    private String capitalize(String s) {
        return Arrays.stream(s.trim().split(" "))
                     .map(String::toLowerCase)
                     .map(StringUtils::capitalize)
                     .collect(Collectors.joining(" "));
    }

}
