package io.codelex.flightplanner.controller.api;

import io.codelex.flightplanner.model.Flight;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchFlightResponse {
    private final List<Flight> items;
    private final Integer page = 0;
    private final Integer totalItems;

    public SearchFlightResponse(List<Flight> items) {
        this.items = items;
        totalItems = items.size();
    }

}
