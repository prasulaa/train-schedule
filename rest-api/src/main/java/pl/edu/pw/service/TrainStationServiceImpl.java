package pl.edu.pw.service;

import java.util.List;
import org.springframework.stereotype.Service;
import pl.edu.pw.domain.TrainStation;
import pl.edu.pw.repository.TrainStationRepository;

@Service
public class TrainStationServiceImpl implements TrainStationService {

    private final TrainStationRepository trainStationRepository;

    public TrainStationServiceImpl(
        TrainStationRepository trainStationRepository) {
        this.trainStationRepository = trainStationRepository;
    }

    @Override
    public List<TrainStation> getTrainStationList() {
        return trainStationRepository.findAll();
    }

}
