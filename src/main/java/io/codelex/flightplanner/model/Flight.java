package io.codelex.flightplanner.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights", uniqueConstraints = { @UniqueConstraint(name = "flights_should_be_unique",
        columnNames = { "airport_from", "airport_to", "carrier", "departureTime", "arrivalTime" }) })
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "airport_from_fk_airport"),
            name = "airport_from", referencedColumnName = "airport")
    private Airport from;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "airport_to_fk_airport"),
            name = "airport_to", referencedColumnName = "airport")
    private Airport to;
    private String carrier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

}
