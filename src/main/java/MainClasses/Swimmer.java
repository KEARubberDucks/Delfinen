package MainClasses;

import java.util.ArrayList;

public class Swimmer {

    private String name;
    private int age;
    private boolean isActive;
    private boolean isjunior = false;
    private boolean isSeniors = false;
    private boolean competetiv;

    ArrayList<Swimmer> swimmers;

    public Swimmer(String name, int age, boolean isActive, boolean competetiv) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.isjunior = age < 18;
        this.isSeniors = age > 60;
        this.competetiv = competetiv;
    }

    public void createSvømmer(String name, int age, boolean isActive, boolean competetiv){
        swimmers.add(new Swimmer(name, age, isActive, competetiv));
    }

    public String getName(){
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean getisActive() {
        return isActive;
    }

    public boolean getIsCompetetiv() {
        return competetiv;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isCompetetiv(){
        return competetiv;
    }
    public boolean isSenior(){
        boolean seniorStatus = false;
        if (age < 18)
            seniorStatus = false;
        if (age > 60)
            seniorStatus = true;
        return seniorStatus;
    }
    public String isActive(){
        String activeStatus = "";
        if (age <= 18) {
            activeStatus = "junior";
        } else if (age >= 60) {
            activeStatus = "senior";
        } else activeStatus = "normal";
        return activeStatus;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setCompetetiv(boolean competetiv) {
        this.competetiv = competetiv;
    }
}
