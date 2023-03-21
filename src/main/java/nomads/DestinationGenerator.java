package nomads;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class DestinationGenerator {
    DataProcessor dataProcessor;


    private ArrayList<Country> generate(User user) throws FileNotFoundException, SQLException {
        dataProcessor = new DataProcessor();
        return order(dataProcessor.generateCountries(user));
    }

    private ArrayList<Country> order(ArrayList<Country> countries) {
        Collections.sort(countries, Collections.reverseOrder());
        return countries;
    }


    public ArrayList<Country> getDestinations(User user) throws FileNotFoundException, SQLException {
        return generate(user);
    }
}

