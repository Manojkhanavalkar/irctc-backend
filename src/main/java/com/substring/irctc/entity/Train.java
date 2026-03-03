package com.substring.irctc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Train {
    @Id
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
