package nomads;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CountryCardController implements Initializable {
    @FXML
    private Label areaLabel;

    @FXML
    private CheckBox culturalCheckBox;

    @FXML
    private Button favoriteButton;

    @FXML
    private ImageView flagImageView;

    @FXML
    private CheckBox foodCheckBox;

    @FXML
    private Label nameLabel;

    @FXML
    private CheckBox outdoorsCheckBox;

    @FXML
    private Label populationLabel;

    @FXML
    private Label regionLabel;

    @FXML
    private CheckBox urbanCheckBox;

    @FXML
    private Label visaLabel;

    @FXML
    private Label warningLabel;

    @FXML
    void onFavoriteButtonClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
