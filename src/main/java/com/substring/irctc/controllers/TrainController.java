package com.substring.irctc.controllers;



import com.substring.irctc.entity.ImageMetaData;
import com.substring.irctc.entity.Train;
import com.substring.irctc.repository.ImageMetaDataRepository;
import com.substring.irctc.service.TrainService;
import com.substring.irctc.service.impl.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private ImageMetaDataRepository imageMetaDataRepository;

    @PostMapping("/photo")
    public ImageMetaData uploadTrainImage(
            @RequestParam("file") MultipartFile file
            ) throws IOException {
        //Process the file
        ImageMetaData imageMetaData=fileUploadService.fileUpload(file);
        imageMetaDataRepository.save(imageMetaData);
        return imageMetaData;

    }




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
