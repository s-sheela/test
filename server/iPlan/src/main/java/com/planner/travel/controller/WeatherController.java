package com.planner.travel.controller;


import com.planner.travel.model.Weather;
import com.planner.travel.service.WeatherService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@CrossOrigin
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("forecast")
    public List<Weather> getWeatherForecast(
            @RequestParam String city,@RequestParam String date) throws ParseException, JSONException {

        return weatherService.getWeatherDisplay(city,date);
    }

}

