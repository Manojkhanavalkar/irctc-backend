package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.service.TrainService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminTrainController")
@RequestMapping("/admin/trains")
@AllArgsConstructor
public class TrainController {
    private TrainService trainService;
    //create
    @PostMapping
    public ResponseEntity<TrainDTO> createTrain(
            @RequestBody TrainDTO trainDTO
    ){
        TrainDTO dto=trainService.createTrain(trainDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //list
    @GetMapping
    public ResponseEntity<List<TrainDTO>> getAllTrains(){
        List<TrainDTO> allTrains=trainService.getAllTrains();
        return new ResponseEntity<>(allTrains, HttpStatus.OK);
    }
    //get detail
    @GetMapping("/{id}")
    public ResponseEntity<TrainDTO> getTrainById(
            @PathVariable Long id
    ){
        TrainDTO dto=trainService.getTrainById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    //update train
    @PutMapping("{id}")
    public ResponseEntity<TrainDTO> updateTrain(
            @PathVariable Long id,
            @RequestBody TrainDTO trainDTO
    ){
        TrainDTO dto=trainService.updateTrain(id, trainDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTrain(
            @PathVariable Long id
    ){
        trainService.deleteTrain(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
