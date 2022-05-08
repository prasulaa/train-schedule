package pl.edu.pw.service.mapper;

import pl.edu.pw.domain.Departure;
import pl.edu.pw.dto.DepartureDTO;

import java.util.ArrayList;
import java.util.List;

public class DepartureMapper {

    public static List<DepartureDTO> map(List<Departure> departures) {
        if (departures == null) {
            throw new IllegalArgumentException();
        } else {
            List<DepartureDTO> mappedDepartures = new ArrayList<>();
            for (Departure d : departures) {
                mappedDepartures.add(map(d));
            }
            return mappedDepartures;
        }
    }

    public static DepartureDTO map(Departure departure) {
        List<Departure> departuresSchedule = departure.getTrainSchedule().getDepartures();
        int departuresScheduleSize = departuresSchedule.size();

        return new DepartureDTO(
                departure.getId(),
                departure.getDepartureTime(),
                departure.getDelay(),
                TrainStationMapper.map(departuresSchedule.get(0).getTrainStation()),
                TrainStationMapper.map(departuresSchedule.get(departuresScheduleSize - 1).getTrainStation())
        );
    }

}
