package com.substring.irctc.service;

import com.substring.irctc.entity.Station;
import com.substring.irctc.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;
    public List<Station> getAllStations(){
        return stationRepository.findAll();
    }
    public Station getStationById(String id){
        return stationRepository.findById(id).orElse(null);
    }
    public Station saveStation(Station station){
        return stationRepository.save(station);
    }
    public void deleteById(String id){
        stationRepository.deleteById(id);
    }
}
