package com.planner.travel.service;

import com.planner.travel.repository.WeatherRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import com.planner.travel.model.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WeatherServiceImpl  extends MappingJackson2HttpMessageConverter  implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    private static final String IMG_URL="http://openweathermap.org/img/w/";
    private static final String IMG_EXT=".png";
    private static final int[] BETWEEN={12,15,18};


    @Override
    public List<Weather> getWeatherDisplay(String city, String date) throws ParseException, JSONException {

        String websiteResponse = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&mode=json&units=metric&appid=06725b072cebb76350078f357e906f6f";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(websiteResponse, String.class);
        JSONObject root = new JSONObject(result);
        JSONArray weatherObject = root.getJSONArray("list");

        List<Weather> weatherDataList = new ArrayList<>();
        try {

            if (!Objects.isNull(weatherObject) ){
                DateFormat sdf = new SimpleDateFormat("E MMM dd yyyy");
                Date ipDate = sdf.parse(date);

                String sdt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ipDate);

                String zid = zid=root.getJSONObject("city").getString("timezone");

                for (int i = 0; i < weatherObject.length(); i++) {

                    JSONObject arrayElement = weatherObject.getJSONObject(i);

                    JSONObject main = arrayElement.getJSONObject("main");

                    Weather weatherData = new Weather();

                    String dt = arrayElement.getString("dt_txt");

                    LocalDateTime ldt = LocalDateTime.parse(dt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    ZonedDateTime zt =ldt.atZone(ZoneId.ofOffset("UTC",ZoneOffset.ofTotalSeconds(Integer.parseInt(zid))));

                    LocalDateTime ldt2 = LocalDateTime.parse(sdt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    ZonedDateTime zt2 =ldt2.atZone(ZoneId.ofOffset("UTC",ZoneOffset.ofTotalSeconds(Integer.parseInt(zid))));

                    if (zt.getDayOfMonth()==zt2.getDayOfMonth() ) {

                        if (BETWEEN[0]==zt.getHour()||BETWEEN[1]==zt.getHour()||BETWEEN[2]==zt.getHour() ) {
                            weatherData.setCity(city);
                            weatherData.setForecastId(arrayElement.getJSONArray("weather").getJSONObject(0).getInt("id"));
                            weatherData.setDesc(arrayElement.getJSONArray("weather").getJSONObject(0).getString("description"));
                            weatherData.setFeellike(main.getString("feels_like"));
                            weatherData.setTemp(main.getDouble("temp"));
                            weatherData.setDate(dt);
                            weatherData.setIcon(IMG_URL+ arrayElement.getJSONArray("weather").getJSONObject(0).getString("icon") + IMG_EXT);

                            weatherDataList.add(weatherData);
                        }
                    }
                }
            }

        }catch(JSONException e) {
            throw new JSONException(e.getMessage());
        }

        return weatherDataList;
    }

    @Override
    public List< Weather> saveWeather(List<Weather> weather) {
        return weatherRepository.saveAll(weather);
    }

    @Override
    public List<Weather> getHistory() {
        return weatherRepository.findAll();
    }
}

