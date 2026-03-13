package com.substring.irctc.service;

import com.substring.irctc.dto.PageResponse;
import com.substring.irctc.dto.StationDTO;
import com.substring.irctc.entity.Station;
import com.substring.irctc.repository.StationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private StationRepository stationRepository;
    private ModelMapper modelMapper;

    public StationService(StationRepository stationRepository, ModelMapper modelMapper) {
        this.stationRepository = stationRepository;
        this.modelMapper = modelMapper;
    }

    public PageResponse<StationDTO> getAllStations(int page, int size, String sortBy, String sortDir){
        Sort sort=sortBy.trim().toLowerCase().equals("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<Station> stationsPage=stationRepository.findAll(pageable);

        Page<StationDTO> stationDTOPage=stationsPage.map(station-> modelMapper.map(station, StationDTO.class));
        return PageResponse.fromPage(stationDTOPage);
    }
    public Station getStationById(String id){
        return stationRepository.findById(id).orElse(null);
    }
    public Station saveStation(Station station){
        return stationRepository.save(station);
    }
    public void deleteById(String id){
        stationRepository.deleteById(id);
    }
}
