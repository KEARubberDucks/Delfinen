package Comparators;

import MainClasses.Swimmer;

import java.util.Comparator;

public class IsActiveComparator implements Comparator<Swimmer> {
    @Override
        public int compare(Swimmer o1, Swimmer o2) {
            return o1.isActive().compareTo(o2.isActive());
        }
    }
