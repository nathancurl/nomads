import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class AppLoop {
    //main class intended to run application
    DestinationGenerator destinationGenerator;
    User user;
    Scanner scanner = new Scanner(System.in);



    void run() throws FileNotFoundException {
        login();
        favoritesPrompt();
        quitPrompt();
        scanner.close();
    }

    void favoritesPrompt(){
        System.out.println("Were there any Destinations you would like to add to a list of favorites?");
        String faveResponse = scanner.nextLine();
        if (faveResponse.equals("no")) {
            return;
        } else {
            user.setFavorites(faveResponse);

        }
    }

    void quitPrompt() throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to quit?");
        String quitResponse = scanner.nextLine();
        if (quitResponse.equals("yes")) {
            System.out.println("Thank you for using NOMAD Travel");
            scanner.close();
            user.close();

        } else {
            System.out.println("close it");
        }
    }



    private void login() throws FileNotFoundException {

            //Initial System User Interactions and Display on the CLI
            System.out.print("Welcome to NOMAD Travel Co ! \n");
            //Creating a scanner to read user responses
            //Scanner scanner = new Scanner(System.in);
            //prompting the user to sign in
            System.out.print("Returning or New?");
            String loginResponse = scanner.nextLine();
            //if user is returning system will be prompted to connect user login to an existing profile
            if (loginResponse.equals("Returning")) {
                ReturningUser();

            //if user is new then the system will create a user profile using user responses to prompts
            } else {
                CreateNewUser();

            }
            //set favorites
            //quit prompt
       // scanner.close();
        }

    private void displayPrompt(User user) throws FileNotFoundException{
      //  Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like to search destinations?");
            String searchResponse = scanner.nextLine();
            if (searchResponse.equals("yes")){
                System.out.println("Destinations and travel requirements :");
                displayNations(user);
            }
           // scanner.close();
        }

    private void ReturningUser() throws FileNotFoundException {
    //    Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username :");
        String userName = scanner.nextLine();

        File file = new File("users", userName);

        if (file.exists()) {
            System.out.println("Your profile exists!");
            user = new User(userName);
            user.updateRetuningUser(userName);
            displayPrompt(user);

        }


        else {
            System.out.println("Your profile doesn't exist!");
            CreateNewUser();
        }



    }

    private void CreateNewUser() throws FileNotFoundException {
        DataProcessor dataprocessor = new DataProcessor();
       // Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username :");
        String userName = scanner.nextLine();
        System.out.println("What is your nation of origin? : ");
        String userNationality = scanner.nextLine();
        this.user = new User(userName, userNationality);
        displayPrompt(user);


    }


    void displayNations(User user) throws FileNotFoundException {
        destinationGenerator = new DestinationGenerator();
        destinationGenerator.getDestinations(user);
        destinationGenerator.displayDestinations();

    }


    public static void main(String[] args) throws FileNotFoundException {

        AppLoop appLoop = new AppLoop();
        appLoop.run();

    }
}
