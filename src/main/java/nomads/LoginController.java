package nomads;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.sql.Connection;

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

    @FXML
    protected void onLoginButtonClicked(ActionEvent e) throws SQLException {
        if ((!usernameTextField.getText().isBlank()) && (!passwordPasswordField.getText().isBlank())){
            validateLogin();
        }else{
            warningLabel.setText("Please enter both username and password!");
        }

    }
    @FXML
    protected void onRegisterButtonClicked(ActionEvent e) throws IOException {
        // Close existing stage
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();

        // Load register view
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Nomad Travels");
        stage.setScene(scene);
        stage.show();
    }

    private void validateLogin() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String verifyQuery = "SELECT COUNT(1) FROM USER WHERE USERNAME = '"
                +   usernameTextField.getText() + "' AND PASSWORD = '" + passwordPasswordField.getText() +"'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(verifyQuery);

            while(resultSet.next()){
                if(resultSet.getInt(1) == 1){
                    warningLabel.setText("Your profile exists!");

                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();

                    // Load register view
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("menu-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    stage.setTitle("Nomad Travels");
                    stage.setScene(scene);
                    stage.show();



                }else{
                    warningLabel.setText("Invalid Login! Try again.");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        connection.close();
    }


}