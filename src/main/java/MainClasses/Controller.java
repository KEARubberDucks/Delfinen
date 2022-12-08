package MainClasses;

import Comparators.AgeComparator;
import Comparators.CompetitiveComparator;
import Comparators.IsActiveComparator;
import Comparators.NameComparator;

import Enums.Discipline;
import Enums.Signals;
import Enums.SortOption;

import FileAndDatabase.Database;
import FileAndDatabase.FileHandler;
import Swimmers.CompetitiveSwimmer;
import Payments.Payment;
import Swimmers.Swimmer;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;


public class Controller {
    private boolean shouldRun;
    private Scanner sc;
    private UserInterface ui;
    private FileHandler fileHandler;
    private Database database;
    private AgeComparator ageComparator;
    private CompetitiveComparator competitiveComparator;
    private IsActiveComparator isActiveComparator;
    private NameComparator nameComparator;
    private SortOption sortingBy;
    private Payment payment;

    public Controller() {
        sc = new Scanner(System.in);
        ui = new UserInterface();
        fileHandler = new FileHandler();
        database = new Database();
        ageComparator = new AgeComparator();
        competitiveComparator = new CompetitiveComparator();
        isActiveComparator = new IsActiveComparator();
        nameComparator = new NameComparator();
        sortingBy = SortOption.NAME;
        payment = new Payment();

    }

    public void startProgram() throws FileNotFoundException, ParseException {
        ArrayList<Swimmer> swimmers = fileHandler.loadSwimmer();
        database.initSwimmers(swimmers);
        shouldRun = true;
        if (0 < database.getCurrentYear()){
            payment.setSwimmersNotPaid(database.getSwimmers());
        }
        mainLoop();
    }

    private void mainLoop() throws FileNotFoundException, ParseException {
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

    private void coachMenu() throws ParseException {
        boolean inMenu = true;
        while(inMenu){
            ui.coachMenu();
            if (sc.hasNextInt()){
                int menuItem = sc.nextInt();
                switch (menuItem){
                    case 1 -> printSwimmers();
                    case 2 -> createCompetitiveResult();
                    case 3 -> ui.signalMessage(Signals.NOT_IMPLEMENTED);
                    case 4 -> inMenu = false;
                    default -> ui.signalMessage(Signals.INVALID_INPUT);
                }
            }
        }
    }

    private void createCompetitiveResult() throws ParseException {
        Swimmer swimmer =  chooseSwimmer();
        if (swimmer.getIsCompetitive().equals("ja")){
            ui.createResult(sc, (CompetitiveSwimmer) swimmer);
        } else ui.signalMessage(Signals.SWIMMER_NOT_COMPETITIVE);
    }

    private void printSwimmers() {
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

    private Comparator<Swimmer> getComparator() {
        return switch (sortingBy) {
            case AGE -> ageComparator;
            case IS_COMPETITIVE -> competitiveComparator;
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
                    swimmerPaying.setHasPaid(true);
                    database.unsavedChangesTrue();
                    loopEndValue=true;
                    break;
                case "nej", "n":
                    swimmerPaying.setHasPaid(false);
                    database.unsavedChangesTrue();
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
        Scanner scanner = new Scanner(System.in);
        boolean answered = false;
        String name = "";
        int age = 0;
        boolean isActive = false;
        boolean competitive = false;
        boolean havePaid = false;
        ui.printCreateSwimmerText();
        ui.signalMessage(Signals.ASK_FOR_NAME);
        name = scanner.nextLine();
        ui.signalMessage(Signals.ASK_FOR_AGE);
        while (!answered) {
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                answered = true;
            } else {
                ui.signalMessage(Signals.NOT_A_NUMBER);
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        answered = false;
        while (!answered) {
            ui.signalMessage(Signals.ASK_IF_SWIMMER_ACTIVE);
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j" -> {
                    isActive = true;
                    answered = true;
                }
                case "nej", "n" -> {
                    answered = true;
                }
                default -> ui.signalMessage(Signals.INCORRECT_INPUT_BOOLEAN);
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
                    answered = true;
                }
                default -> System.out.println("Indtast ja eller nej. inputtet er ikke korrekt");
            }
        }
        answered = false;
        while (!answered) {
            ui.signalMessage(Signals.ASK_IF_SWIMMER_COMPETITIVE);
            switch (scanner.nextLine().toLowerCase()) {
                case "ja", "j" -> {
                    competitive = true;
                    System.out.println("Hvad hedder svømmerens træner?");
                    String coachName = sc.nextLine();
                    ArrayList<Discipline> disciplines = ui.getDisciplineChoices(scanner);
                    database.createSwimmer(name, age, isActive, competitive, havePaid,coachName, disciplines);
                    answered = true;
                }
                case "nej", "n" -> {
                    competitive = false;
                    answered = true;
                    database.createSwimmer(name, age, isActive, competitive, havePaid);
                }
                default -> ui.signalMessage(Signals.INCORRECT_INPUT_BOOLEAN);
            }
        }
    }

    private Swimmer chooseSwimmer() {
        boolean swimmerChosen = false;
        int indexHeroToEdit = 0;
        Swimmer swimmerToDelete = null;
        while (!swimmerChosen) {
            ui.signalMessage(Signals.CHOOSE_SWIMMMER);
            ui.printSwimmers(database.getSwimmers());
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

