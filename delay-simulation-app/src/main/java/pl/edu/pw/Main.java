package pl.edu.pw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) throws FileNotFoundException {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane mainPane = FXMLLoader.load(getClass().getResource("/mainPane.fxml"));
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setTitle("Virtual Camera");
        stage.show();
    }

}
