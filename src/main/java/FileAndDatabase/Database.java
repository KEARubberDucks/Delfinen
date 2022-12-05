package FileAndDatabase;

import java.util.ArrayList;
import MainClasses.Swimmer;

public class Database {
    private ArrayList<Swimmer> swimmers;
    private ArrayList<Swimmer> searchResult;
    private boolean unsavedChanges;

    public Database() {
        unsavedChanges = false;
    }

    public ArrayList<Swimmer> getSwimmers() {
        return swimmers;
    }

    public void initSwimmers(ArrayList<Swimmer> swimmers) {
        this.swimmers = swimmers;
    }

    public void createSwimmer(String name, int age, boolean isActive, boolean competetiv) {
        swimmers.add(new Swimmer(name, age, isActive, competetiv));
        unsavedChanges = true;
    }
    public void deleteSwimmer(Swimmer swimmerDelete){
        swimmers.remove(swimmerDelete);
        unsavedChanges = true;
    }
    public void printSwimmers() {
        System.out.println("liste af svømmere");
        for (Swimmer swimmer : swimmers) {
            System.out.println(" ");
            System.out.println((swimmers.indexOf(swimmer) + 1) + "----------");
            System.out.println("svømmers navn: " + swimmer.getName());
            System.out.println("svømmers age: " + swimmer.getAge());
            System.out.println("er kompetetiv: " + swimmer.getIsCompetitive());
            System.out.println("----------");
        }

    }

    public boolean hasUnsavedChanges(){ 
        return unsavedChanges;
    }
}
