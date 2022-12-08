package FileAndDatabase;

import java.util.ArrayList;

import Enums.Discipline;
import Swimmers.CompetitiveSwimmer;
import Swimmers.Swimmer;

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

    public void createSwimmer(String name, int age, boolean isActive, boolean competitive, boolean paid) {
        swimmers.add(new Swimmer(name, age, isActive, competitive, paid));
        unsavedChanges = true;
    }
    public void deleteSwimmer(Swimmer swimmerDelete){
        swimmers.remove(swimmerDelete);
        unsavedChanges = true;
    }

    public void swimmerPayment(Swimmer swimmer, boolean paid){
        swimmer.setHasPaid(paid);
        unsavedChanges = true;
    }
    public void setUnsavedChanges(){
        unsavedChanges = true;
    }

    public boolean hasUnsavedChanges(){ 
        return unsavedChanges;
    }

    public void createSwimmer(String name, int age, boolean isActive, boolean competitive, boolean hasPaid, String coachName, ArrayList<Discipline> choices) {
        swimmers.add(new CompetitiveSwimmer(name, age, isActive, competitive, hasPaid, coachName, choices));
        unsavedChanges = true;
    }

    public ArrayList<Swimmer> getCompetitiveSwimmers() {
        ArrayList<Swimmer> compSwimmers = new ArrayList<>();
        for (Swimmer swimmer : swimmers) {
            if (swimmer instanceof CompetitiveSwimmer){
                compSwimmers.add(swimmer);
            }
        }
        return compSwimmers;
    }
}
