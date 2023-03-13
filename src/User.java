import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class User {
    String name;
    String nationality;
    ArrayList<String> favorites = new ArrayList<>();

    public User(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public User(String name) {
        this.name = name;
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
        File file = new File("users", name);
        PrintWriter writer = new PrintWriter(file);
        try {
            writer = new PrintWriter(file);
            writer.write("Username: " + (name) + "\n" + "Nationality: " + (nationality) + "\n" + "Favorites: " + favorites);
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






