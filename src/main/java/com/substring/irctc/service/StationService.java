package com.substring.irctc.service;

import com.substring.irctc.dto.PageResponse;
import com.substring.irctc.dto.StationDTO;
import org.springframework.http.ResponseEntity;

public interface StationService {
    StationDTO createStation(StationDTO stationDTO);

    PageResponse<StationDTO> listStations(int page, int size, String sortBy, String sortDir);

    StationDTO getById(Long id);

    StationDTO update(Long id, StationDTO stationDto);

    ResponseEntity<Void> delete(Long id);
}
