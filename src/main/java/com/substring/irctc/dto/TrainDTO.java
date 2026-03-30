package com.substring.irctc.dto;

import com.substring.irctc.entity.Station;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainDTO {

    private Long id;
    @NotEmpty(message = "train number is required!!")
    @Size(min = 3 , max = 20, message = "Invalid Length of train")
    @Pattern(regexp = "^\\d+$",message = "TrainNo should only contain numbers")
    private String number;
    private String name;

    private Integer totalDistance;
    private StationDTO sourceStation;
    private Long sourceStationId;

    private StationDTO destinationStation;
    private Long destinationStationId;




}
