package com.planner.travel.service;

import com.planner.travel.model.Weather;
import org.json.JSONException;

import java.text.ParseException;
import java.util.List;

public interface WeatherService {
    public List<Weather> getWeatherDisplay(String city, String date) throws ParseException, JSONException;

    public List<Weather> saveWeather(List<Weather> weather);

    public List<Weather> getHistory();
}
