package MainClasses;

import java.util.ArrayList;

public class Swimmer {

    String name;
    int age;
    boolean isActive;
    boolean isjunior = false;
    boolean isSeniors = false;
    boolean competetiv;

    ArrayList<Swimmer> Svømmere;

    public Swimmer(String name, int age, boolean isActive, boolean competetiv) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        if (age < 18)
            isjunior = true;
        this.isjunior = isjunior;
        if (age > 60)
            isSeniors = true;
        this.isSeniors = isSeniors;
        this.competetiv = competetiv;
    }
    public String getName(){
        return name;
    }



    public void createTestData() {
        Svømmere.add(new Swimmer("Bob", 34, false, false));
        Svømmere.add(new Swimmer("Jacob", 17, true, false));
        Svømmere.add(new Swimmer("Emil", 68, true, false));
        Svømmere.add(new Swimmer("Morten", 16, false, false));
    }
}
