package MainClasses;
import Comparators.AgeComparator;
import Comparators.CompetetiveComparator;
import Comparators.IsActiveComparator;
import Comparators.NameComparator;

import Enums.Discipline;
import Enums.Signals;
import Enums.SortOption;

import FileAndDatabase.Database;
import FileAndDatabase.FileHandler;
import Payments.Payment;
import Swimmers.Swimmer;

import java.io.FileNotFoundException;
import java.util.*;


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
    Payment payment;

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
        payment = new Payment();

    }

    public void startProgram() throws FileNotFoundException {
        ArrayList<Swimmer> swimmers = fileHandler.loadSvømmer();
        database.initSwimmers(swimmers);
        shouldRun = true;
        if (0 < database.getCurrentYear()){
            payment.setSwimmersNotPaid(database.getSwimmers());
        }
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
                    case 9 ->{
                        if (database.hasUnsavedChanges()){
                            fileHandler.saveSwimmers(database.getSwimmers());
                            fileHandler.saveYear(database.getCurrentYear());
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
        while(inMenu){
            ui.printSwimmers(database.getSwimmers(), sortingBy, getComparator());
            String userChoice = sc.nextLine();
            switch (userChoice.trim().toLowerCase()){
                case "sorter" -> sorterListeMenu();
                case "tilbage" -> inMenu = false;
                default -> ui.signalMessage(Signals.INCORRECT_INPUT);
            }
        }
    }

    private Comparator getComparator() {
        return switch (sortingBy){
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
        }
        else {
            ui.signalMessage(Signals.NOT_A_NUMBER);
            return;
        }
        switch (userChoice){
            case 1 -> sortingBy = SortOption.values()[0];
            case 2 -> sortingBy = SortOption.values()[1];
            case 3 -> sortingBy = SortOption.values()[2];
            case 4 -> sortingBy = SortOption.values()[3];
            default -> ui.signalMessage(Signals.INVALID_INPUT);
        }
    }

    private void cashierMenu() {
        ui.cashierMenu();
        int userChoice = 0;
        if (sc.hasNextInt()) {
            userChoice = sc.nextInt();
            sc.nextLine();
        }
        else {
            ui.signalMessage(Signals.NOT_A_NUMBER);
            return;
        }
        switch (userChoice){
            case 1 -> expectedPayments();
            case 2 -> swimmerPayment();
            case 3 -> missingPayers();
            default -> ui.signalMessage(Signals.INVALID_INPUT);
        }
    }

    private void expectedPayments(){
        //income
        ui.signalMessage(Signals.USERS_PAID);
        System.out.println(payment.swimmersPaid(database.getSwimmers()));
        ui.signalMessage(Signals.AMOUNT_PAID);
        System.out.print(payment.swimmersMembershipIncome(database.getSwimmers()));
        ui.signalMessage(Signals.CURRENCY);

        //debt
        ui.signalMessage(Signals.USERS_MISSING_PAYMENT);
        System.out.println(payment.swimmersNotPaid(database.getSwimmers()));
        ui.signalMessage(Signals.AMOUNT_PAY_MISSING);
        System.out.print(payment.swimmersMembershipDebt(database.getSwimmers()));
        ui.signalMessage(Signals.CURRENCY);
    }
    private void missingPayers() {
        boolean loopEndValue = true;
        while (loopEndValue) {
            ui.signalMessage(Signals.MISSING_PAYERS);
            ui.printSwimmers(payment.getMissingPayers(database.getSwimmers()), sortingBy, getComparator());
            String userChoice = sc.nextLine();
            switch (userChoice.trim().toLowerCase()) {
                case "sorter" -> sorterListeMenu();
                case "tilbage" -> loopEndValue = false;
                default -> ui.signalMessage(Signals.INCORRECT_INPUT);
            }
        }
    }
    private void swimmerPayment(){
        boolean loopEndValue = false;
        Swimmer swimmerPaying = choosePayer();
        ui.signalMessage(Signals.CONFIRMED_SWIMMER_CHOOSEN);
        ui.printSwimmer(swimmerPaying, 0);
        while (!loopEndValue) {
            ui.signalMessage(Signals.PROMPT_YES_NO);
            String userInput = sc.nextLine();
            switch (userInput.toLowerCase()) {
                case "ja", "j":
                    database.swimmerPayment(swimmerPaying, true);
                    loopEndValue=true;
                    break;
                case "nej", "n":
                    database.swimmerPayment(swimmerPaying, false);
                    loopEndValue=true;
                    break;
                default:
                    ui.signalMessage(Signals.INCORRECT_INPUT_BOOLEAN);
            }
        }
    }
    private Swimmer choosePayer() {
        boolean loopEndValue = false;
        int indexPayer = 0;
        Swimmer swimmerPaying = null;
        while (!loopEndValue) {
            ui.signalMessage(Signals.CHOOSE_SWIMMMER);
            ui.printSwimmersNoSort(database.getSwimmers());
            try {
                indexPayer = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException IME) {
                ui.signalMessage(Signals.INCORRECT_INPUT);
            }
            try {
                swimmerPaying = database.getSwimmers().get(indexPayer - 1);
                loopEndValue = true;
            } catch (IndexOutOfBoundsException IOBE) {
                loopEndValue = false;
            }
        }
        return swimmerPaying;
    }
    private void deleteSwimmer() {
        //boolean loopEndValue loop slutter ikke indtil det bliver sat til true
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
        //TODO: Alt system.out her skal refaktorisers til UI klassen
        Scanner scanner = new Scanner(System.in);
        boolean answered = false;
        String name = "";
        int age = 0;
        boolean isActive = false;
        boolean competetiv = false;
        boolean havePaid = false;
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
                    System.out.println("Indtast ja eller nej. Inputtet er ikke korrekt");
            }
        }
        answered = false;
        while (!answered) {
            System.out.println("Har svømmeren betalt? ja eller nej");
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j"->{
                    havePaid = true;
                    answered = true;
                }
                case "nej", "n"->{
                    havePaid = false;
                    answered = true;
                }
                default -> System.out.println("Indtast ja eller nej. inputtet er ikke korrekt");
            }
        }
        answered = false;
        while (!answered) {
            System.out.println("Er svømmeren competitiv? ja eller nej");
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j"->{
                    competetiv = true;
                    System.out.println("Hvad hedder svømmerens træner?");
                    String coachName = sc.nextLine();
                    System.out.println("Hvilke(n) disciplin(er) udøver svømmeren? (Vælg med kommasepererede tal fra 1-4)");
                    System.out.println("""
                            1: BUTTERFLY
                            2: CRAWL
                            3: RYGCRAWL
                            4: BRYSTSVMØMNING""");
                    String[] choices = sc.nextLine().split(",".trim());
                    Discipline[] disciplines = getDisciplinesFromChoices(choices);
                    database.createSwimmer(name, age, isActive, competetiv, havePaid, coachName, disciplines);
                    answered = true;
                }
                case "nej", "n"->{
                    competetiv = false;
                    answered = true;
                    database.createSwimmer(name, age, isActive, competetiv, havePaid);
                }
                default -> System.out.println("Indtast ja eller nej. inputtet er ikke korrekt");
            }
        }
    }

    private Discipline[] getDisciplinesFromChoices(String[] choices) {
        Discipline[] returnArray = new Discipline[choices.length];
        for (int i = 0; i < choices.length; i++) {
            returnArray[i] = switch (choices[i].trim()){
                case "1" -> Discipline.BUTTERFLY;
                case "2" -> Discipline.CRAWL;
                case "3" -> Discipline.RYGCRAWL;
                case "4" -> Discipline.BRYSTSVMØMNING;
                default -> null;
            };
        }
        return returnArray;
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
        //TODO: Der er en fejl her hvor man skal dobbelt trykke på "Enter" for at den opfanger ens valg
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
                        //TODO: Her skal vi håndtere at svømmeren skal gøres kompetitiv hvis det ændres fra nej til ja, og omvendt
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

