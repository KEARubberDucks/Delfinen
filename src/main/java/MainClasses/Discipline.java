package MainClasses;

import java.util.ArrayList;

public class Discipline {
    private String name;
    private ArrayList<Swimmer> swimmers;
    private ArrayList<Integer> times;

    public Discipline(String name) {
        this.name = name;
    }

    public void addSwimmer(Swimmer swimmer, Integer time){
        swimmers.add(swimmer);
        times.add(time);
    }

    public void addSwimmers(ArrayList<Swimmer> swimmers, ArrayList<Integer> times){
        this.swimmers.addAll(swimmers);
        this.times.addAll(times);
    }

    public ArrayList<Swimmer> getSwimmers() {
        return swimmers;
    }
    //TODO: Lav en klasse der håndterer hver "løb", så man kan lave en liste af tuples med svømmere og den klasse
    //TODO: Klassen skal have en int til tid i sekunder, dato (evt med javas indbyggede klasse)

    //TODO: evt. også en konkurrence class, der holder styr på stævne, placering of tid.
}
