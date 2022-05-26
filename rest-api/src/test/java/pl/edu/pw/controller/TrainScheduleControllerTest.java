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
import pl.edu.pw.service.TrainScheduleService;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(URI + "/" + id + "?delay=" + delay)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
        verify(trainScheduleService, times(1)).setTrainScheduleDelay(id, delay);
    }

    @Test
    public void shouldReturnNotFoundStatusWhenTrainScheduleIsNotPresentInDatabase() throws Exception {
        long id = 999;
        int delay = 5;
        doThrow(new EntityNotFoundException()).when(trainScheduleService).setTrainScheduleDelay(id, delay);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(URI + "/" + id + "?delay=" + delay)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void shouldReturnBadRequestStatusWhenRequestIsNotCorrect() throws Exception {
        long id = -999;
        int delay = 5;
        doThrow(new IllegalArgumentException()).when(trainScheduleService).setTrainScheduleDelay(id, delay);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(URI + "/" + id + "?delay=" + delay)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void shouldReturnInternalServerErrorStatusWhenUnexpectedErrorOccurred() throws Exception {
        long id = -999;
        int delay = 5;
        doThrow(new UnsupportedOperationException()).when(trainScheduleService).setTrainScheduleDelay(id, delay);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(URI + "/" + id + "?delay=" + delay)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());
    }

}
