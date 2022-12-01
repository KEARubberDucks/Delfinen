package MainClasses;

public class CompetitiveSwimmer extends Swimmer {

    private String swimmingDisciplines;
    private int bestResult;
    private String dateOfResult;
    private String competitionOfResults;
    private String placeOfResult;
    private String trainer;

    public CompetitiveSwimmer(String name, int age, boolean isActive, boolean competetiv, String swimmingDisciplines, int bestResult,
                               String dateOfResult, String competitionOfResults, String placeOfResult, String trainer) {

        super(name, age, isActive, competetiv);

        this.swimmingDisciplines = swimmingDisciplines;
        this.bestResult = bestResult;
        this.dateOfResult = dateOfResult;
        this.competitionOfResults = competitionOfResults;
        this.placeOfResult = placeOfResult;
        this.trainer = trainer;
    }

    public String getSwimmingDisciplines() {
        return swimmingDisciplines;
    }

    public int getBestResult() {
        return bestResult;
    }

    public String getDateOfResult() {
        return dateOfResult;
    }

    public String getCompetitionOfResults() {
        return competitionOfResults;
    }

    public String getPlaceOfResult() {
        return placeOfResult;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setSwimmingDisciplines(String swimmingDisciplines) {
        this.swimmingDisciplines = swimmingDisciplines;
    }

    public void setBestResult(int bestResult) {
        this.bestResult = bestResult;
    }

    public void setDateOfResult(String dateOfResult) {
        this.dateOfResult = dateOfResult;
    }

    public void setCompetitionOfResults(String competitionOfResults) {
        this.competitionOfResults = competitionOfResults;
    }

    public void setPlaceOfResult(String placeOfResult) {
        this.placeOfResult = placeOfResult;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
}
