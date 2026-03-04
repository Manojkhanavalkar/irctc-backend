package com.substring.irctc.controllers;

import com.substring.irctc.entity.Station;
import com.substring.irctc.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping
    public List<Station> getAllStations(){
        return stationService.getAllStations();
    }

    @GetMapping("/{id}")
    public Station getStationById(@PathVariable String id){
        return stationService.getStationById(id);
    }

    @PostMapping
    public Station createStation(@RequestBody Station station){
        return stationService.saveStation(station);
    }

    @DeleteMapping("/{id}")
    public String deleteStation(@PathVariable String id){
        stationService.deleteById(id);
        return "station is deleted with id: "+id;
    }

}
