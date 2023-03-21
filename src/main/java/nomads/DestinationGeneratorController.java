package nomads;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static nomads.MainApp.changeScene;
import static nomads.MainApp.countries;

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
    public void initialize(URL url, ResourceBundle resourceBundle){

        DestinationGenerator destinationGenerator = new DestinationGenerator();

        try {
            ArrayList<Country> destinations = destinationGenerator.getDestinations(User.getInstance());
            User.getInstance().setDestinations(destinations);
            String[] destinationStringArr = destinationGenerator.getStringArr(destinations);
            listView.getItems().addAll(destinationStringArr);
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    warningLabel.setText("Country selected: " +listView.getSelectionModel().getSelectedItem());
                }
            });

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


