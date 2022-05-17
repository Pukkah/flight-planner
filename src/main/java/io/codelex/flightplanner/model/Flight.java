package io.codelex.flightplanner.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights", uniqueConstraints = { @UniqueConstraint(name = "flights_should_be_unique",
        columnNames = { "airport_from", "airport_to", "carrier", "departureTime", "arrivalTime" }) })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Flight {
    @Id
    @GeneratedValue(generator = "flight_id_sequence")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "flight_airport_from_fkey"),
            name = "airport_from", referencedColumnName = "airport")
    private Airport from;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "flight_airport_to_fkey"),
            name = "airport_to", referencedColumnName = "airport")
    private Airport to;
    private String carrier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

}
