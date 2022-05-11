package pl.edu.pw.service;

import java.util.List;
import pl.edu.pw.domain.TrainSchedule;

public interface TrainScheduleService {

    void setTrainScheduleDelay(Long id, Integer delay);

    List<TrainSchedule> getTrainScheduleForTrainById(Long id);

}
