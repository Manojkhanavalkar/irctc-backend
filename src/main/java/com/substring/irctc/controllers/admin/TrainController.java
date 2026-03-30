package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.service.TrainService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//            System.out.println(trainDTO.getNumber());
//            System.out.println(trainDTO.getName());
//            System.out.println(trainDTO.getSourceStation().getId());
            trainService.saveTrain(trainDTO)
            return new ResponseEntity<>(trainDTO, HttpStatus.CREATED);
        }
    //list

    //get detail

    //update train

    //delete
}
