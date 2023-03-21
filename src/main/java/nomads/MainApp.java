package nomads;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    final static String[] countries = {"Argentina", "Australia", "Bangladesh", "Belgium",
            "Brazil", "Chad", "Chile", "Colombia", "Egypt", "Finland", "France", "Greece",
            "Iran", "Jamaica", "Japan", "Maldives", "Mongolia", "New Zealand", "Niger",
            "Saudi Arabia", "Senegal", "South Africa", "Spain", "Sri Lanka", "Turkey",
            "United Kingdom", "United States", "Uruguay", "Venezuela", "Vietnam"};
    

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

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Nomad Travels");
        stage.setScene(scene);
        stage.show();
    }
}