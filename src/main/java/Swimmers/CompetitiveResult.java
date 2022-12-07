package Swimmers;

import Enums.Discipline;

import java.util.Date;

public class CompetitiveResult {
    private Discipline discipline;
    private int timeInSeconds;
    private Date date;
    private String place;

    public CompetitiveResult(int timeInSeconds, Date date, String place, Discipline discipline) {
        this.timeInSeconds = timeInSeconds;
        this.date = date;
        this.place = place;
        this.discipline = discipline;
    }
}
