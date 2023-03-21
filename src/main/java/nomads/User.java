package nomads;

import java.util.ArrayList;


public class User {
    private static final User user = new User();
    String firstName, lastName, nationality, username, password;
    boolean outdoors, urban, cultural, food;

    ArrayList<Country> favorites = new ArrayList<>();
    ArrayList<Country> destinations;
    Country presentCountry;


    public static User getInstance() {
        return user;

    }

    public void setDestinations(ArrayList<Country> destinations) {
        this.destinations = destinations;
    }

    public ArrayList<Country> getFavorites() {
        return favorites;
    }

    public void addToFavorites(Country country) {
        if (!favorites.contains(country)) {
            favorites.add(country);
        }

    }

    public boolean contains(Country country) {
        for (Country favorite : favorites) {
            if (country.getName().equals(favorite.getName())) {
                return true;
            }
        }
        return false;
    }

    public void removeFromFavorites(Country country) {
        favorites.removeIf(favorite -> favorite.getName().equals(country.getName()));

    }

    public String[] getStringArr(ArrayList<Country> destinations) {
        String[] stringArr = new String[destinations.size()];
        for (int i = 0; i < destinations.size(); i++) {
            stringArr[i] = destinations.get(i).getName();
        }
        return stringArr;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int isOutdoors() {
        return outdoors ? 1 : 0;
    }

    public int isUrban() {
        return urban ? 1 : 0;
    }

    public int isCultural() {
        return cultural ? 1 : 0;
    }

    public int isFood() {
        return food ? 1 : 0;
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

    public void loginUpdate(String firstName, String lastName, String nationality, boolean outdoors, boolean urban, boolean cultural, boolean food) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.outdoors = outdoors;
        this.urban = urban;
        this.cultural = cultural;
        this.food = food;
    }

    public Country getPresentCountry() {
        return this.presentCountry;
    }

    public void setPresentCountry(String countryName) {
        presentCountry = getCountry(countryName);
    }

    public Country getCountry(String countryName) {
        for (Country country : destinations) {
            if (country.getName().equals(countryName)) {
                return country;
            }
        }
        return null;
    }
}
