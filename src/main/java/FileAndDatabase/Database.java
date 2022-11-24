package FileAndDatabase;

import java.util.ArrayList;
import MainClasses.Swimmer;

public class Database {
    private ArrayList<Swimmer> swimmers;
    private ArrayList<Swimmer> searchResult;

    public Database() {
    }

    public ArrayList<Swimmer> getSwimmers() {
        return swimmers;
    }

    public void initSwimmers(ArrayList<Swimmer> swimmers) {
        this.swimmers = swimmers;
    }


    public ArrayList<Swimmer> searchAndEdit(String searchTerm) {

        for (Swimmer swimmer : swimmers) {
            String name = swimmer.getName().toLowerCase();
            //nu skal den finde dem der passer og add dem til searchResult
            if (name.contains(searchTerm)) {

                //sådan stopper jeg den i at lave flere af den samme element
                if (!searchResult.contains(swimmer)) {
                    //add element : men add 2 gange?
                    searchResult.add(swimmer);
                }

                //printer hvert element på sin index plads
                for (int i = 0; i < searchResult.size(); i++)
                    System.out.println(i + 1 + ":" + searchResult.get(i));

            }

        }
        return searchResult;
    }
}
