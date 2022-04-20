package io.codelex.flightplanner.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SearchFlightsRequest {
    @NotEmpty
    private String from;
    @NotEmpty
    private String to;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

}
