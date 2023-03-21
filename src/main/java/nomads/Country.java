package nomads;

public class Country implements Comparable<Country>{
    String name, region, visa; 
    boolean outdoors, urban, cultural, food, isFavorite;
    int population, area, rank;
    Double Density, Coastline, Migration, infantMortality, GDP, Literacy, Phones, Arable, Crops, Other, Climate, Birthrate, Deathrate, Agriculture, Industry, Service;



    public Country(String visa, String name, String region, int population, int area) {
        this.name = name;
        this.region = region;
        this.visa = visa;
        this.population = population;
        this.area = area;
        this.rank = setVisaRank(visa);
    }

    public void updatePreferences(int outdoors, int cultural, int food, int urban){
        this.outdoors = outdoors == 1;
        this.cultural = cultural == 1;
        this.food = food == 1;
        this.urban = urban == 1;
    }

    @Override
    public int compareTo(Country c) {
        return this.rank > c.rank ? 1 : (this.rank < c.rank ? -1 : 0);
    }

    public int setVisaRank(String visa){
        if (visa == null){
            return 0;
        }
        if (isInteger(visa)){
            return 3;
        }
        else if (visa.equals("visa free")) {
            return 4;
        }
        else if(visa.equals("e-visa")){
            return 1;
        }
        else if(visa.equals("visa required")){
            return 0;
        }
        else if(visa.equals("visa on arrival")){
            return 2;
        }
        else
        return 0;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return name + " :" + "\n" + " is in the " + region + " region with a population of " +
                population + " and a " + area + " sq. mi. area. Visa Requirement: " + visa +
                " Outdoors: " + outdoors + " Food: " + food +" Urban: " + urban +
                " Cultural: " + cultural +"\n";
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }

        return true;
    }

}
