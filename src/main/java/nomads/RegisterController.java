package nomads;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox nationalityComboBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private CheckBox outdoorsCheckBox;
    @FXML
    private CheckBox urbanCheckBox;
    @FXML
    private CheckBox culturalCheckBox;
    @FXML
    private CheckBox foodCheckBox;

    @FXML
    protected void onRegisterButtonClicked(ActionEvent e) throws SQLException {
        if ((!usernameTextField.getText().isBlank()) && (!passwordPasswordField.getText().isBlank()) && (!nationalityComboBox.getSelectionModel().isEmpty())
                || outdoorsCheckBox.isSelected() || urbanCheckBox.isSelected() || culturalCheckBox.isSelected() || foodCheckBox.isSelected()
                && (!firstNameTextField.getText().isBlank()) && (!lastNameTextField.getText().isBlank())) {
            User.getInstance().setUsername(usernameTextField.getText());
            User.getInstance().setFirstName(firstNameTextField.getText());
            User.getInstance().setLastName(lastNameTextField.getText());
            User.getInstance().setPassword(passwordPasswordField.getText());
            warningLabel.setText("All fields are filled");
            System.out.println(User.getInstance());
            registerUser();
        } else {
            warningLabel.setText("Please fill out all the required fields!");
        }

    }

    @FXML
    protected void getNationalityComboBoxSelected(ActionEvent e){
        User.getInstance().setNationality((String) nationalityComboBox.getSelectionModel().getSelectedItem().toString());
    }
    @FXML
    protected void onLoginButtonClicked(ActionEvent e) throws IOException {
        // Close existing stage
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();

        // Load register view
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Nomad Travels");
        stage.setScene(scene);
        stage.show();
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nationalityComboBox.getItems().removeAll(nationalityComboBox.getItems());
        nationalityComboBox.getItems().addAll("United States", "Bangladesh", "Australia",
                "Japan", "Brazil", "Spain", "Mexico", "Thailand", "Sweden", "Morocco", "China");
    }

    private void registerUser() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String registerQuery = "INSERT into USER (firstname, lastname, username, password, outdoors, urban, cultural, food)" +
                "VALUES ('" + User.getInstance().getFirstName() + "','" + User.getInstance().getLastName() +
                "','" + User.getInstance().getUsername() +
                "','" + User.getInstance().getPassword() +
                "','" + User.getInstance().isOutdoors() +
                "','" + User.getInstance().isUrban() +
                "','" + User.getInstance().isCultural() +
                "','" + User.getInstance().isFood() + "')";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(registerQuery);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        connection.close();
    }
}