package Comparators;

import MainClasses.CompetitiveSwimmer;
import MainClasses.Swimmer;

import java.util.Comparator;


public class BestSwimmer implements Comparator<Swimmer> {
    @Override
    public int compare(Swimmer o1, Swimmer o2) {
        return o1.getCompetitionOfResults() - o2.getCompetitionOfResults();
    }
}
