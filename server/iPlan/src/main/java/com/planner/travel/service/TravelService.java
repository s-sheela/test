package com.planner.travel.service;

import com.planner.travel.model.Itinerary;

import java.util.List;
import java.util.Optional;

public interface TravelService {
    public Itinerary saveItinerary(Itinerary itinerary);
    public List<Itinerary> getItinerary();
    public Optional<Itinerary> getById(int idd);
}
