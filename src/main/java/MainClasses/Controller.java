package MainClasses;
import Enums.Signals;
import FileAndDatabase.Database;
import FileAndDatabase.FileHandler;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

public class Controller {
    boolean shouldRun;
    Scanner sc;
    UserInterface ui;
    FileHandler fileHandler;
    Database database;

    public Controller() {
        sc = new Scanner(System.in);
        ui = new UserInterface();
        fileHandler = new FileHandler();
        database = new Database();
    }

    public void startProgram() throws FileNotFoundException {
        shouldRun = true;
        ArrayList<Swimmer> swimmers = new ArrayList<>();
        swimmers.add(new Swimmer("Test", 21, false, true));
        swimmers.add(new Swimmer("tore", 100, false, true));
        database.initSwimmers(swimmers);
        mainLoop();
    }

    private void mainLoop() throws FileNotFoundException {
        int choice = 0;
        ui.welcome();
        while (shouldRun) {
            ui.mainMenu();
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> opretSvømmer();
                    case 2 -> cashierMenu();
                    //Todo: Dette skal fjernes og gøres automatisk, hvis der har været ændringer i filen (se Superhero projekt Controller.java l. 131)
                    case 3 -> {

                        fileHandler.saveSvømmer(database.getSwimmers());
                    }
                    case 9 -> shouldRun = false;
                }
            } else {
                ui.signalMessage(Signals.NOT_A_NUMBER);
                sc.nextLine();
            }
        }
    }

    private void coachMenu() {
        ui.signalMessage(Signals.NOT_IMPLEMENTED);
    }

    private void cashierMenu() {
        ui.signalMessage(Signals.NOT_IMPLEMENTED);
    }

    public void opretSvømmer() {
        Scanner scanner = new Scanner(System.in);
        boolean answered = false;
        String name = "";
        int age = 0;
        boolean isActive = false;
        boolean competetiv = false;
        System.out.println("opret svømmer!");
        System.out.println("indtast svømmerens navn");
        name = scanner.nextLine();
        System.out.println("indtast svømmernes alder");
        while (!answered) {
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                answered = true;
            }
            else {
                System.out.println("dette er ikke et tal");
                answered = false;
                scanner.nextLine();


            }
        }
            scanner.nextLine();
            while (!answered) {
                System.out.println("Er svømmeren aktiv ja eller nej");
                switch (scanner.nextLine().toLowerCase()) {
                    case "ja", "j":
                        isActive = true;
                        answered = true;
                        break;
                    case "nej", "n":
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
                switch (scanner.nextLine().toLowerCase()) {
                    case "ja", "j":
                        competetiv = true;
                        answered = true;
                        break;
                    case "nej", "n":
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

