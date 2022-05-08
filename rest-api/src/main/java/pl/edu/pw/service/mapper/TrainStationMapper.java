package pl.edu.pw.service.mapper;

import pl.edu.pw.domain.TrainStation;
import pl.edu.pw.dto.TrainStationDTO;

public class TrainStationMapper {

    public static TrainStationDTO map(TrainStation trainStation) {
        if (trainStation == null) {
            throw new IllegalArgumentException();
        } else {
            return new TrainStationDTO(
                    trainStation.getId(),
                    trainStation.getName()
            );
        }
    }

}
