package pl.edu.pw.controller;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.dto.DepartureDTO;
import pl.edu.pw.service.DepartureService;

@RestController
public class DepartureController {

    private final DepartureService departureService;

    public DepartureController(DepartureService departureService) {
        this.departureService = departureService;
    }

    @GetMapping("/departure")
    public ResponseEntity<?> getDeparturesByTrainStationName(
        @RequestParam String trainStationName) {
        try {
            List<DepartureDTO> departures = departureService.getDeparturesByTrainStationName(
                trainStationName);
            return ResponseEntity.ok(departures);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
