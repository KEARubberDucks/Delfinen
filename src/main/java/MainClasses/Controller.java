package MainClasses;
import Comparators.AgeComparator;
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
    AgeComparator ageComparator;
    public Controller() {

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
        ageComparator = new AgeComparator();
    }

    private void mainLoop() throws FileNotFoundException {
        int choice = 0;
        ui.welcome();
        while (shouldRun) {
            ui.mainMenu();
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                switch (choice){
                    case 1 -> createSwimmer();
                    case 2 -> cashierMenu();
                    //Todo: Dette skal fjernes og gøres automatisk, hvis der har været ændringer i filen (se Superhero projekt Controller.java l. 132)
                    case 3 ->{

                        fileHandler.saveSvømmer(database.getSwimmers());
                    }
                    case 4 -> deleteSwimmer();
                    case 5 -> coachMenu();
                    case 6 -> editSwimmer();
                    case 9 -> shouldRun = false;
                }
            } else {
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
    private void deleteSwimmer(){
        //boolean loop end value loop slutter ikke indtil det bliver sat til true
        //initialize de forskellige variabler jeg benytter
        boolean loopEndValue = false;
        int indexDelete = 0;
        Swimmer swimmerDelete = null;
        while(!loopEndValue){
            //signal enum vælg svømmer
            ui.signalMessage(Signals.CHOOSE_SWIMMER);
            //udskriv alle svømmerne i ui
            ui.printSwimmers(database.getSwimmers());
            try{
                //scanner nextInt i try/catch signal enum for ugyldigt input
                indexDelete = sc.nextInt();
            } catch (InputMismatchException mismatchException){
                ui.signalMessage(Signals.INVALID_INPUT);
            }
            try {
                //fetch svømmeren i database -> bruger valgte index -1 da array starter på 0 ikke 1
                swimmerDelete = database.getSwimmers().get(indexDelete - 1);
                //slutter loop
                loopEndValue = true;
            } catch (IndexOutOfBoundsException outOfBoundsException){

                loopEndValue = false;
            }
        }
        //database metode sletter den svømmer der blev hentet tidligere
        database.deleteSwimmer(swimmerDelete);
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
                        System.out.println("");
                        change = sc.nextLine();
                    } catch (NumberFormatException e){
                        ui.signalMessage(Signals.INCORRECT_VARIABLE_TYPE);
                        intSet = false;
                        System.out.println("");
                        change = sc.nextLine();
                    }
                }
                break;

            case 3:
                boolean changeSet = false;
                while (!changeSet) {
                    switch (change) {
                        case ("ja"):
                            SwimmerToEdit.setActive(true);
                            changeSet = true;
                            break;
                        case ("nej"):
                            SwimmerToEdit.setActive(false);
                            changeSet = true;
                            break;
                        default:
                            ui.signalMessage(Signals.INCORRECT_INPUT_BOOLEAN);
                            change = sc.nextLine();
                            break;
                    }
                }
                break;

            case 4:
                boolean changeSet2 = false;
                while (!changeSet2) {
                    switch (change) {
                        case ("ja"):
                            SwimmerToEdit.setCompetetiv(true);
                            changeSet2 = true;
                            break;
                        case ("nej"):
                            SwimmerToEdit.setCompetetiv(false);
                            changeSet2 = true;
                            break;
                        default:
                            ui.signalMessage(Signals.INCORRECT_INPUT_BOOLEAN);
                            change = sc.nextLine();
                            break;
                    }
                }
                break;

            default:
                ui.signalMessage(Signals.INCORRECT_INPUT);
        }

    }
}

