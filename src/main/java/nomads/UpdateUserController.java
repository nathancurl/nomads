package nomads;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UpdateUserController implements Initializable {

    @FXML
    private Button closeButton;

    @FXML
    private CheckBox culturalCheckBox;

    @FXML
    private Button favoritesButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private CheckBox foodCheckBox;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private ComboBox nationalityComboBox;

    @FXML
    private CheckBox outdoorsCheckBox;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button searchDestinationButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button updateUserInfoButton;

    @FXML
    private CheckBox urbanCheckBox;

    @FXML
    private Label warningLabel;

    @FXML
    protected void getNationalityComboBoxSelected(ActionEvent e){
        User.getInstance().setNationality((String) nationalityComboBox.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    void onCloseButtonClicked(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onOutdoorsCheckBoxClicked(ActionEvent e) {
        User.getInstance().setOutdoors(outdoorsCheckBox.isSelected());
    }
    @FXML
    protected void onUrbanCheckBoxClicked(ActionEvent e) {
        User.getInstance().setUrban(urbanCheckBox.isSelected());
    }
    @FXML
    protected void onCulturalCheckBoxClicked(ActionEvent e) {
        User.getInstance().setCultural(culturalCheckBox.isSelected());
    }
    @FXML
    protected void onFoodCheckBoxClicked(ActionEvent e) {
        User.getInstance().setFood(foodCheckBox.isSelected());
    }

    @FXML
    void onFavoritesButtonClicked(ActionEvent event) {

    }

    @FXML
    void onSearchDestinationButtonClicked(ActionEvent event) {

    }

    @FXML
    void onUpdateButtonClicked(ActionEvent event) throws IOException, SQLException {
        if ((!passwordPasswordField.getText().isBlank()) && (!nationalityComboBox.getSelectionModel().isEmpty())
                || outdoorsCheckBox.isSelected() || urbanCheckBox.isSelected() || culturalCheckBox.isSelected() || foodCheckBox.isSelected()
                && (!firstNameTextField.getText().isBlank()) && (!lastNameTextField.getText().isBlank())) {
            User.getInstance().setFirstName(firstNameTextField.getText());
            User.getInstance().setLastName(lastNameTextField.getText());
            User.getInstance().setPassword(passwordPasswordField.getText());
            System.out.println(User.getInstance());
            updateUser();
            warningLabel.setText("User profile sucessfully updated");

        } else {
            warningLabel.setText("Please fill out all the required fields!");
        }
    }

    private void updateUser() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String updateQuery = "UPDATE USER " +
                "SET firstname = '" + User.getInstance().getFirstName() +
                "', lastname = '" + User.getInstance().getLastName() +
                "', password = '" + User.getInstance().getPassword() +
                "', outdoors = '" + User.getInstance().isOutdoors() +
                "', urban = '" + User.getInstance().isUrban() +
                "', cultural = '" + User.getInstance().isCultural() +
                "', food = '" + User.getInstance().isFood() + "' WHERE username = '" + User.getInstance().getUsername() + "'";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuery);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        connection.close();
    }

    @FXML
    void onUpdateUserInfoButtonClicked(ActionEvent event) {
        warningLabel.setText("You are already in the update user window");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nationalityComboBox.getItems().removeAll(nationalityComboBox.getItems());
        nationalityComboBox.getItems().addAll("United States", "Bangladesh", "Australia",
                "Japan", "Brazil", "Spain", "Mexico", "Thailand", "Sweden", "Morocco", "China");
        nationalityComboBox.setValue(User.getInstance().getNationality());
        passwordPasswordField.setText(User.getInstance().getPassword());
        firstNameTextField.setText(User.getInstance().getFirstName());
        lastNameTextField.setText(User.getInstance().getLastName());
        outdoorsCheckBox.setSelected(User.getInstance().outdoors);
        culturalCheckBox.setSelected(User.getInstance().cultural);
        foodCheckBox.setSelected(User.getInstance().food);
        urbanCheckBox.setSelected(User.getInstance().urban);

    }

}
