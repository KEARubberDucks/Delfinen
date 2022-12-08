package Comparators;

import Swimmers.Swimmer;

import java.util.Comparator;

public class AgeComparator implements Comparator<Swimmer> {
    @Override
    public int compare(Swimmer o1, Swimmer o2) {
        return o1.getAge() - o2.getAge();
    }
}
