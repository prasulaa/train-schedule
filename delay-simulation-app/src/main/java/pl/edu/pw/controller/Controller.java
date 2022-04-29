import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

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
        
    }

