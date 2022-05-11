package pl.edu.pw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.service.TrainService;

@RestController
@Validated
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping(path = "/trains")
    public ResponseEntity<?> getTrains() {
        return ResponseEntity.status(HttpStatus.OK).body(trainService.getTrainList());
    }
}
