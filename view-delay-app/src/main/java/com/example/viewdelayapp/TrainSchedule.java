package com.example.viewdelayapp;

import java.util.List;

public class TrainSchedule {

    private Long id;
    private List<Departure> departures;
    private Train train;

    public TrainSchedule() {
        super();
    }

    public List<Departure> getDepartures() {
        return departures;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDepartures(List<Departure> departures) {
        this.departures = departures;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
