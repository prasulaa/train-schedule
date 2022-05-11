package com.example.viewdelayapp;

import java.time.LocalTime;

public class Departure {

    private Long id;
    private LocalTime departureTime;
    private Integer delay;
    private TrainStation startingTrainStation;
    private TrainStation finalTrainStation;

    public Departure() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public void setStartingTrainStation(TrainStation startingTrainStation) {
        this.startingTrainStation = startingTrainStation;
    }

    public void setFinalTrainStation(TrainStation finalTrainStation) {
        this.finalTrainStation = finalTrainStation;
    }

    @Override
    public String toString() {
        return "Arrival from " + startingTrainStation.toString() + " to "
                   + finalTrainStation.toString() + " at " + departureTime +
                   " | Delay: " + delay + "min";
    }
}
