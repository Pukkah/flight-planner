package io.codelex.flightplanner.flight.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.codelex.flightplanner.airport.domain.Airport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class Flight {
    @EqualsAndHashCode.Exclude
    private Long id;
    @NotNull
    @Valid
    private Airport from;
    @NotNull
    @Valid
    private Airport to;
    @NotEmpty
    private String carrier;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

}
