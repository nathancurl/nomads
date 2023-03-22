package nomads;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserModel {

    DatabaseConnectionModel databaseConnectionModel = new DatabaseConnectionModel();
    Connection connection = databaseConnectionModel.getConnection();

    public boolean validateLogin(String username, String password) throws SQLException {
        String verifyQuery = "SELECT COUNT(1) FROM USER WHERE USERNAME = '"
                + username + "' AND PASSWORD = '" + password + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(verifyQuery);

            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        connection.close();
        return false;
    }

    public void updateUserAtLogin(String username){
        String getCountryDataQuery = "SELECT * FROM USER WHERE username = '" + username + "'";

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

    public void updateUserAfterLogin() throws SQLException {
        String updateQuery = "UPDATE USER " +
                "SET firstname = '" + User.getInstance().getFirstName() +
                "', lastname = '" + User.getInstance().getLastName() +
                "', username = '" + User.getInstance().getUsername() +
                "', password = '" + User.getInstance().getPassword() +
                "', outdoors = " + User.getInstance().isOutdoors() +
                ", urban = " + User.getInstance().isUrban() +
                ", cultural = " + User.getInstance().isCultural() +
                ", food = " + User.getInstance().isFood() + " WHERE username = '" + User.getInstance().getUsername() + "'";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuery);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        connection.close();
    }

    public void registerUser() throws SQLException {
        String registerQuery = "INSERT into USER (firstname, lastname, username, password, outdoors, urban, cultural, food, nationality)" +
                "VALUES ('" + User.getInstance().getFirstName() + "','" + User.getInstance().getLastName() +
                "','" + User.getInstance().getUsername() +
                "','" + User.getInstance().getPassword() +
                "','" + User.getInstance().isOutdoors() +
                "','" + User.getInstance().isUrban() +
                "','" + User.getInstance().isCultural() +
                "','" + User.getInstance().isFood() +
                "','" + User.getInstance().getNationality() + "')";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(registerQuery);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        connection.close();
    }
}
