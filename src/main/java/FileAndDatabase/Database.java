package FileAndDatabase;

import java.util.ArrayList;
import MainClasses.Swimmer;

public class Database {
    private ArrayList<Swimmer> swimmers;

    public Database() {
        swimmers = new ArrayList<>();
    }

    public ArrayList<Swimmer> getSwimmers() {
        return swimmers;
    }
}
