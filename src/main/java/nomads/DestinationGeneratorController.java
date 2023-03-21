package nomads;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static nomads.MainApp.changeScene;

public class DestinationGeneratorController implements Initializable {
    @FXML
    private ButtonBar ButtonBar;
    @FXML
    private Button SearchDestButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button favButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button quitButton;

    @FXML
    private Button updateUserButton;

    @FXML
    private Label warningLabel;

    @FXML
    void onFavButtonClicked(ActionEvent event) throws IOException {
        changeScene(favButton, "favorites-view.fxml");

    }

    @FXML
    void onQuitButtonClicked(ActionEvent event) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onSearchDestClicked(ActionEvent event) throws FileNotFoundException, SQLException {
        warningLabel.setText("You are already in the search destination view");
    }

    @FXML
    void onUpdateUserClicked(ActionEvent event) throws IOException {
        changeScene(updateUserButton, "update-user-view.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        warningLabel.setText("Welcome to your personalized list of recommended travel destinations!");

        DestinationGenerator destinationGenerator = new DestinationGenerator();

        try {
            ArrayList<Country> destinations = destinationGenerator.getDestinations(User.getInstance());
            User.getInstance().setDestinations(destinations);
            String[] destinationStringArr = User.getInstance().getStringArr(destinations);
            listView.getItems().addAll(destinationStringArr);
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    String countryName = listView.getSelectionModel().getSelectedItem();
                    warningLabel.setText("Country selected: " + countryName);
                    User.getInstance().setPresentCountry(countryName);
                    try {
                        changeScene(quitButton, "country-card-view.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


