package io.codelex.flightplanner.controller.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SearchFlightRequest {
    @NotEmpty
    private String from;
    @NotEmpty
    private String to;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

}
