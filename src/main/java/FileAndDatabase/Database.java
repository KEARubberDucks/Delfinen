package FileAndDatabase;

import java.util.ArrayList;

import Enums.Discipline;
import Swimmers.CompetitiveSwimmer;
import Swimmers.Swimmer;

public class Database {
    private ArrayList<Swimmer> swimmers;
    private ArrayList<Swimmer> searchResult;
    private boolean unsavedChanges;
    private int year;
    public Database() {
        unsavedChanges = false;
    }

    public ArrayList<Swimmer> getSwimmers() {
        return swimmers;
    }

    public void initSwimmers(ArrayList<Swimmer> swimmers) {
        this.swimmers = swimmers;
    }

    public void createSwimmer(String name, int age, boolean isActive, boolean competetiv, boolean paid) {
        swimmers.add(new Swimmer(name, age, isActive, competetiv, paid));
        unsavedChanges = true;
    }
    public void deleteSwimmer(Swimmer swimmerDelete){
        swimmers.remove(swimmerDelete);
        unsavedChanges = true;
    }
    public void payStatus(Swimmer swimmerPaying, boolean payStatus){
        swimmerPaying.setHasPaid(payStatus);
        unsavedChanges = true;
    }
    public void printHeroes() {
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

    public void createSwimmer(String name, int age, boolean isActive, boolean competetiv, boolean hasPaid, String coachName, Discipline[] choices) {
        swimmers.add(new CompetitiveSwimmer(name, age, isActive, competetiv, hasPaid, coachName, choices));
        unsavedChanges = true;
    }
}
