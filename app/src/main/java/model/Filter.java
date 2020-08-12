package model;

public class Filter{

    private int maxDistance;
    private int maxAge;
    private int minAge;
    private String genderPreferences;

    public Filter(int maxDistance, int minAge, int maxAge, String genderPreferences){
        this.maxDistance = maxDistance;
        this.maxAge = maxAge;
        this.minAge = minAge;
        this.genderPreferences = genderPreferences;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setAgeRange(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public String getGenderPreferences() {
        return genderPreferences;
    }

    public void setGenderPreferences(String genderPreferences) {
        this.genderPreferences = genderPreferences;
    }
}