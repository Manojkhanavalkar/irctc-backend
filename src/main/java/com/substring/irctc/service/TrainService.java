package com.substring.irctc.service;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.entity.Train;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repository.TrainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TrainService {
//    List<Train> trainList =new ArrayList<>();
//    public TrainService(){
//        trainList.add(new Train("1234","Pune-Mumbai Singhgad-Express",28));
//        trainList.add(new Train("1235","Pune-Satara Rajdhani-Express",10));
//    }
//
//
//    public Train add(Train train){
//        trainList.add(train);
//        return train;
//    }
//    public List<Train> all(){
//        return this.trainList;
//    }
//    public Train get(String trainNo){
//        Train train1= trainList.stream().filter(train -> train.getTrainNo().equals(trainNo)).findFirst().get();
//        return train1;
//    }
//    public void delete(String trainNo){
//        List<Train> list=trainList.stream().filter(train -> !train.getTrainNo().equals(trainNo)).toList();
//        this.trainList=list;
//    }


    private TrainRepository trainRepository;
    private ModelMapper modelMapper;

    public TrainService(TrainRepository trainRepository, ModelMapper modelMapper) {
        this.trainRepository = trainRepository;
        this.modelMapper = modelMapper;
    }

    public List<TrainDTO> getAllTrains(){
        List<Train> all=trainRepository.findAll();
        List<TrainDTO> listTrainDto=all.stream().map(train-> modelMapper.map(train,TrainDTO.class)).toList();
        return listTrainDto;
    }
    public TrainDTO getTrainById(String id){
        Train train=trainRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No value Present in database"));
        TrainDTO dto=modelMapper.map(train,TrainDTO.class);
        return dto;
    }
    public TrainDTO saveTrain(TrainDTO trainDto){

//        Train train=new Train();
//        train.setTrainNo(trainDto.getTrainNo());
//        train.setName(trainDto.getName());
//        train.setRouteName(trainDto.getRouteName());
        Train train=modelMapper.map(trainDto,Train.class);
        Train savedTrain=trainRepository.save(train);

//        TrainDTO dto=new TrainDTO();
//        dto.setName(savedTrain.getName());
//        dto.setTrainNo(savedTrain.getTrainNo());
//        dto.setRouteName(savedTrain.getRouteName());

        TrainDTO dto=modelMapper.map(savedTrain,TrainDTO.class);
        return dto;
    }
    public void  deleteTrain(String id){
        Train train=trainRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No value Present in database"));
        trainRepository.delete(train);
    }
}
