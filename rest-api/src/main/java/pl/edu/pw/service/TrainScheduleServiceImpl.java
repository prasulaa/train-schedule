package pl.edu.pw.service;

import org.springframework.stereotype.Service;
import pl.edu.pw.domain.Departure;
import pl.edu.pw.domain.TrainSchedule;
import pl.edu.pw.repository.TrainScheduleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TrainScheduleServiceImpl implements TrainScheduleService {

    private final TrainScheduleRepository trainScheduleRepository;

    public TrainScheduleServiceImpl(TrainScheduleRepository trainScheduleRepository) {
        this.trainScheduleRepository = trainScheduleRepository;
    }

    @Override
    public void setTrainScheduleDelay(Long id, Integer delay) {
        if (delay < 0) {
            throw new IllegalArgumentException();
        } else {
            TrainSchedule trainSchedule = trainScheduleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            setDelayInEveryDeparture(trainSchedule.getDepartures(), delay);
            trainScheduleRepository.save(trainSchedule);
        }
    }

    private void setDelayInEveryDeparture(List<Departure> departures, Integer delay) {
        if (departures == null || departures.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            for (Departure d: departures) {
                d.setDelay(delay);
            }
        }
    }
}
