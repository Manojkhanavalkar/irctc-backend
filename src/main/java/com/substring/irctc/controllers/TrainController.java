package com.substring.irctc.controllers;



import com.substring.irctc.entity.Train;
import com.substring.irctc.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
