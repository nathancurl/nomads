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

    Country country = User.getInstance().presentCountry;

    @FXML
    void onFavoriteButtonClicked(ActionEvent event) {
        if(country.isFavorite){
            User.getInstance().removeFromFavorites(country);
        }else{
            User.getInstance().addToFavorites(country);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Update favoriteButton Text
        if(country.isFavorite){
            favoriteButton.setText("Unfavorite");
        }else{
            favoriteButton.setText("Favorite");
        }

        // Update labels
        nameLabel.setText(country.getName());
        visaLabel.setText(country.getVisa());
        areaLabel.setText(String.valueOf(country.getArea()));
        populationLabel.setText(String.valueOf(country.getPopulation()));

        // Update checkboxes
        outdoorsCheckBox.setSelected(country.isOutdoors());
        urbanCheckBox.setSelected(country.isUrban());
        culturalCheckBox.setSelected(country.isCultural());
        foodCheckBox.setSelected(country.isFood());
    }
}
