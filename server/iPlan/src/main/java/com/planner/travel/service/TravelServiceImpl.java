package com.planner.travel.service;

import com.planner.travel.model.Itinerary;
import com.planner.travel.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelRepository travelRepository;

    @Override
    public Itinerary saveItinerary(Itinerary travel) {
        return travelRepository.save(travel);
    }

    @Override
    public List<Itinerary> getItinerary() {
        return travelRepository.findAll();
    }

    @Override
    public Optional<Itinerary> getById(int id) {
        return travelRepository.findById(id);
    }
}
