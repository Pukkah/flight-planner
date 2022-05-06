package io.codelex.flightplanner.controller.api;

import io.codelex.flightplanner.model.Flight;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@RequiredArgsConstructor
public class SearchFlightPageResponse {
    private final Page<Flight> items;

    public List<Flight> getItems() {
        return items.getContent();
    }

    public Integer getPage() {
        return items.getNumber();
    }

    public Long getTotalItems() {
        return items.getTotalElements();
    }

}
