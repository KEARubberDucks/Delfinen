package MainClasses;

import Enums.Discipline;

import java.util.ArrayList;

public class CompetitiveSwimmer extends Swimmer{
    private String coachName;
    private Discipline discipline;

    public CompetitiveSwimmer(String name, int age, boolean isActive, boolean competitive, String coachName, Discipline discipline) {
        super(name, age, isActive, competitive);
        this.coachName = coachName;
        this.discipline = discipline;
    }

    public String getCoachName() {
        return coachName;
    }

    public Discipline getDiscipline() {
        return discipline;
    }
    @Override
    void setType(int age) {
        type = age < 18 ? Type.JUNIOR : Type.SENIOR;
    }

    @Override
    public String toString(){
        return super.toString() + "; " + getCoachName() + "; " + getDiscipline();
    }

}
