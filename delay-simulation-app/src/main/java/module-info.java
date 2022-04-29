module virtual.camera {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports pl.edu.pw to javafx.graphics;
    opens pl.edu.pw.controller to javafx.fxml;
}