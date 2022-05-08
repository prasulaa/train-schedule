package pl.edu.pw.service;

import pl.edu.pw.dto.DepartureDTO;

import java.util.List;

public interface DepartureService {

    List<DepartureDTO> getDeparturesByTrainStationName(String trainStationName);

}
