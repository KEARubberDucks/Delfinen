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
import java.util.Scanner;
import java.util.Date;

public class UserInterface {

    public UserInterface(){
    }

    public void welcome() {
        System.out.println("Velkommen til administrative system!");
    }

    public void mainMenu() {
        System.out.print("Vælg en mulighed: \n" +
                "1: Opret ny Svømmer\n" +
                "2: Kassør menu\n" +
                "3: Slette en svømmer\n" +
                "4: Træner menu\n" +
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
                "Aldersgruppe: %s\n" +
                "Betalt: %s\n",
                (index + 1), swimmer.getName(), swimmer.getAge(), swimmer.getIsActive(), swimmer.getIsCompetitive(), swimmer.getAgeGroup(), swimmer.getHasPaid());
        if (swimmer instanceof CompetitiveSwimmer)
            System.out.printf("Træner: %s\n" +
                    "Disciplin: %s\n", ((CompetitiveSwimmer) swimmer).getCoachName(), ((CompetitiveSwimmer) swimmer).getDisciplines().toString().toLowerCase());
        System.out.println("--------------- \n");
    }

    public void signalMessage(Signals signal) {
        switch (signal){
            case ASK_FOR_NAME -> System.out.println("indtast svømmerens navn");
            case ASK_FOR_AGE -> System.out.println("indtast svømmernes alder");
            case ASK_IF_SWIMMER_ACTIVE ->  System.out.println("Er svømmeren aktiv ja eller nej");
            case ASK_IF_SWIMMER_COMPETITIVE -> System.out.println("Er svømmeren competitiv? ja eller nej");
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
            case USERS_PAID -> System.out.print("antal svømmere der har betalt: ");
            case AMOUNT_PAID -> System.out.print("mængde betalt: ");
            case USERS_MISSING_PAYMENT -> System.out.print("antal svømmere der mangler at betale: ");
            case AMOUNT_PAY_MISSING -> System.out.print("mængde manglende betalinger: ");
            case CURRENCY -> System.out.println(" kr. ");
            case CONFIRMED_SWIMMER_CHOOSEN -> System.out.println("svømmer valgt");
            case PROMPT_YES_NO -> System.out.println("Hvad vil du ændre det til ja/nej");
            case MISSING_PAYERS -> System.out.println("Disse svømmere mangler at betal");
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
            case BEST_SWIMMER -> null;
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
    public void printCreateSwimmerText(){
        System.out.println("opret svømmer!");
    }
    public void printSwimmers(ArrayList<Swimmer> swimmers) {
        System.out.println("liste af svømmere");
        for (Swimmer swimmer : swimmers) {
            System.out.println(" ");
            System.out.println((swimmers.indexOf(swimmer) + 1) + "----------");
            System.out.println("svømmers navn: " + swimmer.getName());
            System.out.println("svømmers age: " + swimmer.getAge());
            System.out.println("er kompetetiv: " + swimmer.getIsCompetitive());
            System.out.println("----------");
        }
    }

    public void coachMenu() {
        System.out.println("Vælg venligst en funktion:\n" +
                "1: Se en liste over alle svømmere\n" +
                "2: Indtast et resultat\n" +
                "3: Se en liste over top 5 svømmere inden for en disciplin (ikke implementeret endnu)\n" +
                "4: Tilbage");
    }

    void ChooseGroupOfSwimmers(ArrayList<Swimmer> swimmers){
        for (Swimmer swimmer : swimmers){
            if (swimmer instanceof CompetitiveSwimmer){
                printDisciplin((CompetitiveSwimmer) swimmer, swimmers.indexOf(swimmer));
            }

        }
    }
    public void printDisciplin(CompetitiveSwimmer swimmer, int index){

        System.out.println("Svømmer id: " + (index+1));
        System.out.println("Navn: " + swimmer.getName());
        System.out.println("Alder:" + swimmer.getAge());
        System.out.println("Svømme disciplin: " + swimmer.getResults().get(swimmer.getResults().size() - 1).getDiscipline());
        System.out.println("Beste tid: " + swimmer.getResults().get(swimmer.getResults().size() - 1).getTimeInSeconds() + " sekunder");
        System.out.println("Beste tid's dato: " + swimmer.getResults().get(swimmer.getResults().size() - 1).getDate());
        System.out.println("Beste placering: " + swimmer.getResults().get(swimmer.getResults().size() - 1).getPlace());
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

    public Discipline getDiscipline(Scanner sc) {
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
    public void cashierMenu(){
        System.out.print("1: oversigt\n" +
                "2: betaling \n" +
                "3: manglende betalere \n");
    }
    public void printSwimmersNoSort(ArrayList<Swimmer> swimmers) {
        // for each loop der printer alle svømmerne i arrayet
        for (Swimmer swimmer : swimmers){
            printSwimmer(swimmer, swimmers.indexOf(swimmer));
        }
    }
}
