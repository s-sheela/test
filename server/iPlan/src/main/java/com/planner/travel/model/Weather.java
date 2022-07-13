package com.planner.travel.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="weather")
public class Weather {

    @Id
    @GeneratedValue
    @Column(name="weather_id")
    private int weatherId;

    @Column(name="forecast_id")
    private int forecastId;

    @Column(name="description")
    private String desc;

    @Column(name="temp")
    private Double temp;

    @Column(name="icon")
    private String icon;

    @Column(name="weather_date")
    private String date;

    @Column(name="feelike")
    private String feellike;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "weather_id", nullable = false, referencedColumnName = "id",insertable=false, updatable=false)
    private Itinerary itinerary;

    private String city;

    public Weather() {
    }

    public Weather(int id, int forecastId, String desc, Double temp, String icon, String date, String feellike, Itinerary itinerary, String city) {
        this.weatherId = id;
        this.forecastId = forecastId;
        this.desc = desc;
        this.temp = temp;
        this.icon = icon;
        this.date = date;
        this.feellike = feellike;
        this.itinerary = itinerary;
        this.city = city;
    }

    public int getId() {
        return weatherId;
    }

    public void setId(int id) {
        this.weatherId = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getIcon() {
        return icon;
    }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getForecastId() {
        return forecastId;
    }

    public void setForecastId(int forecastId) {
        this.forecastId = forecastId;
    }

    public String getFeellike() {
        return feellike;
    }

    public void setFeellike(String feellike) {
        this.feellike = feellike;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
}
