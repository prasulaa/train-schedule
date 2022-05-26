package pl.edu.pw.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.pw.domain.Departure;
import pl.edu.pw.domain.TrainStation;
import pl.edu.pw.repository.DepartureRepository;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartureControllerTest {

    @Mock
    private DepartureRepository departureRepository;
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
    public void test() throws Exception {
        List<Departure> departures = List.of(new Departure(1L, new TrainStation(1L, "Minsk"), LocalTime.NOON, 5, null));
        when(departureRepository.findAllByTrainStation_Name(any())).thenReturn(departures);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI + "?trainStationName=Maciek").contentType(MediaType.APPLICATION_JSON)).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }


}
