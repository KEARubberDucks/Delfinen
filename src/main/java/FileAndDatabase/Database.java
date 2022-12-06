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


    public boolean hasUnsavedChanges(){ 
        return unsavedChanges;
    }
}
