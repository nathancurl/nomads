package nomads;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static nomads.MainApp.countries;

public class CountryModel {

    private File getFile() {
        return new File("data/visa.csv");
    }

    private HashMap updateVisaInfo(User user) throws FileNotFoundException {
        String nationality = user.nationality;
        HashMap<String, String> hashMap = new HashMap<>();
        Scanner scanner = new Scanner(getFile());
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
        List<String> targetList = Arrays.asList(countries);
        for (String country : keys) {
            if (!targetList.contains(country)) {
                hashMap.remove(country);
            }
        }

        return hashMap;
    }

    private ArrayList<Country> updateGeneralInfo(HashMap hashMap) throws SQLException {
        ArrayList<Country> destinations = new ArrayList<>();
        DatabaseConnectionModel databaseConnectionModel = new DatabaseConnectionModel();
        Connection connection = databaseConnectionModel.getConnection();

        for (String country : countries) {
            String getCountryDataQuery = "SELECT * FROM COUNTRIES WHERE Country = '" + country + "'";

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(getCountryDataQuery);
                String Region = "";
                int Population = 0, Area = 0;
                while (resultSet.next()) {
                    Region = resultSet.getString("Region");
                    Population = resultSet.getInt("Population");
                    Area = resultSet.getInt("Area");
                }
                destinations.add(new Country((String) hashMap.get(country), country, Region, Population, Area));

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }


        connection.close();

        return destinations;
    }

    private ArrayList<Country> updatePreferences(ArrayList<Country> destinations) throws SQLException {
        DatabaseConnectionModel databaseConnectionModel = new DatabaseConnectionModel();
        Connection connection = databaseConnectionModel.getConnection();

        for (Country country : destinations) {
            String getPreferencesQuery = "SELECT * FROM PREFERENCES WHERE country = '" + country.getName() + "'";

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(getPreferencesQuery);
                while (resultSet.next()) {
                    int outdoors = resultSet.getInt("outdoors");
                    int cultural = resultSet.getInt("cultural");
                    int food = resultSet.getInt("food");
                    int urban = resultSet.getInt("urban");
                    String description = resultSet.getString("description");
                    country.updatePreferences(outdoors, cultural, food, urban, description);
                }

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }


        connection.close();

        return destinations;
    }

    ArrayList<Country> generateCountries(User user) throws FileNotFoundException, SQLException {
        return updatePreferences(updateGeneralInfo(updateVisaInfo(user)));
    }

}
