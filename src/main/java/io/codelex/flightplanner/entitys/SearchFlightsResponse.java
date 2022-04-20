package io.codelex.flightplanner.entitys;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchFlightsResponse {
    private final List<Flight> items;
    private final Integer page = 0;
    private final Integer totalItems;

    public SearchFlightsResponse(List<Flight> items) {
        this.items = items;
        totalItems = items.size();
    }

}
