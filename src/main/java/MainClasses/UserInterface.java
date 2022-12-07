package MainClasses;

import Enums.Discipline;
import Enums.Signals;
import Enums.SortOption;
import Swimmers.CompetitiveSwimmer;
import Swimmers.Swimmer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

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
        System.out.println(switch (signal){
            case NOT_A_NUMBER -> "Indtast venligst et nummer";
            case NOT_IMPLEMENTED -> "Denne funktionalitet er ikke implementeret endnu";
            case INCORRECT_INPUT -> "Kunne ikke genkende input";
            case CHOOSE_SWIMMMER -> "vælg en svømmer";
            case CHOOSE_EDIT_OPTION -> "vælg en mulighed til redigere";
            case ASK_FOR_EDIT -> "Hvad vil du ændre det til";
            case INCORRECT_INPUT_BOOLEAN -> "vælg mellem ja eller nej";
            case INCORRECT_VARIABLE_TYPE -> "Ikke korrekt input, skriv venligst et tal";
            case CHOOSE_SWIMMER -> "Indtast svømmer id på den svømmer du gerne vil slette";
            case INVALID_INPUT -> "ugyldigt input";
            case SWIMMER_NOT_COMPETITIVE -> "Denne svømmer er ikke kompetitiv";
        });
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

    public void coachMenu() {
        System.out.println("Vælg venligst en funktion:\n" +
                "1: Se en liste over alle svømmere\n" +
                "2: Indtast et resultat\n" +
                "3: Se en liste over top 5 svømmere inden for en disciplin\n" +
                "4: Tilbage");
    }

    public void createResult(Scanner sc, CompetitiveSwimmer swimmer) throws ParseException {
        boolean inMenu = true;
        int time = 0;
        while(inMenu) {
            System.out.println("Hvor lang tid i sekunder varede ræset?");
            if (sc.hasNextInt()) {
                time = sc.nextInt();
                inMenu = false;
            } else {
                signalMessage(Signals.NOT_A_NUMBER);
            }
            sc.nextLine();
        }
        inMenu = true;
        Date date = new Date();
        while(inMenu){
            System.out.println("Hvornår var ræset? (datoformat dd/MM/yyyy)");
            String input = sc.nextLine();
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(input);
                inMenu = false;
            } catch (ParseException e) {
                signalMessage(Signals.INVALID_INPUT);
            }
        }
        System.out.println("Indtast stedet for ræset");
        String place = sc.nextLine();
        Discipline discipline = getDiscipline(sc);
        swimmer.createCompetitiveResult(time, date, place, discipline);
    }

    private Discipline getDiscipline(Scanner sc) {
        boolean inMenu = true;
        int choice = 0;
        while(inMenu) {
            System.out.println("Hvilken disciplin?");
            System.out.println("""
                    1: BUTTERFLY
                    2: CRAWL
                    3: RYGCRAWL
                    4: BRYSTSVØMNING""");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                inMenu = false;
            } else {
                signalMessage(Signals.NOT_A_NUMBER);
            }
            sc.nextLine();
        }
        String[] choiceArray = new String[1];
        choiceArray[0] = String.valueOf(choice);
        ArrayList<Discipline> disciplines = getDisciplinesFromChoices(choiceArray);
        return disciplines.get(0);
    }

    public ArrayList<Discipline> getDisciplineChoices(Scanner sc) {
        System.out.println("Hvilke(n) disciplin(er) udøver svømmeren? (Vælg med kommasepererede tal fra 1-4)");
        System.out.println("""
                1: BUTTERFLY
                2: CRAWL
                3: RYGCRAWL
                4: BRYSTSVØMNING""");
        String[] choices = sc.nextLine().split(",".trim());
        ArrayList<Discipline> disciplines = getDisciplinesFromChoices(choices);
        return disciplines;
    }

    private ArrayList<Discipline> getDisciplinesFromChoices(String[] choices) {
        ArrayList<Discipline>  returnArray = new ArrayList<>();
        for (String choice : choices) {
            returnArray.add(switch (choice.trim()) {
                case "1" -> Discipline.BUTTERFLY;
                case "2" -> Discipline.CRAWL;
                case "3" -> Discipline.RYGCRAWL;
                case "4" -> Discipline.BRYSTSVØMNING;
                default -> null;
            });
        }
        return returnArray;
    }
}
