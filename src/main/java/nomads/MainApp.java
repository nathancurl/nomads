package nomads;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainApp extends Application {

    final static String[] countries = {"Argentina", "Australia", "Bangladesh", "Belgium",
            "Bolivia", "Brazil", "Canada", "China", "Colombia", "Egypt", "France",
            "Greece", "India", "Italy", "Jamaica", "Japan", "Maldives", "Mexico",
            "New Zealand", "Nigeria", "Saudi Arabia", "Senegal", "Spain",
            "South Africa", "Turkey", "United Kingdom", "United States", "Uruguay",
            "Venezuela", "Vietnam"};
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Nomad Travels");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public static void changeScene(Button button, String viewPath) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        // Load register view
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(viewPath));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Nomad Travels");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}