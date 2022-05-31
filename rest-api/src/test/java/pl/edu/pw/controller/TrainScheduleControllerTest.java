package pl.edu.pw.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.pw.domain.Departure;
import pl.edu.pw.domain.Train;
import pl.edu.pw.domain.TrainSchedule;
import pl.edu.pw.domain.TrainStation;
import pl.edu.pw.service.TrainScheduleService;

@ExtendWith(MockitoExtension.class)
public class TrainScheduleControllerTest {

    @Mock
    private TrainScheduleService trainScheduleService;
    @InjectMocks
    private TrainScheduleController trainScheduleController;
    @Autowired
    private MockMvc mockMvc;

    private final String URI = "/trainschedule";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(trainScheduleController).build();
    }

    @Test
    public void shouldCallSetMethodAndReturnNoContentStatusWhenRequestIsCorrect() throws Exception {
        long id = 1;
        int delay = 5;

        MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.put(URI + "/" + id + "?delay=" + delay)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
        verify(trainScheduleService, times(1)).setTrainScheduleDelay(id, delay);
    }

    @Test
    public void shouldReturnNotFoundStatusWhenTrainScheduleIsNotPresentInDatabase()
        throws Exception {
        long id = 999;
        int delay = 5;
        doThrow(new EntityNotFoundException()).when(trainScheduleService)
            .setTrainScheduleDelay(id, delay);

        MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.put(URI + "/" + id + "?delay=" + delay)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void shouldReturnBadRequestStatusWhenRequestIsNotCorrect() throws Exception {
        long id = -999;
        int delay = 5;
        doThrow(new IllegalArgumentException()).when(trainScheduleService)
            .setTrainScheduleDelay(id, delay);

        MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.put(URI + "/" + id + "?delay=" + delay)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void shouldReturnInternalServerErrorStatusWhenUnexpectedErrorOccurred()
        throws Exception {
        long id = -999;
        int delay = 5;
        doThrow(new UnsupportedOperationException()).when(trainScheduleService)
            .setTrainScheduleDelay(id, delay);

        MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.put(URI + "/" + id + "?delay=" + delay)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void shouldCallGetMethodAndReturnOKCodeWithValidTrainScheduleList() throws Exception {
        //given
        long id = 5;
        trainScheduleServiceReturnsValidTrainScheduleList();

        //when
        MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get(URI + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertHttpStatusOKAndResponseEqualToGivenList(mvcResult);
        verify(trainScheduleService, times(1)).getTrainScheduleForTrainById(id);
    }

    @Test
    public void shouldCallGetMethodAndReturnOKCodeWithEmptyTrainScheduleList() throws Exception {
        //given
        long id = 5;
        trainScheduleServiceReturnsEmptyTrainScheduleList();

        //when
        MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get(URI + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertHttpStatusOKAndEmptyResponse(mvcResult);
        verify(trainScheduleService, times(1)).getTrainScheduleForTrainById(id);
    }

    @Test
    public void shouldCallGetMethodAndReturnNotFoundCode() throws Exception {
        //given
        long id = 5;
        trainScheduleServiceThrowsEntityNotFoundException();

        //when
        MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get(URI + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertHttpStatusNotFound(mvcResult);
        verify(trainScheduleService, times(1)).getTrainScheduleForTrainById(id);
    }

    private void assertHttpStatusOKAndResponseEqualToGivenList(MvcResult mvcResult)
        throws UnsupportedEncodingException {
        String expectedTrainSchedules = "[{\"id\":null,\"departures\":[{\"id\":null,\"trainStation\":{\"id\":null,\"name\":\"Minsk Mazowiecki\"},\"departureTime\":[10,30],\"delay\":0,\"trainSchedule\":null},{\"id\":null,\"trainStation\":{\"id\":null,\"name\":\"Warszawa Centralna\"},\"departureTime\":[11,15],\"delay\":0,\"trainSchedule\":null},{\"id\":null,\"trainStation\":{\"id\":null,\"name\":\"Ostroleka\"},\"departureTime\":[12,30],\"delay\":0,\"trainSchedule\":null}],\"train\":{\"id\":null,\"name\":\"Inka\"}}]";
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(expectedTrainSchedules, mvcResult.getResponse().getContentAsString());
    }

    private void assertHttpStatusOKAndEmptyResponse(MvcResult mvcResult)
        throws UnsupportedEncodingException {
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals("[]", mvcResult.getResponse().getContentAsString());
    }

    private void assertHttpStatusNotFound(MvcResult mvcResult) {
        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    private void trainScheduleServiceReturnsValidTrainScheduleList() {
        Train train1 = new Train("Inka");

        TrainStation trainStation1 = new TrainStation("Minsk Mazowiecki");
        TrainStation trainStation2 = new TrainStation("Warszawa Centralna");
        TrainStation trainStation3 = new TrainStation("Ostroleka");

        List<Departure> departures1 = Arrays.asList(
            new Departure(trainStation1, LocalTime.parse("10:30")),
            new Departure(trainStation2, LocalTime.parse("11:15")),
            new Departure(trainStation3, LocalTime.parse("12:30")));

        TrainSchedule trainSchedule1 = new TrainSchedule(departures1, train1);

        when(trainScheduleService.getTrainScheduleForTrainById(any())).thenReturn(
            List.of(trainSchedule1));

    }

    private void trainScheduleServiceReturnsEmptyTrainScheduleList() {
        when(trainScheduleService.getTrainScheduleForTrainById(any())).thenReturn(
            new ArrayList<>());
    }

    private void trainScheduleServiceThrowsEntityNotFoundException() {
        when(trainScheduleService.getTrainScheduleForTrainById(any())).thenThrow(
            EntityNotFoundException.class);
    }


}
