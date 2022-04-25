package pl.edu.pw.domain;

import javax.persistence.*;

@Entity
@Table
public class TrainStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public TrainStation() {
    }

    public TrainStation(String name) {
        this.name = name;
    }

    public TrainStation(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
