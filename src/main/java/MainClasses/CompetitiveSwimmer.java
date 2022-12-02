package MainClasses;

import java.util.ArrayList;

public class CompetitiveSwimmer extends Swimmer{
    private String coachName;

    public CompetitiveSwimmer(String name, int age, boolean isActive, boolean competitive) {
        super(name, age, isActive, competitive);
    }
    void setType(int age) {
        type = age < 18 ? Type.JUNIOR : Type.SENIOR;
    }
}
