package Comparators;

import MainClasses.Swimmer;
import java.util.Comparator;

public class TypeComparator implements Comparator<Swimmer> {

    @Override
    public int compare(Swimmer o1, Swimmer o2){
        return o1.isCompetetiv().compareTo(o2.isCompetetiv());

    }
}
