package FileAndDatabase;

import java.util.ArrayList;
import MainClasses.Swimmer;

public class Database {
    private ArrayList<Swimmer> swimmers;

    public Database() {
    }

    public ArrayList<Swimmer> getSwimmers() {
        return swimmers;
    }

    public void initSwimmers(ArrayList<Swimmer> swimmers) {
        this.swimmers = swimmers;
    }
    public void deleteSwimmer(Swimmer swimmerDelete){
        swimmers.remove(swimmerDelete);
    }
}
