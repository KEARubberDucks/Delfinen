package MainClasses;

import Comparators.AgeComparator;
import Comparators.CompetetiveComparator;
import Comparators.IsActiveComparator;
import Comparators.NameComparator;

import Enums.Signals;
import Enums.SortOption;

import FileAndDatabase.Database;
import FileAndDatabase.FileHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Controller {
    boolean shouldRun;
    Scanner sc;
    UserInterface ui;
    FileHandler fileHandler;
    Database database;

    AgeComparator ageComparator;
    CompetetiveComparator competetiveComparator;
    IsActiveComparator isActiveComparator;
    NameComparator nameComparator;
    SortOption sortingBy;

    public Controller() {
        sc = new Scanner(System.in);
        ui = new UserInterface();
        fileHandler = new FileHandler();
        database = new Database();
        ageComparator = new AgeComparator();
        competetiveComparator = new CompetetiveComparator();
        isActiveComparator = new IsActiveComparator();
        nameComparator = new NameComparator();
        sortingBy = SortOption.NAME;
    }

    public void startProgram() throws FileNotFoundException {
        shouldRun = true;
        ArrayList<Swimmer> swimmers = fileHandler.loadSvømmer();
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
                sc.nextLine();
                switch (choice) {
                    case 1 -> createSwimmer();
                    case 2 -> cashierMenu();
                    case 3 -> deleteSwimmer();
                    case 4 -> coachMenu();
                    case 5 -> editSwimmer();
                    case 9 -> {
                        if (database.hasUnsavedChanges()) {
                            fileHandler.saveSwimmers(database.getSwimmers());
                        }
                        shouldRun = false;
                    }
                }
            } else {
                ui.signalMessage(Signals.NOT_A_NUMBER);
                sc.nextLine();
            }
        }
    }

    private void coachMenu() {
        boolean inMenu = true;
        while (inMenu) {
            ui.printSwimmers(database.getSwimmers(), sortingBy, getComparator());
            String userChoice = sc.nextLine();
            switch (userChoice.trim().toLowerCase()) {
                case "sorter" -> sorterListeMenu();
                case "tilbage" -> inMenu = false;
                default -> ui.signalMessage(Signals.INCORRECT_INPUT);
            }
        }
    }

    private Comparator getComparator() {
        return switch (sortingBy) {
            case AGE -> ageComparator;
            case IS_COMPETITIVE -> competetiveComparator;
            case IS_ACTIVE -> isActiveComparator;
            case NAME -> nameComparator;
        };
    }

    private void sorterListeMenu() {
        ui.chooseSortOption();
        int userChoice = 0;
        if (sc.hasNextInt()) {
            userChoice = sc.nextInt();
            sc.nextLine();
        } else {
            ui.signalMessage(Signals.NOT_A_NUMBER);
            return;
        }
        switch (userChoice) {
            case 1 -> sortingBy = SortOption.values()[0];
            case 2 -> sortingBy = SortOption.values()[1];
            case 3 -> sortingBy = SortOption.values()[2];
            case 4 -> sortingBy = SortOption.values()[3];
            default -> ui.signalMessage(Signals.INVALID_INPUT);
        }
    }

    private void cashierMenu() {
        ui.signalMessage(Signals.NOT_IMPLEMENTED);
    }

    private void deleteSwimmer() {
        //boolean loop end value loop slutter ikke indtil det bliver sat til true
        //initialize de forskellige variabler jeg benytter
        boolean loopEndValue = false;
        int indexDelete = 0;
        Swimmer swimmerDelete = null;
        while (!loopEndValue) {
            //signal enum vælg svømmer
            ui.signalMessage(Signals.CHOOSE_SWIMMER);
            //udskriv alle svømmerne i ui
            ui.printSwimmers(database.getSwimmers(), sortingBy, getComparator());
            try {
                //scanner nextInt i try/catch signal enum for ugyldigt input
                indexDelete = sc.nextInt();
            } catch (InputMismatchException mismatchException) {
                ui.signalMessage(Signals.INVALID_INPUT);
            }
            try {
                //fetch svømmeren i database -> bruger valgte index -1 da array starter på 0 ikke 1
                swimmerDelete = database.getSwimmers().get(indexDelete - 1);
                //slutter loop
                loopEndValue = true;
            } catch (IndexOutOfBoundsException outOfBoundsException) {

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

        String swimmingDisciplines = "";
        String bestResult = "";
        String dateOfResult = "";
        int competitionOfResults = 0;
        String placeOfResult = "";
        String trainer = "";

        System.out.println("opret svømmer!");
        System.out.println("indtast svømmerens navn");
        name = scanner.nextLine();
        System.out.println("indtast svømmernes alder");
        while (!answered) {
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                answered = true;
            } else {
                System.out.println("dette er ikke et tal");
                answered = false;
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        answered = false;
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
                    System.out.println("Indtast ja eller nej. inputtet er ikke korrekt");
            }
        }
        answered = false;
        while (!answered) {
            System.out.println("Er svømmeren competitiv? ja eller nej");
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j" -> {
                    competetiv = true;
                    answered = true;
                }
                case "nej", "n" -> {
                    competetiv = false;
                    answered = true;
                }
                default -> System.out.println("Indtast ja eller nej. inputtet er ikke korrekt");

            }
        }
        if (competetiv == true) {
            answered = false;
            while (!answered) {
                System.out.println("hvad er deres svømmedisciplin?");
                ui.printSwimmingDisciplines();
                switch (scanner.nextLine().toLowerCase()) {
                    case "1":
                        swimmingDisciplines = "Butterfly";
                        answered = true;
                        break;
                    case "2":
                        swimmingDisciplines = "Crawl";
                        answered = true;
                        break;
                    case "3":
                        swimmingDisciplines = "Rygcrawl";
                        answered = true;
                        break;
                    case "4":
                        swimmingDisciplines = "Brystsvømning";
                        answered = true;
                        break;
                }
            }
            System.out.println("indtast svømmernes beste rank inden for deres diciplin");
            answered = true;
            while (answered) {
                if (scanner.hasNextInt()) {
                    competitionOfResults = scanner.nextInt();
                    answered = false;
                } else {
                    System.out.println("dette er ikke et tal");
                    answered = true;
                    scanner.nextLine();
                }
            }
            System.out.println("indtast datoen for deres beste resultat");
            dateOfResult = scanner.nextLine();
            if(dateOfResult == ""){
                System.out.println("Indtast den dato hvor svømmeren fik deres beste resultat");
                dateOfResult = scanner.nextLine();
            }
            System.out.println("indtast svømmerens beste tid inden for deres diciplin");
            bestResult = scanner.nextLine();
            System.out.println("hvor fik svømeren deres beste tid");
            placeOfResult = scanner.nextLine();
            System.out.println("hvem er deres træner");
            trainer = scanner.nextLine();

            database.createSwimmer(name, age, isActive, competetiv, swimmingDisciplines, bestResult, dateOfResult, competitionOfResults, placeOfResult, trainer);
        } else {
            database.createSwimmer(name, age, isActive, competetiv);
        }
    }

    private Swimmer chooseSwimmer() {
        boolean swimmerChosen = false;
        int indexHeroToEdit = 0;
        Swimmer swimmerToDelete = null;
        while (!swimmerChosen) {
            ui.signalMessage(Signals.CHOOSE_SWIMMMER);
            database.printHeroes();
            try {
                indexHeroToEdit = sc.nextInt();
                sc.nextLine();
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
        Swimmer SwimmerToEdit = chooseSwimmer();
        ui.signalMessage(Signals.CHOOSE_EDIT_OPTION);
        boolean attributeChosen = false;
        int menuItem = 0;
        while (!attributeChosen) {
            ui.swimmerInformation();
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
                    } catch (InputMismatchException | NumberFormatException IME) {
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
                        case ("ja") -> {
                            SwimmerToEdit.setActive(true);
                            changeSet = true;
                        }
                        case ("nej") -> {
                            SwimmerToEdit.setActive(false);
                            changeSet = true;
                        }
                        default -> {
                            ui.signalMessage(Signals.INCORRECT_INPUT_BOOLEAN);
                            change = sc.nextLine();
                        }
                    }
                }
                break;

            case 4:
                boolean changeSet2 = false;
                while (!changeSet2) {
                    switch (change) {
                        case ("ja") -> {
                            SwimmerToEdit.setCompetitive(true);
                            changeSet2 = true;
                        }
                        case ("nej") -> {
                            SwimmerToEdit.setCompetitive(false);
                            changeSet2 = true;
                        }
                        default -> {
                            ui.signalMessage(Signals.INCORRECT_INPUT_BOOLEAN);
                            change = sc.nextLine();
                        }
                    }
                }
                break;

            default:
                ui.signalMessage(Signals.INCORRECT_INPUT);
        }
    }
}

