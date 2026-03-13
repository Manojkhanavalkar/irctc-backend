package com.substring.irctc.controllers;



import com.substring.irctc.dto.*;
import com.substring.irctc.entity.ImageMetaData;
import com.substring.irctc.entity.Train;
import com.substring.irctc.entity.TrainImage;
import com.substring.irctc.repository.ImageMetaDataRepository;
import com.substring.irctc.service.TrainImageService;
import com.substring.irctc.service.TrainService;
import com.substring.irctc.service.impl.FileUploadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
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
    @Autowired
    private TrainImageService trainImageService;

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
    public PageResponse<TrainDTO> getAllTrains(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sortBy",defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc") String sortDir){

        return this.trainService.getAllTrains(page,size,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public TrainDTO getTrainById(@PathVariable String id){
        return trainService.getTrainById(id);
    }

    @PostMapping
    public ResponseEntity<TrainDTO> createTrain(@Valid @RequestBody TrainDTO trainDto){
        return new ResponseEntity<>(trainService.saveTrain(trainDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String deleteTrain(@PathVariable String id){
        trainService.deleteTrain(id);
        return "Train deleted successfully";
    }



    @PostMapping("/{trainNo}/upload-image")
    public ResponseEntity<?> uploadTrainImage(
            @PathVariable String trainNo,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        String contentType=image.getContentType();
        if(contentType.toLowerCase().startsWith("image")){
            return  new ResponseEntity<>(trainImageService.upload(image,trainNo),HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(new ErrorResponse("Image not uploaded","403",false),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{trainId}/image")
    public ResponseEntity<Resource> serveTrainImage(@PathVariable String trainId) throws MalformedURLException {
       TrainImageDataWithResource trainImageDataWithResource= trainImageService.loadImageByTrainNo(trainId);
        TrainImage trainImage = trainImageDataWithResource.trainImage();
        return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(trainImage.getFileType()))
               .body(trainImageDataWithResource.resource());
    }
}
