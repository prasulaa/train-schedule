package pl.edu.pw.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DbInit implements CommandLineRunner {

    private final DepartureRepository departureRepository;
    private final TrainRepository trainRepository;
    private final TrainScheduleRepository trainScheduleRepository;
    private final TrainStationRepository trainStationRepository;

    public DbInit(DepartureRepository departureRepository, TrainRepository trainRepository,
                  TrainScheduleRepository trainScheduleRepository, TrainStationRepository trainStationRepository) {
        this.departureRepository = departureRepository;
        this.trainRepository = trainRepository;
        this.trainScheduleRepository = trainScheduleRepository;
        this.trainStationRepository = trainStationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
