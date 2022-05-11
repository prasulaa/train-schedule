package pl.edu.pw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.service.TrainStationService;

@RestController
@Validated
public class TrainStationController {

    private final TrainStationService trainStationService;

    public TrainStationController(TrainStationService trainStationService) {
        this.trainStationService = trainStationService;
    }

    @GetMapping(path = "/trainstations")
    public ResponseEntity<?> getTrainStations() {
        return ResponseEntity.status(HttpStatus.OK).body(trainStationService.getTrainStationList());
    }
}
