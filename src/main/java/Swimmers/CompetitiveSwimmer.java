package Swimmers;

import Enums.Discipline;
import Swimmers.CompetitiveResult;

import java.util.ArrayList;
import java.util.Date;

public class CompetitiveSwimmer extends Swimmer {
    private String coachName;
    private ArrayList<Discipline> disciplines;
    private ArrayList<CompetitiveResult> results;

    public CompetitiveSwimmer(String name, int age, boolean isActive, boolean competitive, boolean hasPaid, String coachName, ArrayList<Discipline> disciplines) {
        super(name, age, isActive, competitive, hasPaid);
        this.coachName = coachName;
        this.disciplines = disciplines;
        this.results = new ArrayList<>();
    }

    public ArrayList<CompetitiveResult> getResults() {
        return results;
    }

    public String getCoachName() {
        return coachName;
    }

    public void createCompetitiveResult(int timeInSeconds, Date date, String place, Discipline discipline){
        if (!disciplines.contains(discipline)){
            disciplines.add(discipline);
        }
        results.add(new CompetitiveResult(timeInSeconds, date, place, discipline));
    }

    public String getDisciplinesString(){
        String disciplinesToReturn = String.valueOf(disciplines.get(0));
        return disciplinesToReturn;
    }

    public String getDisciplines() {
        String returnString = "";
        for (int i = 0; i < disciplines.size() - 1; i++) {
            returnString += disciplines.get(i) + ", ";
        }
        returnString += (disciplines.get(disciplines.size() - 1));
        return returnString;
    }
    @Override
    void setType(int age) {
        type = age < 18 ? Type.JUNIOR : Type.SENIOR;
    }

    @Override
    public String toString(){
        return super.toString() + "; " + getCoachName() + "; " + getDisciplines();
    }

}
