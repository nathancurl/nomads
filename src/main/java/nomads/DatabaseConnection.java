package nomads;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "nomads", databaseUser = "root", databasePassword = "root";
        String url = "jdbc:mysql://localhost:8889/"+databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            System.out.println("Connection established successfully!");
        }catch (Exception e){
            System.out.println("Cannot connect to database!");
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }
}
