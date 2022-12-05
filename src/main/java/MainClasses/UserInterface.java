package MainClasses;

import Enums.Signals;
import Enums.SortOption;

import java.util.ArrayList;
import java.util.Comparator;

public class UserInterface {

    public UserInterface(){
    }

    public void welcome() {
        System.out.println("Velkommen til administrative system!");
    }

    public void mainMenu() {
        System.out.print("Vælg en mulighed: \n" +
                "1: Opret ny Svømmer\n" +
                "2: Se oplysninger om kontigentbetalinger (ikke implementeret)\n" +
                "3: Slette en svømmer\n" +
                "4: Se oplysninger om klubbens svømmer\n" +
                "5: Redigere i svømmere\n" +
                "9: Afslut\n"
        );
    }

    public void printSwimmer(Swimmer swimmer, int index){
        System.out.printf(
                "Svømmer id: %d \n" +
                "Navn: %s\n" +
                "Alder: %d\n" +
                "Aktiv: %s\n" +
                "Competetiv: %s\n" +
                "Aldersgruppe: %s\n" ,(index + 1), swimmer.getName(), swimmer.getAge(), swimmer.getIsActive(), swimmer.getIsCompetitive(), swimmer.getAgeGroup());
        if (swimmer instanceof CompetitiveSwimmer)
            System.out.printf("Træner: %s\n" +
                    "Disciplin: %s\n", ((CompetitiveSwimmer) swimmer).getCoachName(), ((CompetitiveSwimmer) swimmer).getDisciplines().toString().toLowerCase());
        System.out.println("--------------- \n");

    }

    public void signalMessage(Signals signal) {
        switch (signal){
            case NOT_A_NUMBER -> System.out.println("Indtast venligst et nummer");
            case NOT_IMPLEMENTED -> System.out.println("Denne funktionalitet er ikke implementeret endnu");
            case INCORRECT_INPUT -> System.out.println("Kunne ikke genkende input");
            case CHOOSE_SWIMMMER -> System.out.println("vælg en svømmer");
            case CHOOSE_EDIT_OPTION -> System.out.println("vælg en mulighed til redigere");
            case ASK_FOR_EDIT -> System.out.println("Hvad vil du ændre det til");
            case INCORRECT_INPUT_BOOLEAN -> System.out.println("vælg mellem ja eller nej");
            case INCORRECT_VARIABLE_TYPE -> System.out.print("Ikke korrekt input, skriv venligst et tal");
            case CHOOSE_SWIMMER -> System.out.println("Indtast svømmer id på den svømmer du gerne vil slette");
            case INVALID_INPUT -> System.out.println("ugyldigt input");
            default -> System.out.println("HurrDurr, dette skal ikke kunne findes blah, ret dine enums");
        }
    }

    public void printSwimmers(ArrayList<Swimmer> swimmers, SortOption sortOption, Comparator<Swimmer> comparator) {
        // for each loop der printer alle svømmerne i arrayet
        System.out.println("Svømmere sorteret efter " + parseSortOption(sortOption));
        swimmers.sort(comparator);
        for (Swimmer swimmer : swimmers){
            printSwimmer(swimmer, swimmers.indexOf(swimmer));
        }
        System.out.println("Skriv \"Sorter\" for at sortere efter en anden parameter, eller \"Tilbage\" for at gå tilbage.");
    }

    public void swimmerInformation(){
        System.out.print("1: Navn \n" +
                "2: alder \n" +
                "3: er activ \n" +
                "4: svømmer kompetetiv\n");
    }

    public String parseSortOption(SortOption option){
        return switch (option){
            case NAME -> "navn";
            case AGE -> "alder";
            case IS_ACTIVE -> "om de er aktive";
            case IS_COMPETITIVE -> "om de er competitive";
        };
    }

    public void chooseSortOption() {
        System.out.println("Vælg en af følgende sorterings parametre");
        int i = 0;
        for (SortOption option : SortOption.values()) {
            i++;
            System.out.printf("%d: %s\n", i, parseSortOption(option));
        }
    }
}
