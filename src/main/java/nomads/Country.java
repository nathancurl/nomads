package nomads;


public class Country implements Comparable<Country> {
    private final String name, region;
    private String visa;
    private boolean outdoors, urban, cultural, food;
    private final int population, area, rank;
    private String description;


    public Country(String visa, String name, String region, int population, int area) {
        this.name = name;
        this.region = region;
        this.population = population;
        this.area = area;
        this.rank = setVisaRank(visa) + setPreferenceRank();
    }

    public void updatePreferences(int outdoors, int cultural, int food, int urban, String description) {
        this.outdoors = outdoors == 1;
        this.cultural = cultural == 1;
        this.food = food == 1;
        this.urban = urban == 1;
        this.description = description;
    }

    @Override
    public int compareTo(Country c) {
        return this.rank > c.rank ? 1 : (this.rank < c.rank ? -1 : 0);
    }

    private int setPreferenceRank(){
        boolean[] preferenceBooleans = {this.outdoors == User.getInstance().outdoors,
        this.food == User.getInstance().food, this.cultural == User.getInstance().cultural,
        this.urban == User.getInstance().urban};

        int count = 0;
        for (boolean bool : preferenceBooleans) {
            if (bool) {
                count ++;
            }
        }
        return count;
    }

    private int setVisaRank(String visa) {
        if (visa == null) {
            return 0;
        }
        if (visa.equals("-1")) {
            this.visa = "Country of Nationality";
        } else if (isInteger(visa)) {
            this.visa = visa + " days";
            return 3;
        } else if (visa.equals("visa free")) {
            this.visa = "Visa Free";
            return 4;
        } else if (visa.equals("e-visa")) {
            this.visa = "E-Visa";
            return 1;
        } else if (visa.equals("visa required")) {
            this.visa = "Visa Required";
            return 0;
        } else if (visa.equals("visa on arrival")) {
            this.visa = "Visa on Arrival";
            return 2;
        }

        return 0;

    }

    public String getName() {
        return this.name;
    }


    public String toString() {
        return name + " :" + "\n" + " is in the " + region + " region with a population of " +
                population + " and a " + area + " sq. mi. area. Visa Requirement: " + visa +
                " Outdoors: " + outdoors + " Food: " + food + " Urban: " + urban +
                " Cultural: " + cultural + "\n";
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }

        return true;
    }

    public String getRegion() {
        return region;
    }

    public String getVisa() {
        return visa;
    }

    public boolean isOutdoors() {
        return outdoors;
    }

    public boolean isUrban() {
        return urban;
    }

    public boolean isCultural() {
        return cultural;
    }

    public boolean isFood() {
        return food;
    }

    public int getPopulation() {
        return population;
    }

    public int getArea() {
        return area;
    }

    public String getDescription() {
        return this.description;
    }
}
