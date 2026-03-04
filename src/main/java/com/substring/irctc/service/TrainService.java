package com.substring.irctc.service;

import com.substring.irctc.entity.Train;
import com.substring.irctc.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private TrainRepository trainRepository;

    public List<Train> getAllTrains(){
        return trainRepository.findAll();
    }
    public Train getTrainById(String id){
        return trainRepository.findById(id).orElse(null);
    }
    public Train saveTrain(Train train){
        return trainRepository.save(train);
    }
    public void  deleteTrain(String id){
        trainRepository.deleteById(id);
    }
}
