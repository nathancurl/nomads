import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DestinationGenerator {
    DataProcessor dataProcessor;
    ArrayList<Country> destinations;
    User user;
    

    void generate(User user) throws FileNotFoundException {
        dataProcessor = new DataProcessor();
        this.user = user;
        //hardcode visa data
        destinations = order(dataProcessor.generateCountries(dataProcessor.generateVisaInfo(this.user)));
    }

    ArrayList<Country> order(ArrayList<Country> destinations){
        return null;

    }

    ArrayList<Country> getDestinations(User user) throws FileNotFoundException{
        generate(user);
        return destinations;
    }
}

