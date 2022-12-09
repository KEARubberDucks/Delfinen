package Comparators;

import Swimmers.CompetitiveSwimmer;
import Swimmers.Swimmer;

import java.util.Comparator;


public class BestSwimmer implements Comparator<CompetitiveSwimmer> {
    @Override
    public int compare(CompetitiveSwimmer o1, CompetitiveSwimmer o2) {
        return o1.getResults().get(0).getTimeInSeconds() - o2.getResults().get(0).getTimeInSeconds();
    }
}
