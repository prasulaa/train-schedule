package pl.edu.pw.service;

import java.util.List;
import org.springframework.stereotype.Service;
import pl.edu.pw.domain.Train;
import pl.edu.pw.repository.TrainRepository;

@Service
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;

    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public List<Train> getTrainList() {
        return trainRepository.findAll();
    }
}
