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
                    case 4 -> editSwimmer();
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
        //Swimmer.createSvømmer(name, age, isActive, competetiv);
    }

    private Swimmer swimmerChosen() {
        boolean swimmerChosen = false;
        int indexHeroToEdit = 0;
        Swimmer swimmerToDelete = null;
        while (!swimmerChosen) {
            ui.signalMessage(Signals.CHOOSE_SWIMMMER);
            database.printHero();
            try {
                indexHeroToEdit = sc.nextInt();
            } catch (InputMismatchException IME) {
                ui.signalMessage(Signals.INCORRECT_INPUT);
            }
            sc.nextLine();
            try {
                swimmerToDelete = database.getSwimmers().get(indexHeroToEdit - 1);
                swimmerChosen = true;
            } catch (IndexOutOfBoundsException IOBE) {
                //ui.chooseNumberInRange(database.getSwimmers().size());
                swimmerChosen = false;
            }
        }
        return swimmerToDelete;
    }

    private void editSwimmer() {
        Swimmer SwimmerToEdit = swimmerChosen();
        ui.signalMessage(Signals.CHOOSE_EDIT_OPTION);
        boolean attributeChosen = false;
        int menuItem = 0;
        while (!attributeChosen) {
            ui.swimmerinfomation();
            try {
                menuItem = sc.nextInt();
                attributeChosen = true;
            } catch (InputMismatchException IME) {
                attributeChosen = false;
                ui.signalMessage(Signals.INCORRECT_INPUT);
            }
            sc.nextLine();
        }
        ui.signalMessage(Signals.ASK_FOR_EDIT);
        String change = sc.nextLine();
        switch (menuItem) {
            case 1:
                SwimmerToEdit.setName(change);
                break;
            case 2:
                boolean intSet = false;
                while (!intSet) {
                    try {
                        SwimmerToEdit.setAge(Integer.parseInt(change));
                        intSet = true;
                    } catch (InputMismatchException IME) {
                        ui.signalMessage(Signals.INCORRECT_VARIABLE_TYPE);
                        intSet = false;
                    }
                }
                break;

            case 3:
                boolean changeSet = false;
                while (!changeSet) {
                    switch (change) {
                        case ("j"):
                            SwimmerToEdit.setActive(true);
                            changeSet = true;
                            break;
                        case ("n"):
                            SwimmerToEdit.setActive(false);
                            changeSet = true;
                            break;
                        default:
                            ui.signalMessage(Signals.INCORRECT_INPUT_BOOLEAN);
                            break;
                    }
                }
                break;

            case 4:
                boolean changeSet2 = false;
                while (!changeSet2) {
                    switch (change) {
                        case ("j"):
                            SwimmerToEdit.setCompetetiv(true);
                            changeSet2 = true;
                            break;
                        case ("n"):
                            SwimmerToEdit.setCompetetiv(false);
                            changeSet2 = true;
                            break;
                        default:
                            ui.signalMessage(Signals.INCORRECT_INPUT_BOOLEAN);
                    }
                }
                break;

            default:
                ui.signalMessage(Signals.INCORRECT_INPUT);
        }

    }
}

