package bean;

// Agelist for age list page
public class AgeList {
    private String ageGroup;
    private String maleSum;
    private String femaleSum;

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getFemaleSum() {
        return femaleSum;
    }

    public String getMaleSum() {
        return maleSum;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public void setFemaleSum(String femaleSum) {
        this.femaleSum = femaleSum;
    }

    public void setMaleSum(String maleSum) {
        this.maleSum = maleSum;
    }

}
