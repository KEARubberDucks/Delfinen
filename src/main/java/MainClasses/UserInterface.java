package MainClasses;

import Enums.Signals;

public class UserInterface {

    public UserInterface() {
    }

    public void welcome() {
        System.out.println("Velkommen til administrative system!");
    }

    public void mainMenu() {
        System.out.print("Vælg en mulighed: \n" +
                "1: Se oplysninger om klubbens svømmmere (ikke implementeret)\n" +
                "2: Se oplysninger om kontigentbetalinger (ikke implementeret)\n" +
                "9: Afslut\n"
        );
    }

    public void signalMessage(Signals signal) {
        switch (signal) {
            case NOT_A_NUMBER -> System.out.println("Indtast venligst et nummer");
            case NOT_IMPLEMENTED -> System.out.println("Denne funktionalitet er ikke implementeret endnu");
            case INCORRECT_INPUT -> System.out.println("Kunne ikke genkende input");
            case CHOOSE_SWIMMMER -> System.out.println("vælg en svømmer");
            case CHOOSE_EDIT_OPTION -> System.out.println("vælg en mulighed til redigere");
            case ASK_FOR_EDIT -> System.out.println("Hvad vil du ændre det til");
            case INCORRECT_INPUT_BOOLEAN -> System.out.println("vælg mellem ja eller nej");
            case INCORRECT_VARIABLE_TYPE -> System.out.printf("Ikke korrekt input, skriv venligst et tal");
            default -> System.out.println("HurrDurr, dette skal ikke kunne findes blah, ret dine enums");
        }
    }

    public void swimmerinfomation(){
        System.out.printf("1: Navn \n" +
                "2: alder \n" +
                "3: er activ \n" +
                "4: svømmer kompetetiv\n");
    }
}
