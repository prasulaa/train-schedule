package pl.edu.pw.service;

import org.springframework.stereotype.Service;
import pl.edu.pw.domain.Departure;
import pl.edu.pw.dto.DepartureDTO;
import pl.edu.pw.repository.DepartureRepository;
import pl.edu.pw.service.mapper.DepartureMapper;

import java.util.List;

@Service
public class DepartureServiceImpl implements DepartureService {

    private final DepartureRepository departureRepository;

    public DepartureServiceImpl(DepartureRepository departureRepository) {
        this.departureRepository = departureRepository;
    }

    @Override
    public List<DepartureDTO> getDeparturesByTrainStationName(String trainStationName) {
        if (trainStationName == null || trainStationName.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            List<Departure> departures = departureRepository.findAllByTrainStation_Name(trainStationName);
            return DepartureMapper.map(departures);
        }
    }

}
