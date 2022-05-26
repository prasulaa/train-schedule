package pl.edu.pw.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pw.domain.Departure;
import pl.edu.pw.domain.Train;
import pl.edu.pw.domain.TrainSchedule;
import pl.edu.pw.domain.TrainStation;
import pl.edu.pw.repository.TrainScheduleRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainScheduleServiceTest {

    @Mock
    private TrainScheduleRepository trainScheduleRepository;
    @InjectMocks
    private TrainScheduleServiceImpl trainScheduleService;

    @Test
    public void shouldUpdateTrainScheduleWhenMethodIsCalledProperly() {
        int delay = 5;
        TrainStation trainStation = new TrainStation("Ostroleka");
        Train train = new Train("Inka");
        List<Departure> departures = List.of(
                new Departure(trainStation, LocalTime.parse("09:00")),
                new Departure(trainStation, LocalTime.parse("11:00")));
        TrainSchedule trainSchedule = new TrainSchedule(departures, train);

        when(trainScheduleRepository.findById(any())).thenReturn(Optional.of(trainSchedule));

        trainScheduleService.setTrainScheduleDelay(1L, delay);

        verify(trainScheduleRepository).save(trainSchedule);
        assertEquals(departures.get(0).getDelay(), delay);
        assertEquals(departures.get(1).getDelay(), delay);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenDelayIsNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                trainScheduleService.setTrainScheduleDelay(1L, -5));
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenTrainScheduleIsNotPresentInDatabase() {
        when(trainScheduleRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                trainScheduleService.setTrainScheduleDelay(1L, 5));
    }

}
