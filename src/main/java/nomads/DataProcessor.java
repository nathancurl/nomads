package nomads;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class DataProcessor {
    private final String visaFilePath = "data/visa.csv";
    private final String countriesFilePath = "data/countries.csv";
    private String nationality;
    private final String[] target = {"United States", "Bangladesh", "Australia", "Japan", "Brazil", "Spain", "Mexico", "Thailand", "Sweden", "Morocco", "China"};

    private File getFile(String fileName) {
        return new File(fileName);
    }

    HashMap generateVisaInfo(User user) throws FileNotFoundException {
        this.nationality = user.nationality;
        HashMap<String, String> hashMap = new HashMap<String, String>();
        Scanner scanner = new Scanner(getFile(visaFilePath));
        String line = scanner.nextLine();
        String[] keys = line.split(",");
        String[] values = new String[keys.length];

        // Get to the line with the nationality visa info
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            values = line.split(",");
            if (values[0].equals(nationality)) {
                break;
            }
        }
        scanner.close();

        // Create the hashmap with the visa info
        int i = 1;
        while (i < values.length) {
            hashMap.put(keys[i], values[i]);
            i++;
        }


        // Filter the hashmap with target countries
        List targetList = Arrays.asList(target);
        //System.out.println(Arrays.toString(keys));
        for (String country : keys) {
            if (!targetList.contains(country)) {
                hashMap.remove(country);
            }
        }

        return hashMap;
    }

    private String getString(String word) {
        word = word.substring(1, word.length() - 1);
        return word.trim();
    }

    ArrayList<Country> generateCountries(HashMap hashMap) throws FileNotFoundException, SQLException {
        ArrayList<Country> countries = new ArrayList<>();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String getCountryDataQuery = "SELECT * FROM USER WHERE country = '" + User.getInstance().getNationality() + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getCountryDataQuery);

            while(resultSet.next()){
                String region = resultSet.getString("Region");
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        connection.close();
        Scanner scanner = new Scanner(getFile(countriesFilePath));
        String line = scanner.nextLine();
        String[] values;
        List targetList = Arrays.asList(target);

        // Get info of countries and generate Country object
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            values = line.split(",");
            if (targetList.contains(getString(values[0]))) {
                countries.add(new Country((String) hashMap.get(getString(values[0])), getString(values[0]), getString(values[1]), Integer.parseInt(getString(values[2])), Integer.parseInt(getString(values[3]))));
            }
        }
        scanner.close();

        return countries;
    }

}