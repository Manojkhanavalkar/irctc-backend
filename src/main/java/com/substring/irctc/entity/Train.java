package com.substring.irctc.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Train {
    @Id
    private String trainNo;
    private String name;

    private String routeName;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private TrainImage trainImage;

    public TrainImage getTrainImage() {
        return trainImage;
    }

    public void setTrainImage(TrainImage trainImage) {
        this.trainImage = trainImage;
    }

    public Train(){}

    public Train(String trainNo, String name, String routeName) {
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
