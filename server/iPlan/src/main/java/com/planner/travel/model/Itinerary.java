package com.planner.travel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="itinerary")
public class Itinerary {
    @Id
    @GeneratedValue
    private int id;

    private String city;

    @OneToMany(mappedBy = "itinerary", fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    @ElementCollection(targetClass = Weather.class)
    private List<Weather> weather;

    public Itinerary() {
    }

    public Itinerary(int id, String city, List<Weather> weather) {
        this.id = id;
        this.city = city;
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
