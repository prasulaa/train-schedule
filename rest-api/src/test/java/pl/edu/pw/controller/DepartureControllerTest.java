package pl.edu.pw.controller;

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
import pl.edu.pw.dto.DepartureDTO;
import pl.edu.pw.dto.TrainStationDTO;
import pl.edu.pw.service.DepartureService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartureControllerTest {

    @Mock
    private DepartureService departureService;
    @InjectMocks
    private DepartureController departureController;
    @Autowired
    private MockMvc mockMvc;

    private final String URI = "/departure";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departureController).build();
    }

    @Test
    public void shouldReturnCorrectJSONWhenRequestIsCorrectAndDepartureIsInDatabase() throws Exception {
        TrainStationDTO startingTrainStation = new TrainStationDTO(1L, "Minsk");
        TrainStationDTO finalTrainStation = new TrainStationDTO(2L, "Ostroleka");
        List<DepartureDTO> departures = List.of(new DepartureDTO(1L, LocalTime.parse("15:00"), 0, startingTrainStation, finalTrainStation));
        String expectedResponse = "[{\"id\":1,\"departureTime\":[15,0],\"delay\":0,\"startingTrainStation\":{\"id\":1,\"name\":\"Minsk\"},\"finalTrainStation\":{\"id\":2,\"name\":\"Ostroleka\"}}]";

        when(departureService.getDeparturesByTrainStationName("Minsk")).thenReturn(departures);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI + "?trainStationName=Minsk")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void shouldReturnStatusNotFoundWhenDepartureIsNotInDatabase() throws Exception {
        when(departureService.getDeparturesByTrainStationName(any())).thenThrow(new EntityNotFoundException());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI + "?trainStationName=WrongTrainStationName")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void shouldReturnStatusBadRequestWhenParamIsNotCorrect() throws Exception {
        when(departureService.getDeparturesByTrainStationName(any())).thenThrow(new IllegalArgumentException());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI + "?trainStationName=")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void shouldReturnStatusInternalServerErrorWhenUnexpectedErrorOccurred() throws Exception {
        when(departureService.getDeparturesByTrainStationName(any())).thenThrow(new UnsupportedOperationException());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI + "?trainStationName=Test")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());
    }


}
