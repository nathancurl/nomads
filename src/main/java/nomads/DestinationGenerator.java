package nomads;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class DestinationGenerator {
    DataProcessor dataProcessor;
    User user;
    

    ArrayList<Country> generate(User user) throws FileNotFoundException, SQLException {
        dataProcessor = new DataProcessor();
        this.user = user;
        return order(dataProcessor.generateCountries(dataProcessor.generateVisaInfo(this.user)));
    }

    public ArrayList<Country> order(ArrayList<Country> countries) {
        Collections.sort(countries, Collections.reverseOrder());
        return countries;
    }


    ArrayList<Country> getDestinations(User user) throws FileNotFoundException, SQLException {
        return generate(user);
    }
}

