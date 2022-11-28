package Comparators;

import MainClasses.Swimmer;
import java.util.Comparator;

public class TypeComparator implements Comparator<Swimmer> {

    @Override
    public int compare(Swimmer o1, Swimmer o2){
        return Boolean.compare(o1.getIsCompetetiv(), o2.getIsCompetetiv());
    }
}
