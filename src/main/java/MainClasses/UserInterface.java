package MainClasses;

import Enums.Signals;
import Enums.SortOption;

import java.util.ArrayList;
import java.util.Comparator;
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
                "Aldersgruppe: %s\n" +
                "--------------- \n",
                (index + 1), swimmer.getName(), swimmer.getAge(), swimmer.getIsActive(), swimmer.getIsCompetitive(), swimmer.getAgeGroup());
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






    }

