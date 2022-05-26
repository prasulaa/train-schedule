package pl.edu.pw.controller;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.domain.TrainSchedule;
import pl.edu.pw.service.TrainScheduleService;

@RestController
@Validated
public class TrainScheduleController {

    private final TrainScheduleService trainScheduleService;

    public TrainScheduleController(TrainScheduleService trainScheduleService) {
        this.trainScheduleService = trainScheduleService;
    }

    @PutMapping(path = "/trainschedule/{id}") // TODO TEST
    public ResponseEntity<?> setTrainScheduleDelay(@PathVariable("id") Long id,
                                                   @RequestParam Integer delay) {
        try {
            trainScheduleService.setTrainScheduleDelay(id, delay);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/trainschedule/{id}")
    public ResponseEntity<?> getTrainScheduleDelay(@PathVariable("id") Long id) {
        try {
            List<TrainSchedule> trainScheduleList = trainScheduleService.getTrainScheduleForTrainById(
                id);
            return ResponseEntity.status(HttpStatus.OK).body(trainScheduleList);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
