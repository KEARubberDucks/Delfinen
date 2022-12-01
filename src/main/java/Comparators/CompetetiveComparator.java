package Comparators;

import MainClasses.Swimmer;

import java.util.Comparator;

public class CompetetiveComparator implements Comparator<Swimmer>{
    @Override
    public int compare(Swimmer o1, Swimmer o2) {
        return o1.getIsCompetitive().compareTo(o2.getIsCompetitive());
    }
}
