package com.substring.irctc.service.impl;

import com.substring.irctc.dto.PageResponse;
import com.substring.irctc.dto.StationDTO;
import com.substring.irctc.entity.Station;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repository.StationRepository;
import com.substring.irctc.service.StationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class StationServiceImpl implements StationService {


    private StationRepository stationRepo;


    private ModelMapper modelMapper;

    @Override
    public StationDTO createStation(StationDTO stationDTO) {
        Station station = modelMapper.map(stationDTO, Station.class);
        Station savedStation=stationRepo.save(station);
        return modelMapper.map(savedStation,StationDTO.class);
    }

    @Override
    public PageResponse<StationDTO> listStations(int page, int size, String sortBy, String sortDir) {
        Sort sort=sortDir.trim().toLowerCase().equals("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<Station> stations = stationRepo.findAll(pageable);
        Page<StationDTO> stationDTOs = stations.map(station -> modelMapper.map(station, StationDTO.class));
        return PageResponse.fromPage(stationDTOs);
    }

    @Override
    public StationDTO getById(Long id) {
       Station station= stationRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("station not found with given name"));
       return modelMapper.map(station,StationDTO.class);
    }

    @Override
    public StationDTO update(Long id, StationDTO stationDto) {
        Station station= stationRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("station not found with given name"));

        //update details
        station.setName(stationDto.getName());
        station.setCity(stationDto.getCity());
        station.setCode(stationDto.getCode());
        station.setState(stationDto.getState());

        Station updatedStation=stationRepo.save(station);
        return modelMapper.map(updatedStation,StationDTO.class);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        Station station= stationRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("station not found with given name"));
        stationRepo.delete(station);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
