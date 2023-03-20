module nomads {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports nomads;
    opens nomads to javafx.fxml;
}