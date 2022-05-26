package pl.edu.pw.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.edu.pw.domain.Departure;
import pl.edu.pw.domain.Train;
import pl.edu.pw.domain.TrainSchedule;
import pl.edu.pw.domain.TrainStation;
import pl.edu.pw.dto.DepartureDTO;
import pl.edu.pw.repository.DepartureRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartureServiceTest {

    @Mock
    private DepartureRepository departureRepository;
    @InjectMocks
    private DepartureServiceImpl departureService;

    @Test
    public void shouldReturnDeparturesWhenDeparturesArePresentInDatabase() {
        Train train = new Train(1L, "Inka");
        TrainStation trainStation = new TrainStation(1L, "Minsk Mazowiecki");
        List<Departure> expectedDepartures = Arrays.asList(new Departure(1L, trainStation, LocalTime.parse("09:00"), 1, null));
        TrainSchedule trainSchedule = new TrainSchedule(expectedDepartures, train);
        expectedDepartures.get(0).setTrainSchedule(trainSchedule);

        when(departureRepository.findAllByTrainStation_Name(any())).thenReturn(expectedDepartures);

        List<DepartureDTO> actualDepartures = departureService.getDeparturesByTrainStationName("any");

        assertEquals(expectedDepartures.size(), actualDepartures.size());
        checkEquality(expectedDepartures.get(0), actualDepartures.get(0));
    }

    @Test
    public void shouldReturnEmptyListWhenDeparturesAreNotPresentInDatabase() {
        when(departureRepository.findAllByTrainStation_Name(any())).thenReturn(new ArrayList<>());

        List<DepartureDTO> actualDepartures = departureService.getDeparturesByTrainStationName("any");

        assertEquals(0, actualDepartures.size());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTrainStationNameIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                departureService.getDeparturesByTrainStationName(null));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTrainStationNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                departureService.getDeparturesByTrainStationName(""));
    }

    private void checkEquality(Departure expected, DepartureDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDepartureTime(), actual.getDepartureTime());
        assertEquals(expected.getDelay(), actual.getDelay());
    }

}
