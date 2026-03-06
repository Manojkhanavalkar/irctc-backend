package com.substring.irctc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Train {
    @Id
    @NotEmpty(message = "train number is required!!")
    @Size(min = 3 , max = 20, message = "Invalid Length of train")
    @Pattern(regexp = "^\\d+$",message = "TrainNo should only contain numbers")
    private String trainNo;
    private String name;
    private int coaches;

    public Train(){}

    public Train(String trainNo, String name, int coaches) {
        this.trainNo = trainNo;
        this.name = name;
        this.coaches = coaches;
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

    public int getCoaches() {
        return coaches;
    }

    public void setCoaches(int coaches) {
        this.coaches = coaches;
    }
}
