package com.substring.irctc.service.impl;

import com.substring.irctc.dto.TrainRouteDto;
import com.substring.irctc.entity.Station;
import com.substring.irctc.entity.Train;
import com.substring.irctc.entity.TrainRoute;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repository.StationRepository;
import com.substring.irctc.repository.TrainRepository;
import com.substring.irctc.repository.TrainRouteRepository;
import com.substring.irctc.service.TrainRouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainRouteServiceImpl implements TrainRouteService {

    private TrainRepository trainRepository;
    private StationRepository stationRepository;
    private TrainRouteRepository trainRouteRepository;
    private ModelMapper modelMapper;

        public TrainRouteServiceImpl(TrainRepository trainRepository, StationRepository stationRepository, TrainRouteRepository trainRouteRepository, ModelMapper modelMapper) {
            this.trainRepository = trainRepository;
            this.stationRepository = stationRepository;
            this.trainRouteRepository = trainRouteRepository;
            this.modelMapper = modelMapper;
        }



    @Override
    public TrainRouteDto addRoute(TrainRouteDto dto) {
        Train train = this.trainRepository.findById(dto.getTrain().getId()).orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + dto.getTrain().getId()));
        Station station = this.stationRepository.findById(dto.getStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Station not found with id: " + dto.getStation().getId()));

        //convert DTO to entity
        TrainRoute trainRoute=modelMapper.map(dto, TrainRoute.class);
        trainRoute.setTrain(train);
        trainRoute.setStation(station);
        // Save the TrainRoute entity
        TrainRoute savedTrainRoute=trainRouteRepository.save(trainRoute);

        TrainRouteDto savedTrainRouteDto=modelMapper.map(savedTrainRoute, TrainRouteDto.class);

        return savedTrainRouteDto;
    }

    @Override
    public List<TrainRouteDto> getRoutesByTrain(Long trainId) {
        Train train = this.trainRepository.findById(trainId).orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + trainId));
        List<TrainRoute> trainRoutes=this.trainRouteRepository.findByTrain(train);
        List<TrainRouteDto> trainRouteDtos= trainRoutes.stream().map(trainroute-> modelMapper.map(trainroute,TrainRouteDto.class)).toList();
        return trainRouteDtos;
    }

    @Override
    public TrainRouteDto updateRoute(Long id, TrainRouteDto dto) {
        TrainRoute existingRoute = this.trainRouteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Train route not found with id: " + id));
        Station station = this.stationRepository.findById(dto.getStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Station not found with id: " + dto.getStation().getId()));
        Train train=this.trainRepository.findById(dto.getTrain().getId()).orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + dto.getTrain().getId()));
        //update the existing values
        existingRoute.setStation(station);
        existingRoute.setTrain(train);
        existingRoute.setStationOrder(dto.getStationOrder());
        existingRoute.setArrivalTime(dto.getArrivalTime());
        existingRoute.setDepartureTime(dto.getDepartureTime());
        existingRoute.setHaltMinutes(dto.getHaltMinutes());

        //save the updated route
        TrainRoute updatedRoute=this.trainRouteRepository.save(existingRoute);
        //convert updated entity back to DTO
        TrainRouteDto updatedRouteDto=modelMapper.map(updatedRoute,TrainRouteDto.class);
        return updatedRouteDto;
    }

    @Override
    public void deleteRoute(Long id) {
        TrainRoute existingRoute = this.trainRouteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Train route not found with id: " + id));
        //delete the route
        trainRouteRepository.delete(existingRoute);
    }
}
