package nomads;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    @FXML
    protected void onLoginButtonClicked(ActionEvent e) throws SQLException, IOException {
        if ((!usernameTextField.getText().isBlank()) && (!passwordPasswordField.getText().isBlank())){
            if(validateLogin()){
                updateUser();
                System.out.println(User.getInstance());
                changeScene(loginButton, "update-user-view.fxml");
            }
        }else{
            warningLabel.setText("Please enter both username and password!");
        }

    }

    private void updateUser() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

            String getCountryDataQuery = "SELECT * FROM USER WHERE username = '" + usernameTextField.getText() + "'";

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(getCountryDataQuery);
                while (resultSet.next()) {
                    User.getInstance().setUsername(resultSet.getString("username"));
                    User.getInstance().setPassword(resultSet.getString("password"));
                    User.getInstance().setFirstName(resultSet.getString("firstName"));
                    User.getInstance().setLastName(resultSet.getString("lastName"));
                    User.getInstance().setNationality(resultSet.getString("nationality"));
                    User.getInstance().setOutdoors(resultSet.getInt("outdoors") == 1);
                    User.getInstance().setCultural(resultSet.getInt("cultural") == 1);
                    User.getInstance().setFood(resultSet.getInt("food") == 1);
                    User.getInstance().setUrban(resultSet.getInt("urban") == 1);
                }

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
    }

    @FXML
    protected void onRegisterButtonClicked(ActionEvent e) throws IOException {
        changeScene(loginButton, "register-view.fxml");
    }

    private boolean validateLogin() throws SQLException {
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
                    return true;
                }else{
                    warningLabel.setText("Invalid Login! Try again.");
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        connection.close();
        return false;
    }


}