package com.planner.travel.controller;

import com.planner.travel.model.Itinerary;
import com.planner.travel.model.Weather;
import com.planner.travel.service.TravelService;
import com.planner.travel.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/travel")
@CrossOrigin
public class TravelController {
    @Autowired
    private TravelService travelService;

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/add/{city}")
    public String saveItinerary(@RequestBody List<Weather> weather,@PathVariable ( value = "city") String city){

            Itinerary it = new Itinerary();
            it.setCity(city);
            it.setWeather(weather);
            travelService.saveItinerary(it);
            weatherService.saveWeather(weather);

            return "Itinerary added successfully!!";
    }

    @GetMapping("/getAll")
    public List<Itinerary> getHistory(){
        return travelService.getItinerary();
    }

    @GetMapping("/{id}")
    public Optional<Itinerary> getById(@PathVariable ( value = "id") int id){
        return travelService.getById(id);
    }
}
