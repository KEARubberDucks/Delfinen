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
                "1: Se oplysninger om klubbens svømmmere (ikke implementeret)\n" +
                "2: Se oplysninger om kontigentbetalinger (ikke implementeret)\n" +
                "9: Afslut\n"
        );
    }

    public void signalMessage(Signals signal) {
        switch (signal){
            case NOT_A_NUMBER -> System.out.println("Indtast venligst et nummer");
            case NOT_IMPLEMENTED -> System.out.println("Denne funktionalitet er ikke implementeret endnu");
            default -> System.out.println("HurrDurr, dette skal ikke kunne findes blah, ret dine enums");
        }
    }
    public void printsvimmer(Swimmer swimmer, int index){
        System.out.printf("Svimmer nr %d: \n" +
                "Navn: %s \n" +
                "Age: %s \n" +
                "Active: %s \n" +
                "Competetive: %s \n\n",
                (index + 1), swimmer.getName(), swimmer.

    }
    public void printSvimmers(ArrayList<Swimmer> svimmers){
        for (Swimmer swim : svimmers){

        }
    }
}
