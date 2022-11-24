package MainClasses;
import Enums.Signals;
import FileAndDatabase.Database;
import FileAndDatabase.FileHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    boolean shouldRun;
    Scanner sc;
    UserInterface ui;
    FileHandler fileHandler;
    Database database;
    public Controller(){
        sc = new Scanner(System.in);
        ui = new UserInterface();
        fileHandler = new FileHandler();
        database = new Database();
    }
    public void startProgram() throws FileNotFoundException {
        shouldRun = true;
        ArrayList<Swimmer> swimmers = new ArrayList<>();
        swimmers.add(new Swimmer("juniortest",18,false,true));
        swimmers.add(new Swimmer("Test",21,false,true));
        swimmers.add(new Swimmer("seniortest",60,false,true));
        swimmers.add(new Swimmer("tore",100,false,true));
        database.initSwimmers(swimmers);
        mainLoop();
    }

    private void mainLoop() throws FileNotFoundException {
        int choice = 0;
        ui.welcome();
        while(shouldRun){
            ui.mainMenu();
            if (sc.hasNextInt()){
                choice = sc.nextInt();
                switch (choice){
                    case 1 -> createSwimmer();
                    case 2 -> cashierMenu();
                    //Todo: Dette skal fjernes og gøres automatisk, hvis der har været ændringer i filen (se Superhero projekt Controller.java l. 132)
                    case 3 ->{

                        fileHandler.saveSvømmer(database.getSwimmers());
                    }
                    case 9 -> shouldRun = false;
                }
            }
            else {
                ui.signalMessage(Signals.NOT_A_NUMBER);
                sc.nextLine();
            }
        }
    }

    private void coachMenu() {
        ui.printSwimmers(database.getSwimmers());
    }
    private void cashierMenu() {
        ui.signalMessage(Signals.NOT_IMPLEMENTED);
    }
    public void createSwimmer() {
        Scanner scanner = new Scanner(System.in);
        boolean answered = false;
        String name = "";
        int age = 0;
        boolean isActive = false;
        boolean competetiv = false;
        System.out.println("opret svømmer!");
        System.out.println("indtast svømmerens navn");
        name = scanner.next();
        System.out.println("indtast svømmernes alder");
        age = scanner.nextInt();
        while (!answered) {
            System.out.println("Er svømmeren aktiv ja eller nej");
            switch (scanner.nextLine()) {
                case "ja, j, Ja":
                    isActive = true;
                    answered = true;
                    break;
                case "nej, n , Nej":
                    isActive = false;
                    answered = true;
                    break;
                default:
                    System.out.println("indtast ja eller nej. inputtet er ikke korrekt");
            }
        }
        answered = false;
        while (!answered) {
            System.out.println("er svømmeren competitiv? ja eller nej");
            switch (scanner.nextLine()) {
                case "ja, j, Ja":
                    competetiv = true;
                    answered = true;
                    break;
                case "nej, n , Nej":
                    competetiv = false;
                    answered = true;
                    break;
                default:
                    System.out.println("indtast ja eller nej. inputtet er ikke korrekt");

            }
        }
        database.createSvømmer(name, age, isActive, competetiv);
    }

}
