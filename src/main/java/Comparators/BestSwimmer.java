package Comparators;

import Swimmers.CompetitiveResult;
import Swimmers.CompetitiveSwimmer;
import Swimmers.Swimmer;

import java.util.Comparator;


public class BestSwimmer implements Comparator<CompetitiveSwimmer> {
    @Override
    public int compare(CompetitiveSwimmer o1, CompetitiveSwimmer o2) {
        int result = 0;
        if (o1.getResults().size() < 1 && o2.getResults().size() < 1){
            int o1min = o1.getResults().get(0).getTimeInSeconds();
            for(int i = 1; i <  o1.getResults().size(); i++){
                if (o1min > o1.getResults().get(i).getTimeInSeconds()){
                    o1min = o1.getResults().get(i).getTimeInSeconds();
                }
            }
            int o2min = o2.getResults().get(0).getTimeInSeconds();
            for(int i = 1; i <  o2.getResults().size(); i++){
                if (o2min > o2.getResults().get(i).getTimeInSeconds()){
                    o2min = o2.getResults().get(i).getTimeInSeconds();
                }
            }
            result = o1min - o2min;
        } else {
            result = o1.getResults().get(0).getTimeInSeconds() - o2.getResults().get(0).getTimeInSeconds();

        }
        return result;
    }
}
