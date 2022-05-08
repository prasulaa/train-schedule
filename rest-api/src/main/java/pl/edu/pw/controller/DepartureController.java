package pl.edu.pw.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartureController {

    @GetMapping("/departure")
    public ResponseEntity<?> getDeparturesByTrainStationName(@RequestParam String trainStationName) {
        //return List<DepartureDTO>
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
