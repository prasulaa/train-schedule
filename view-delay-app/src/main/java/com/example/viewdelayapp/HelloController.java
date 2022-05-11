package com.example.viewdelayapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class HelloController {

    @FXML
    private ChoiceBox<TrainStation> trainStationsChoice;

    @FXML
    private ListView<Departure> delayList;

    @FXML
    private void initialize() {
        initChoiceCheckbox(getTrainStationList());
    }

    private void initChoiceCheckbox(List<TrainStation> trainStationList) {
        ObservableList<TrainStation> trainObservableList = FXCollections.observableArrayList(
            trainStationList);

        trainStationsChoice.setItems(trainObservableList);
    }

    @FXML
    protected void onCheckDelayClick() {
        List<Departure> departureList = getDeparturesByTrainStationName(
            trainStationsChoice.getValue().toString());
        ObservableList<Departure> departureObservableList = FXCollections.observableArrayList(
            departureList);

        delayList.setItems(departureObservableList);
    }

    private List<TrainStation> getTrainStationList() {
        BufferedReader br;
        List<TrainStation> trainStationList = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/trainstations");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            trainStationList.addAll(parseBufferedReaderToTrainStationList(br));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return trainStationList;
    }

    private List<TrainStation> parseBufferedReaderToTrainStationList(BufferedReader br)
        throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        return objectMapper.readValue(sb.toString(), new TypeReference<>() {
        });
    }

    private List<Departure> parseBufferedReaderToDeparturesList(BufferedReader br)
        throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        return objectMapper.readValue(sb.toString(), new TypeReference<>() {
        });
    }

    private List<Departure> getDeparturesByTrainStationName(String name) {
        BufferedReader br;
        List<Departure> departureList = new ArrayList<>();
        try {
            URL url = new URL(
                "http://localhost:8080/departure?trainStationName=" + encodeValue(name));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            departureList = parseBufferedReaderToDeparturesList(br);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return departureList;
    }

    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }
}