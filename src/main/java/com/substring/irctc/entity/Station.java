package com.substring.irctc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Station {
    @Id
    String stationCode;
    String stationName;
    String city;
    String state;
    String zone;
    int platforms;
    boolean active;

    public Station() {
    }

    public Station(String stationCode, String stationName, String city, String state, String zone, int platforms, boolean active) {
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.city = city;
        this.state = state;
        this.zone = zone;
        this.platforms = platforms;
        this.active = active;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getPlatforms() {
        return platforms;
    }

    public void setPlatforms(int platforms) {
        this.platforms = platforms;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
