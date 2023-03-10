public class Country {
    String name, region, visa;
    int population, area;
    int rank;


    public Country(String visa, String name, String region, int population, int area) {
        this.name = name;
        this.region = region;
        this.visa = visa;
        this.population = population;
        this.area = area;
        this.rank = setRank(visa);
    }

    public int setRank(String visa){
        if (visa == null){
            return 0;
        }
        if (visa.equals("visa free")) {
            return 3;
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
        return 0;
    }

    public String toString(){
        return name + " is in the " + region + " region with a population of " + population + " and a " + area + " sq. mi. area. Visa Requirement: " + visa ;
    }

}
