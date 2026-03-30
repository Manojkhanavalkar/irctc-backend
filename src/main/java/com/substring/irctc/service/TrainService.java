package com.substring.irctc.service;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.entity.Train;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repository.TrainRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.substring.irctc.dto.PageResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainService {


    private TrainRepository trainRepository;
    private ModelMapper modelMapper;



    public PageResponse<TrainDTO> getAllTrains(int page,int size,String sortBy,String sortDir){
        //code pagination and sorting
        Sort sort=sortBy.trim().toLowerCase().equals("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);

        Page<Train> trainPage=trainRepository.findAll(pageable);

        Page<TrainDTO> trainDTOPage=trainPage.map(train-> modelMapper.map(train,TrainDTO.class));
        return PageResponse.fromPage(trainDTOPage);
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
