package nomads;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class DestinationGenerator {
    DataProcessor dataProcessor;
    User user;

    private ArrayList<Country> generate(User user) throws FileNotFoundException, SQLException {
        dataProcessor = new DataProcessor();
        this.user = user;
        return order(dataProcessor.generateCountries(dataProcessor.generateVisaInfo(this.user)));
    }

    private ArrayList<Country> order(ArrayList<Country> countries) {
        Collections.sort(countries, Collections.reverseOrder());
        return countries;
    }


    public ArrayList<Country> getDestinations(User user) throws FileNotFoundException, SQLException {
        return generate(user);
    }

    public String[] getStringArr(ArrayList<Country> destinations) {
        String[] stringArr = new String[destinations.size()];
        for (int i = 0; i < destinations.size(); i++){
            stringArr[i] = destinations.get(i).getName();
        }
        return stringArr;
    }
}

