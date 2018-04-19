package rob_pc.fypbelaytionship;


public class UserInformation {

    private int id;
    private String name;
    private String age;
    private String gender;
    private String onSightBouldering;
    private String onSightTrad;
    private String onSightSport;
    private String workedBouldering;
    private String workedTrad;
    private String workedSport;
    private String yearsClimbing;
    private String climbingFreq;
    private String favCrag;
    private String favRock;
    private String favClimbing;



    public UserInformation(int id, String name, String age, String gender, String onSightBouldering, String onSightTrad, String onSightSport, String workedBouldering, String workedTrad, String workedSport, String yearsClimbing, String climbingFreq, String favCrag, String favRock, String favClimbing) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.onSightBouldering = onSightBouldering;
        this.onSightTrad = onSightTrad;
        this.onSightSport = onSightSport;
        this.workedBouldering = workedBouldering;
        this.workedTrad = workedTrad;
        this.workedSport = workedSport;
        this.yearsClimbing = yearsClimbing;
        this.climbingFreq = climbingFreq;
        this.favCrag = favCrag;
        this.favRock = favRock;
        this.favClimbing = favClimbing;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOnSightBouldering() {
        return onSightBouldering;
    }

    public void setonSightBouldering(String onSightBouldering) {
        this.onSightBouldering = onSightBouldering;
    }


    public String getOnSightTrad() {
        return onSightTrad;
    }

    public void setOnSightTrad(String onSightTrad) {
        this.onSightTrad = onSightTrad;
    }

    public String getOnSightSport() {
        return onSightSport;
    }

    public void setOnSightSport(String onSightSport) {
        this.onSightSport = onSightSport;
    }

    public String getWorkedBouldering() {
        return workedBouldering;
    }

    public void setWorkedBouldering(String workedBouldering) {
        this.workedBouldering = workedBouldering;
    }

    public String getWorkedTrad() {
        return workedTrad;
    }

    public void setWorkedTrad(String workedTrad) {
        this.workedTrad = workedTrad;
    }

    public String getWorkedSport() {
        return workedSport;
    }

    public void setWorkedSport(String workedSport) {
        this.workedSport = workedSport;
    }

    public String getYearsClimbing() {

        return yearsClimbing;
    }

    public void setYearsClimbing(String yearsClimbing) {
        this.yearsClimbing = yearsClimbing;
    }

    public String getClimbingFreq() {
        return climbingFreq;
    }

    public void setClimbingFreq(String climbingFreq) {
        this.climbingFreq = climbingFreq;
    }

    public String getFavCrag() {
        return favCrag;
    }

    public void setFavCrag(String favCrag) {
        this.favCrag = favCrag;
    }

    public String getFavRock() {
        return favRock;
    }

    public void setFavRock(String favRock) {
        this.favRock = favRock;
    }

    public String getFavClimbing() {
        return favClimbing;
    }

    public void setFavClimbing(String favClimbing) {
        this.favClimbing = favClimbing;
    }


}
