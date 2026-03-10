package com.substring.irctc.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TrainDTO {
    @NotEmpty(message = "train number is required!!")
    @Size(min = 3 , max = 20, message = "Invalid Length of train")
    @Pattern(regexp = "^\\d+$",message = "TrainNo should only contain numbers")
    private String trainNo;
    private String name;

    private String routeName;
    public TrainDTO(){}

    public TrainDTO(String trainNo, String name, String routeName) {
        this.trainNo = trainNo;
        this.name = name;
        this.routeName = routeName;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
