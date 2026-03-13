package com.substring.irctc.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class StationDTO {
    @Size(min = 3 , max = 20, message = "station code is too long should be less than 20 characters")
    @Pattern(regexp = "^[A-Z]+-[0-9]+[A-Z]$", message = "station code is invalid should be in format like MUMBAI-1E ")
    String stationCode;
    String stationName;
    String city;
    String state;
    String zone;
    int platforms;
    boolean active;

    public StationDTO() {
    }

    public StationDTO(String stationCode, String stationName, String city, String state, String zone, int platforms, boolean active) {
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
