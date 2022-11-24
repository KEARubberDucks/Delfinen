import java.util.ArrayList;

public class Svømmer {

    String name;
    int age;
    boolean isActive;
    boolean isjunior = false;
    boolean isSeniors = false;
    boolean competetiv;

    ArrayList<Svømmer> Svømmere;

    public String getName() {
        return name;
    }

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

    public void makeSvimmer(String name, int age, boolean isActive, boolean competetiv){

    }

    public void createTestData() {
        Svømmere.add(new Svømmer("Bob", 34, false, false));
        Svømmere.add(new Svømmer("Jacob", 17, true, false));
        Svømmere.add(new Svømmer("Emil", 68, true, false));
        Svømmere.add(new Svømmer("Morten", 16, false, false));
    }

    public ArrayList<Svømmer> searchResult = new ArrayList<>();

    public ArrayList<Svømmer> searchAndEdit(String searchTerm) {

        for (Svømmer Svømmere : Svømmere) {
            String name = Svømmere.getName().toLowerCase();
            //nu skal den finde dem der passer og add dem til searchResult
            if (name.contains(searchTerm)) {

                //sådan stopper jeg den i at lave flere af den samme element
                if (!searchResult.contains(Svømmere)) {
                    //add element : men add 2 gange?
                    searchResult.add(Svømmere);
                }

                //printer hvert element på sin index plads
                for (int i = 0; i < searchResult.size(); i++)
                    System.out.println(i + 1 + ":" + searchResult.get(i));

            }

        }
        return searchResult;
    }
}
