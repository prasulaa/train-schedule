package pl.edu.pw.dto;

import java.time.LocalTime;

public class DepartureDTO {

    private Long id;
    private LocalTime departureTime;
    private Integer delay;
    private TrainStationDTO startingTrainStation;
    private TrainStationDTO finalTrainStation;

    public DepartureDTO() {
    }

    public DepartureDTO(Long id, LocalTime departureTime, Integer delay, TrainStationDTO startingTrainStation, TrainStationDTO finalTrainStation) {
        this.id = id;
        this.departureTime = departureTime;
        this.delay = delay;
        this.startingTrainStation = startingTrainStation;
        this.finalTrainStation = finalTrainStation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public TrainStationDTO getStartingTrainStation() {
        return startingTrainStation;
    }

    public void setStartingTrainStation(TrainStationDTO startingTrainStation) {
        this.startingTrainStation = startingTrainStation;
    }

    public TrainStationDTO getFinalTrainStation() {
        return finalTrainStation;
    }

    public void setFinalTrainStation(TrainStationDTO finalTrainStation) {
        this.finalTrainStation = finalTrainStation;
    }
}
