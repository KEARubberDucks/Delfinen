package MainClasses;

import Enums.Discipline;

public class CompetitiveSwimmer extends Swimmer{
    private String coachName;
    private Discipline[] disciplines;

    public CompetitiveSwimmer(String name, int age, boolean isActive, boolean competitive, String coachName, Discipline[] disciplines) {
        super(name, age, isActive, competitive);
        this.coachName = coachName;
        this.disciplines = disciplines;
    }

    public String getCoachName() {
        return coachName;
    }

    public String getDisciplines() {
        String returnString = "";
        for (int i = 0; i < disciplines.length - 1; i++) {
            returnString += disciplines[i] + ", ";
        }
        returnString += (disciplines[disciplines.length-1]);
        return returnString.toString();
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
