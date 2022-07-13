package com.planner.travel.repository;

import com.planner.travel.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Itinerary,Integer> {
}
