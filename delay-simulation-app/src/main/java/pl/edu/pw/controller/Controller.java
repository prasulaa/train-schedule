package pl.edu.pw.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Controller {

    @FXML
    private Spinner<Integer> trainScheduleIdSpinner;
    @FXML
    private Spinner<Integer> delaySpinner;
    @FXML
    private Button setDelayButton;

    @FXML
    private void initialize() {
        initSpinner(trainScheduleIdSpinner);
        initSpinner(delaySpinner);
    }

    private void initSpinner(Spinner<Integer> spinner) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0, 1));
    }

    @FXML
    private void setDelayButtonClicked() {
        if (setDelay(trainScheduleIdSpinner.getValue(), delaySpinner.getValue())) {
            setDelayButton.setStyle("-fx-border-color: #00ff00");
        } else {
            setDelayButton.setStyle("-fx-border-color: #ff0000");
        }
    }

    private boolean setDelay(Integer trainScheduleId, Integer delay) {
        try {
            URL url = new URL("http://localhost:8080/trainschedule/" + trainScheduleId + "?delay=" + delay);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            int responseCode = connection.getResponseCode();
            return responseCode == 204;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

}
