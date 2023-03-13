import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class AppLoop {
    //main class intended to run application
    DestinationGenerator destinationGenerator;
    ArrayList<Country> destinations;
    User user;
    Scanner scanner = new Scanner(System.in);

    void run() throws FileNotFoundException {
        while (!login()){
            System.out.println("Please log in again!");
        }
        mainMenu();
    }

    int filterInput(String input){
        // Remove all whitespace from the input string
        String noSpaces = input.replaceAll("\\s+", "");

        // Loop through the characters in the noSpaces string and build up the integer
        StringBuilder intString = new StringBuilder();
        for (int i = 0; i < noSpaces.length(); i++) {
            char c = noSpaces.charAt(i);
            if (Character.isDigit(c)) {
                intString.append(c);
            }
        }

        // Try to parse the resulting string as an integer
        int result = 0;
        try {
            result = Integer.parseInt(intString.toString());
        } catch (NumberFormatException e) {
            // If there were no digits in the input, or if the resulting string isn't a valid integer, the result will be 0
        }

        return result;
    }

    void mainMenu() throws FileNotFoundException {
        System.out.println("\n--------Main Menu----------- \n" +
                           "1. Search New Destinations \n" +
                           "2. See Favorites \n" +
                           "3. Exit \n"+
                           "---------------------------- \n");
        int userInput = filterInput(scanner.nextLine());

        if (userInput == 1){
            displayNations(user);
        } else if (userInput == 2){
            showFavorites(user);
        } else if (userInput == 3){
            quit();
        } else {
            System.out.println("Invalid input. Please try again.");
            mainMenu();
        }
    }

    void favoritesPrompt() throws FileNotFoundException {
        System.out.println("Please add countries to your list of favourites. Example: Spain, France, Italy");
        System.out.println("Press Q to skip and return to main menu");
        String faveResponse = scanner.nextLine();
        if ((faveResponse.equals("q")) || (faveResponse.equals("Q")) ){
            mainMenu();
        }
        else{
            user.setFavorites(faveResponse);
            mainMenu();
        }
    }

    void quit() throws FileNotFoundException {
        System.out.println("Thank you for using NOMAD Travel");
        //scanner.close();
        user.close();
    }



    private boolean login() throws FileNotFoundException {

            //Initial System User Interactions and Display on the CLI
            System.out.println("Welcome to NOMAD Travel Co !");

            System.out.println("Returning or New?");
            String loginResponse = scanner.nextLine().toLowerCase();
            //if user is returning system will be prompted to connect user login to an existing profile
            if (loginResponse.equals("returning")) {
                ReturningUser();
                return true;

            //if user is new then the system will create a user profile using user responses to prompts
            } else if (loginResponse.equals("new")) {
                CreateNewUser("");
                return true;

            }else{
                System.out.println("Login failed!");
                return false;
            }
        }

    private boolean validCountry(String country) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File( "data/visa.csv"));
        String line = fileScanner.nextLine();
        List<String> countries = Arrays.asList(line.split(","));
        return countries.contains(country);
    }

    private void ReturningUser() throws FileNotFoundException {
        System.out.println("Enter your username :");
        String userName = scanner.nextLine().toLowerCase();

        File file = new File("users", userName);

        if (file.exists()) {
            System.out.println("Your profile exists!");
            user = new User(userName);
            user.updateRetuningUser(userName);

        }
        else {
            System.out.println("Your profile doesn't exist!");
            CreateNewUser(userName);
        }

    }
    private static String capitalizeWords(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return sb.toString().trim();
    }

    private void CreateNewUser(String userName) throws FileNotFoundException {
        if (userName.equals("")){
            System.out.println("Enter your username :");
            userName = scanner.nextLine();
        }
        System.out.println("What is your nation of origin? Please capitalise it: ");
        String userNationality = capitalizeWords(scanner.nextLine());
        while(!validCountry(userNationality)){
            System.out.println("Invalid country input! Please reenter nation of origin:");
            userNationality = capitalizeWords(scanner.nextLine());
        }
        this.user = new User(userName, userNationality);

    }

    private void displayNations(User user) throws FileNotFoundException {
        destinationGenerator = new DestinationGenerator();
        destinations = destinationGenerator.getDestinations(user);
        System.out.println("Destinations : " + "\n");
        for (Country country : destinations) {
            System.out.println(country);
        }
        favoritesPrompt();
    }

    void showFavorites(User user) throws FileNotFoundException {

        if ((user.favorites.equals(null) || (user.favorites.size() == 0))){
            System.out.println("No User Data to Display ...");
            System.out.println("Press Q to quit and return to main menu");
            String response = scanner.nextLine().toLowerCase();
            while (!response.equals("q")){
                System.out.println("Invalid Response Try Again ... ");
                response = scanner.nextLine().toLowerCase();
            }
            mainMenu();
        } else {
            System.out.println("Favorites:");
            for (String place : user.favorites) {
                System.out.println(place);
            }
        }
        mainMenu();
    }




    public static void main(String[] args) throws FileNotFoundException {

        AppLoop appLoop = new AppLoop();
        appLoop.run();

    }
}
