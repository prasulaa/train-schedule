package pl.edu.pw.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.List;
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
import pl.edu.pw.domain.TrainStation;
import pl.edu.pw.service.TrainStationService;

@ExtendWith(MockitoExtension.class)
public class TrainStationControllerTest {

    @Mock
    private TrainStationService trainStationService;
    @InjectMocks
    private TrainStationController trainStationController;
    @Autowired
    private MockMvc mockMvc;

    private final String URI = "/trainstations";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(trainStationController).build();
    }

    @Test
    public void shouldCallGetMethodAndReturnOKCodeWithValidTrainStationList() throws Exception {
        //given
        trainStationServiceReturnsValidTrainStationList();

        //when
        MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get(URI)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertHttpStatusOKAndResponseEqualToGivenList(mvcResult);
        verify(trainStationService, times(1)).getTrainStationList();
    }

    @Test
    public void shouldCallGetMethodAndReturnOKCodeWithEmptyTrainStationList() throws Exception {
        //given
        trainStationServiceReturnsEmptyTrainStationList();

        //when
        MvcResult mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get(URI)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertHttpStatusOKAndEmptyResponse(mvcResult);
        verify(trainStationService, times(1)).getTrainStationList();
    }

    private void assertHttpStatusOKAndResponseEqualToGivenList(MvcResult mvcResult)
        throws UnsupportedEncodingException {
        String expectedTrainSchedules = "[{\"id\":null,\"name\":\"Minsk Mazowiecki\"},{\"id\":null,\"name\":\"Warszawa Centralna\"},{\"id\":null,\"name\":\"Ostroleka\"}]";
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(expectedTrainSchedules, mvcResult.getResponse().getContentAsString());
    }

    private void assertHttpStatusOKAndEmptyResponse(MvcResult mvcResult)
        throws UnsupportedEncodingException {
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals("[]", mvcResult.getResponse().getContentAsString());
    }

    private void trainStationServiceReturnsValidTrainStationList() {
        TrainStation trainStation1 = new TrainStation("Minsk Mazowiecki");
        TrainStation trainStation2 = new TrainStation("Warszawa Centralna");
        TrainStation trainStation3 = new TrainStation("Ostroleka");

        when(trainStationService.getTrainStationList()).thenReturn(
            List.of(trainStation1, trainStation2, trainStation3));
    }

    private void trainStationServiceReturnsEmptyTrainStationList() {
        when(trainStationService.getTrainStationList()).thenReturn(List.of());
    }

}
