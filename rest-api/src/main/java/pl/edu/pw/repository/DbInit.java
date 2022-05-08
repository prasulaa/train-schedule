package pl.edu.pw.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import pl.edu.pw.domain.Departure;
import pl.edu.pw.domain.Train;
import pl.edu.pw.domain.TrainSchedule;
import pl.edu.pw.domain.TrainStation;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

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
        Train train1 = new Train("Inka");
        Train train2 = new Train("Awangarda");
        trainRepository.save(train1);
        trainRepository.save(train2);

        TrainStation trainStation1 = new TrainStation("Minsk Mazowiecki");
        TrainStation trainStation2 = new TrainStation("Warszawa Centralna");
        TrainStation trainStation3 = new TrainStation("Ostroleka");
        TrainStation trainStation4 = new TrainStation("Wielun");
        TrainStation trainStation5 = new TrainStation("Starogard Gdanski");
        TrainStation trainStation6 = new TrainStation("Nidzica");
        TrainStation trainStation7 = new TrainStation("Pulawy");
        TrainStation trainStation8 = new TrainStation("Suwalki");
        trainStationRepository.save(trainStation1);
        trainStationRepository.save(trainStation2);
        trainStationRepository.save(trainStation3);
        trainStationRepository.save(trainStation4);
        trainStationRepository.save(trainStation5);
        trainStationRepository.save(trainStation6);
        trainStationRepository.save(trainStation7);
        trainStationRepository.save(trainStation8);

        List<Departure> departures1 = Arrays.asList(
                new Departure(trainStation7, LocalTime.parse("09:00")),
                new Departure(trainStation1, LocalTime.parse("10:30")),
                new Departure(trainStation2, LocalTime.parse("11:15")),
                new Departure(trainStation3, LocalTime.parse("12:30")),
                new Departure(trainStation8, LocalTime.parse("14:00")));

        List<Departure> departures2 = Arrays.asList(
                new Departure(trainStation8, LocalTime.parse("14:30")),
                new Departure(trainStation3, LocalTime.parse("16:00")),
                new Departure(trainStation2, LocalTime.parse("17:15")),
                new Departure(trainStation1, LocalTime.parse("18:00")),
                new Departure(trainStation7, LocalTime.parse("19:30")));

        List<Departure> departures3 = Arrays.asList(
                new Departure(trainStation4, LocalTime.parse("12:30")),
                new Departure(trainStation2, LocalTime.parse("14:15")),
                new Departure(trainStation6, LocalTime.parse("16:30")),
                new Departure(trainStation5, LocalTime.parse("17:45")));

        List<Departure> departures4 = Arrays.asList(
                new Departure(trainStation5, LocalTime.parse("18:15")),
                new Departure(trainStation6, LocalTime.parse("19:30")),
                new Departure(trainStation2, LocalTime.parse("21:45")),
                new Departure(trainStation4, LocalTime.parse("23:30")));

        TrainSchedule trainSchedule1 = new TrainSchedule(departures1, train1);
        TrainSchedule trainSchedule2 = new TrainSchedule(departures2, train1);
        TrainSchedule trainSchedule3 = new TrainSchedule(departures3, train2);
        TrainSchedule trainSchedule4 = new TrainSchedule(departures4, train2);

        addTrainScheduleToDepartures(departures1, trainSchedule1);
        addTrainScheduleToDepartures(departures2, trainSchedule2);
        addTrainScheduleToDepartures(departures3, trainSchedule3);
        addTrainScheduleToDepartures(departures4, trainSchedule4);

        trainScheduleRepository.save(trainSchedule1);
        trainScheduleRepository.save(trainSchedule2);
        trainScheduleRepository.save(trainSchedule3);
        trainScheduleRepository.save(trainSchedule4);
    }

    private void addTrainScheduleToDepartures(List<Departure> departures, TrainSchedule trainSchedule) {
        for (Departure d: departures) {
            d.setTrainSchedule(trainSchedule);
        }
    }
}
