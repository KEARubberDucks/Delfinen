package MainClasses;

import Enums.Signals;

import java.util.ArrayList;

public class UserInterface {

    public UserInterface(){
    }

    public void welcome() {
        System.out.println("Velkommen til administrative system!");
    }

    public void mainMenu() {
        System.out.print("Vælg en mulighed: \n" +
                "1: Se oplysninger om klubbens svømmmere\n" +
                "2: Se oplysninger om kontigentbetalinger (ikke implementeret)\n" +
                "4: Slette en svømmer\n" +
                "5: Se oplysninger om klubbens svømmer\n" +
                "9: Afslut\n"
        );
    }

    public void printSwimmer(Swimmer swimmer, int index){
        System.out.printf(
                "Svømmer id: %d \n" +
                "Navn: %s\n" +
                "Alder: %d\n" +
                "competetiv: %s\n" +
                "seniorstatus: %s\n" +
                "--------------- \n",
                (index + 1), swimmer.getName(), swimmer.getAge(), swimmer.isCompetetiv(), swimmer.isActive());
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
            case INCORRECT_VARIABLE_TYPE -> System.out.printf("Ikke korrekt input, skriv venligst et tal");
            case CHOOSE_SWIMMER -> System.out.println("Indtast svømmer id på den svømmer du gerne vil slette");
            case INVALID_INPUT -> System.out.println("ugyldigt input");
            default -> System.out.println("HurrDurr, dette skal ikke kunne findes blah, ret dine enums");
        }
    }

    public void printSwimmers(ArrayList<Swimmer> swimmers) {
        // for each loop der printer alle svømmerne i arrayet
        for (Swimmer swimmer : swimmers){
            printSwimmer(swimmer, swimmers.indexOf(swimmer));
        }
    }

    public void swimmerinfomation(){
        System.out.printf("1: Navn \n" +
                "2: alder \n" +
                "3: er activ \n" +
                "4: svømmer kompetetiv\n");
    }
}
