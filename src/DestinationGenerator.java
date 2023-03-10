import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

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

    public ArrayList<Country> order(ArrayList<Country> countries) {
//        for (int i = 1; i < countries.size(); i++) {
//            int value = countries.get(i).rank;
//            int j = i;
//            for (j = i-1; j >= 0; j--) {
//                if (countries.get(j).rank<= countries.get(i).rank) {
//                    break;
//                }
//            }
//            countries.add(j,countries.remove(i));
//        }
//        return countries;
        Collections.sort(countries, Collections.reverseOrder());
        return countries;
    }


    ArrayList<Country> getDestinations(User user) throws FileNotFoundException{
        generate(user);
        return destinations;
    }

    void displayDestinations(){
        for (Country country : destinations) {
            System.out.println(country);
        }
    }
}

