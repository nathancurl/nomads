package nomads;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static nomads.MainApp.changeScene;

public class CountryCardController implements Initializable {
    Country country = User.getInstance().presentCountry;
    @FXML
    private Label areaLabel;
    @FXML
    private CheckBox culturalCheckBox;
    @FXML
    private Button favoriteButton;
    @FXML
    private Button backButton;
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
        if (User.getInstance().contains(country)) {
            User.getInstance().removeFromFavorites(country);
            favoriteButton.setText("Favorite");
        } else {
            User.getInstance().addToFavorites(country);
            favoriteButton.setText("Unfavorite");
        }
    }

    @FXML
    void onBackButtonClicked(ActionEvent event) throws IOException {
        changeScene(backButton, "destination-generator-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Update favoriteButton Text
        if (User.getInstance().contains(country)) {
            favoriteButton.setText("Unfavorite");
        } else {
            favoriteButton.setText("Favorite");
        }

        // Update labels
        nameLabel.setText(country.getName());
        visaLabel.setText(country.getVisa());
        areaLabel.setText(String.valueOf(country.getArea()));
        populationLabel.setText(String.valueOf(country.getPopulation()));
        regionLabel.setText(country.getRegion());

        // Update checkboxes
        outdoorsCheckBox.setSelected(country.isOutdoors());
        outdoorsCheckBox.setDisable(true);
        urbanCheckBox.setSelected(country.isUrban());
        urbanCheckBox.setDisable(true);
        culturalCheckBox.setSelected(country.isCultural());
        culturalCheckBox.setDisable(true);
        foodCheckBox.setSelected(country.isFood());
        foodCheckBox.setDisable(true);

        // Update image
        String flagImageURL = "images/flags/";
        String countryName = country.getName().replaceAll(" ", "");// make the space go awa

        InputStream stream = null;
        try {
            stream = new FileInputStream(flagImageURL + countryName + ".png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);
        flagImageView.setImage(image);


    }
}
