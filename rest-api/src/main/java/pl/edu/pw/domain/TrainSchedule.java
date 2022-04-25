package pl.edu.pw.domain;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class TrainSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<Departure> departures;
    private Train train;

    public TrainSchedule(Long id, List<Departure> departures, Train train) {
        this.id = id;
        this.departures = departures;
        this.train = train;
    }

    public TrainSchedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Departure> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Departure> departures) {
        this.departures = departures;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
