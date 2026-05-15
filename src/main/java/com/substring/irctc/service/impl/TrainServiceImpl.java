package com.substring.irctc.service.impl;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.entity.Station;
import com.substring.irctc.entity.Train;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repository.StationRepository;
import com.substring.irctc.repository.TrainRepository;
import com.substring.irctc.service.TrainService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainServiceImpl implements TrainService {

    private StationRepository stationRepository;
    private ModelMapper modelMapper;
    private TrainRepository trainRepository;

    @Override
    public TrainDTO createTrain(TrainDTO trainDTO) {

        Long sid=trainDTO.getSourceStation().getId();
        Long did=trainDTO.getDestinationStation().getId();

        Station sourceStation=stationRepository.findById(sid).orElseThrow(()->new ResourceNotFoundException("Source Station not found with given id:"+sid));
        Station destinationStation=stationRepository.findById(did).orElseThrow(()->new ResourceNotFoundException("Destination Station not found with given id:"+did));
        Train train = modelMapper.map(trainDTO, Train.class);
        train.setSourceStation(sourceStation);
        train.setDestinationStation(destinationStation);
        Train savedTrain = trainRepository.save(train);

        return modelMapper.map(savedTrain, TrainDTO.class);
    }

    @Override
    public List<TrainDTO> getAllTrains() {
        List<Train> all = trainRepository.findAll();
        return all.stream().map(train -> modelMapper.map(train, TrainDTO.class)).toList();
    }

    @Override
    public TrainDTO getTrainById(Long id) {
        Train train=trainRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Train not found with given id:"+id));
        return modelMapper.map(train, TrainDTO.class);
    }

    @Override
    public TrainDTO updateTrain(Long id, TrainDTO trainDTO) {
        Train existingTrain=trainRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Train not found with given id:"+id));
        existingTrain.setName(trainDTO.getName());
        existingTrain.setNumber(trainDTO.getNumber());
        existingTrain.setTotalDistance(trainDTO.getTotalDistance());
        //fetch source and destination station

        Station sourceStation=stationRepository.findById(trainDTO.getSourceStation().getId()).orElseThrow(()->new ResourceNotFoundException("Source Station not found with given id:"+trainDTO.getSourceStation().getId()));
        Station destinationStation=stationRepository.findById(trainDTO.getDestinationStation().getId()).orElseThrow(()->new ResourceNotFoundException("Destination Station not found with given id:"+trainDTO.getDestinationStation().getId()));
        existingTrain.setSourceStation(sourceStation);
        existingTrain.setDestinationStation(destinationStation);
        Train updatedTrain=trainRepository.save(existingTrain);

        return modelMapper.map(updatedTrain, TrainDTO.class);
    }

    @Override
    public void deleteTrain(Long id) {
        Train existingTrain=trainRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Train not found with given id:"+id));
        trainRepository.delete(existingTrain);
    }
}
