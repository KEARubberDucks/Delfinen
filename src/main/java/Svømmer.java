import java.util.ArrayList;

public class Svømmer {

    String name;
    int age;
    boolean isActive;
    boolean isjunior = false;
    boolean isSeniors = false;
    boolean competetiv;

    ArrayList<Svømmer> Svømmere;

    Svømmer(String name, int age, boolean isActive, boolean competetiv) {
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

    public void createTestData() {
        Svømmere.add(new Svømmer("Bob", 34, false, false));
        Svømmere.add(new Svømmer("Jacob", 17, true, false));
        Svømmere.add(new Svømmer("Emil", 68, true, false));
        Svømmere.add(new Svømmer("Morten", 16, false, false));
    }
}
