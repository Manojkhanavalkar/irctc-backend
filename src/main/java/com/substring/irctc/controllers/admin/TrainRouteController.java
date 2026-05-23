package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainRouteDto;
import com.substring.irctc.service.TrainRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-routes")
public class TrainRouteController {
    private TrainRouteService trainRouteService;
    public TrainRouteController(TrainRouteService trainRouteService) {
        this.trainRouteService = trainRouteService;
    }


    //create train route
    @PostMapping
    public ResponseEntity<TrainRouteDto> addTrainRoute(@RequestBody TrainRouteDto dto){
        TrainRouteDto savedTrainRoute=this.trainRouteService.addRoute(dto);
        return ResponseEntity.status(201).body(savedTrainRoute);
    }

    //List train routes by ID
    @GetMapping("/train/{trainId}")
    public ResponseEntity<List<TrainRouteDto>> getTrainRoute(@PathVariable("trainId") Long trainId){
        List<TrainRouteDto> routes=trainRouteService.getRoutesByTrain(trainId);
        return ResponseEntity.status(200).body(routes);
    }
    //Update train route
    @PutMapping("/{id}")
    public ResponseEntity<TrainRouteDto> updateTrainRoute(@PathVariable("id") Long id, @RequestBody TrainRouteDto dto) {
        TrainRouteDto updatedTrainRoute = this.trainRouteService.updateRoute(id, dto);
        return ResponseEntity.status(200).body(updatedTrainRoute);
    }
    //Delete train route

    //Additional methods for train route management can be added here
}
