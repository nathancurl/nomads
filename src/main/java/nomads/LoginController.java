package nomads;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import static nomads.MainApp.changeScene;

public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    UserModel userModel = new UserModel();

    @FXML
    protected void onLoginButtonClicked(ActionEvent e) throws SQLException, IOException {
        if ((!usernameTextField.getText().isBlank()) && (!passwordPasswordField.getText().isBlank())) {
            if (validateLogin()) {
                updateUser();
                System.out.println(User.getInstance());
                changeScene(loginButton, "destination-generator-view.fxml");
            }
        } else {
            warningLabel.setText("Please enter both username and password!");
        }

    }

    private void updateUser() {
        userModel.updateUserAtLogin(usernameTextField.getText());
    }

    @FXML
    protected void onRegisterButtonClicked(ActionEvent e) throws IOException {
        changeScene(loginButton, "register-view.fxml");
    }

    private boolean validateLogin() throws SQLException {
        if (userModel.validateLogin(usernameTextField.getText(), passwordPasswordField.getText())){
            warningLabel.setText("Successful login");
            return true;
        }
        else{
            warningLabel.setText("Invalid login");
            return false;
        }
    }

}