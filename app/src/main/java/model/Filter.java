package model;

public class Filter{

    private int maxDistance;
    private int[] ageRange;
    private String[] genderPreferences;


    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public int[] getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(int[] ageRange) {
        this.ageRange = ageRange;
    }

    public String[] getGenderPreferences() {
        return genderPreferences;
    }

    public void setGenderPreferences(String[] genderPreferences) {
        this.genderPreferences = genderPreferences;
    }
}