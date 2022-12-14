package Swimmers;

public class Swimmer {

    private String name;
    private int age;
    private boolean isActive;
    enum Type {SENIOR, NORMAL, JUNIOR}
    Type type;
    private boolean competitive;
    private boolean hasPaid;

    public Swimmer(String name, int age, boolean isActive, boolean competitive, boolean hasPaid) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.competitive = competitive;
        this.hasPaid = hasPaid;
        setType(age);
    }

    void setType(int age) {
        if (age < 18)
            type = Type.JUNIOR;
        else if (age > 60)
            type = Type.SENIOR;
        else type = Type.NORMAL;
    }

    public String getName(){
        return name;
    }

    public int getAge() {
        return age;
    }

    private String parseBoolean(boolean bool){
        return bool ? "ja" : "nej";
    }

    public String getIsActive() {
        return parseBoolean(isActive);
    }

    public String getIsCompetitive() {
        return parseBoolean(competitive);
    }
    public String getHasPaid(){
        return parseBoolean(hasPaid);
    }
    public void setHasPaid(boolean payment){
        hasPaid = payment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAgeGroup(){
        String activeStatus = "";
        switch (type){
            case JUNIOR -> activeStatus = "junior";
            case NORMAL -> activeStatus = "normal";
            case SENIOR -> activeStatus = "senior";
        }
        return activeStatus;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setCompetitive(boolean competitive) {
        this.competitive = competitive;
    }

    @Override
    public String toString() {
        return getName()+ "; "+ getAge()+ "; " + getIsActive()+ "; " + getIsCompetitive()+ "; " + getHasPaid();
    }
}
