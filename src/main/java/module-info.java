module nomads {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    exports nomads;
    opens nomads to javafx.fxml;
}