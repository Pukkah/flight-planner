package io.codelex.flightplanner.model;

import io.codelex.flightplanner.utils.StringFormatUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
        this.airport = StringFormatUtils.toUpperCase(airport);
    }

    public void setCity(String city) {
        this.city = StringFormatUtils.capitalize(city);
    }

    public void setCountry(String country) {
        this.country = StringFormatUtils.capitalize(country);
    }

}
