package nomads;

public class Country implements Comparable<Country>{
    String name, region, visa; 
    boolean outdoors, urban, cultural, food;
    Integer Population, Area;
    Double Denstiy, Coastline, Migration, infantMortality, GDP, Literacy, Phones, Arable, Crops, Other, Climate, Birthrate, Deathrate, Agriculture, Industry, Service;



    public Country(String visa, String name, String region, int population, int area) {
        this.name = name;
        this.region = region;
        this.visa = visa;
        this.Population = population;
        this.Area = area;
        this.rank = setRank(visa);
    }

    @Override
    public int compareTo(Country c) {
        return this.rank > c.rank ? 1 : (this.rank < c.rank ? -1 : 0);
    }

    public int setRank(String visa){
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

    public String toString(){
        return name + " :" + "\n" + " is in the " + region + " region with a population of " + population + " and a " + area + " sq. mi. area. Visa Requirement: " + visa + "\n";
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
