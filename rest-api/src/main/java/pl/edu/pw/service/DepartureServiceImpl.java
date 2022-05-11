package pl.edu.pw.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.stereotype.Service;
import pl.edu.pw.domain.Departure;
import pl.edu.pw.dto.DepartureDTO;
import pl.edu.pw.repository.DepartureRepository;
import pl.edu.pw.service.mapper.DepartureMapper;

@Service
public class DepartureServiceImpl implements DepartureService {

    private final DepartureRepository departureRepository;

    public DepartureServiceImpl(DepartureRepository departureRepository) {
        this.departureRepository = departureRepository;
    }

    @Override
    public List<DepartureDTO> getDeparturesByTrainStationName(String trainStationName) {
        try {
            trainStationName = decode(trainStationName);
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }

        if (trainStationName == null || trainStationName.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            List<Departure> departures = departureRepository.findAllByTrainStation_Name(
                trainStationName);
            return DepartureMapper.map(departures);
        }
    }

    private String decode(String value) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
    }

}
