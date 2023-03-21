package nomads;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class User {
    private static final User user = new User();
    String firstName, lastName, nationality, username, password;
    boolean outdoors, urban, cultural, food;

    ArrayList<String> favorites = new ArrayList<>();
    ArrayList<Country> destinations;


    public static User getInstance(){
        return user;

    }

    public void setDestinations(ArrayList<Country> destinations){
        this.destinations = destinations;
    }

    public ArrayList<Country> getDestinations(){
        return destinations;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOutdoors(boolean outdoors) {
        this.outdoors = outdoors;
    }

    public void setUrban(boolean urban) {
        this.urban = urban;
    }

    public void setCultural(boolean cultural) {
        this.cultural = cultural;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int isOutdoors() {
        return outdoors? 1:0;
    }

    public int isUrban() {
        return urban? 1:0;
    }

    public int isCultural() {
        return cultural? 1:0;
    }

    public int isFood() {
        return food? 1:0;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", outdoors=" + outdoors +
                ", urban=" + urban +
                ", cultural=" + cultural +
                ", food=" + food +
                ", favorites=" + favorites +
                '}';
    }

    public void loginUpdate(String firstName, String lastName, String nationality, boolean outdoors, boolean urban, boolean cultural, boolean food){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.outdoors = outdoors;
        this.urban = urban;
        this.cultural = cultural;
        this.food = food;
    }

    public void updateRetuningUser(String path) {
        File file = new File("users", path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line = scanner.nextLine();
        line = scanner.nextLine();
        String[] arr = line.split(" ");
        this.nationality = arr[1];
        if (scanner.hasNextLine()) {
            line = scanner.nextLine().substring(12);
            arr = line.split(", ");
            String lastWord = arr[arr.length-1];
            lastWord = lastWord.substring(0, lastWord.length()-1);
            arr[arr.length-1] = lastWord;
            this.favorites = new ArrayList<String>(Arrays.asList(arr));
        } else {
            this.favorites = new ArrayList<>();
        }

    }


    public void close() throws FileNotFoundException {
        File file = new File("users", username);
        PrintWriter writer = new PrintWriter(file);
        try {
            writer = new PrintWriter(file);
            writer.write("Username: " + (username) + "\n" + "Nationality: " + (nationality) + "\n" + "Favorites: " + favorites);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void setFavorites(String faves) {
        String[] words = faves.split(", ");
        for (String word : words) {
            if(!favorites.contains(word)){
                favorites.add(word);
            }
        }
    }
}
