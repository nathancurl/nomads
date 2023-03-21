package nomads;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static nomads.MainApp.changeScene;

public class FavoritesController {

    @FXML
    private ButtonBar ButtonBar;

    @FXML
    private Label ListFavTitle;

    @FXML
    private Button SearchDestButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button favButton;

    @FXML
    private ListView<String> favlistView;

    @FXML
    private Button quitButton;

    @FXML
    private Button removeDestButton;

    @FXML
    private Button updateUserButton;


    @FXML
    private Label warningLabel;

    public FavoritesController() {
        favlistView = new ListView<String>();

    }
    

    @FXML
    void onFavButtonClicked(ActionEvent event) throws IOException {
        changeScene(favButton, "destination-generator-view.fxml");
        warningLabel.setText("You are already in the favorites view");
    }

    @FXML
    void onQuitButtonClicked(ActionEvent event) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

//    @FXML
//    void onRemoveDestClicked(ActionEvent event) {
//        // check if selected
//        // if selected remove from listview
//        favlistView.getSelectionModel().selectedItemProperty().addListener();
//
//        @Override
//        public void changed(){
//
//        }
//
//
//
//    }

    @FXML
    void onSearchDestClicked(ActionEvent event) throws IOException {
        changeScene(SearchDestButton, "destination-generator-view.fxml");
    }

    @FXML
    void onUpdateUserClicked(ActionEvent event) throws IOException {
        changeScene(updateUserButton, "update-user-view.fxml");
    }

//   public removeFavItem(){
//        favlistView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                favlistView.getse
//            }
//        });
//    }
//            }
//
//
//
//    }



}
