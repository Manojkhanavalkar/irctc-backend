package com.substring.irctc.controllers.admin;

import com.substring.irctc.config.AppConstants;
import com.substring.irctc.dto.PageResponse;
import com.substring.irctc.dto.StationDTO;
import com.substring.irctc.entity.Station;
import com.substring.irctc.service.StationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/stations")
@AllArgsConstructor
public class StationController {


    private StationService stationService;


    @PostMapping
    public ResponseEntity<StationDTO> createStation(@RequestBody StationDTO stationDTO){
        StationDTO dto=stationService.createStation(stationDTO);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    //api to get all stations
    @GetMapping
    public PageResponse<StationDTO> listStations(
            @RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "sortBy",defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc") String sortDir
    ){
        PageResponse<StationDTO> stationDto=stationService.listStations(
                page,
                size,
                sortBy,
                sortDir
        );
        return stationDto;
    }
    @GetMapping("/{id}")
    public StationDTO getById(
            @PathVariable Long id
    ){
        StationDTO dto=stationService.getById(id);
        return dto;
    }


    //update the station
    @PutMapping("/{id}")
    public StationDTO update(
            @PathVariable Long id,
            @RequestBody StationDTO stationDto
    ){
        StationDTO dto =stationService.update(id,stationDto);
        return dto;
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ){
        stationService.delete(id);
    }


}
