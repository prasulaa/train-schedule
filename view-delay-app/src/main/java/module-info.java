module com.example.viewdelayapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.datatype.jsr310;

    requires javafx.graphics;

    opens com.example.viewdelayapp to javafx.fxml;
    exports com.example.viewdelayapp;
}