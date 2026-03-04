package com.substring.irctc.controllers;



import com.substring.irctc.entity.Train;
import com.substring.irctc.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;


    //getAll
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    @GetMapping
//    public List<Train> all(){
//        return this.trainService.all();
//    }
    @GetMapping
    public List<Train> getAllTrains(){
        return trainService.getAllTrains();
    }

    @GetMapping("/{id}")
    public Train getTrainById(@PathVariable String id){
        return trainService.getTrainById(id);
    }

    @PostMapping
    public Train createTrain(@RequestBody Train train){
        return trainService.saveTrain(train);
    }

    @DeleteMapping("/{id}")
    public String deleteTrain(@PathVariable String id){
        trainService.deleteTrain(id);
        return "Train deleted successfully";
    }

}
