package pl.edu.pw.domain;

import javax.persistence.*;
import java.time.LocalTime;

@Table
@Entity
public class Departure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private TrainStation trainStation;
    private LocalTime departureTime;
    private Integer delay;

    public Departure() {
    }

    public Departure(TrainStation trainStation, LocalTime departureTime) {
        this.trainStation = trainStation;
        this.departureTime = departureTime;
        this.delay = 0;
    }

    public Departure(Long id, TrainStation trainStation, LocalTime departureTime, Integer delay) {
        this.id = id;
        this.trainStation = trainStation;
        this.departureTime = departureTime;
        this.delay = delay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainStation getTrainStation() {
        return trainStation;
    }

    public void setTrainStation(TrainStation trainStation) {
        this.trainStation = trainStation;
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
}
