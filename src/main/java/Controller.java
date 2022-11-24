import java.util.Scanner;

public class Controller {
    boolean shouldRun;
    Scanner sc;
    UserInterface ui;
    public Controller(){
        sc = new Scanner(System.in);
        ui = new UserInterface();
    }
    public void startProgram() {
        shouldRun = true;
        mainLoop();
    }

    private void mainLoop() {
        int choice = 0;
        ui.welcome();
        while(shouldRun){
            ui.mainMenu();
            if (sc.hasNextInt()){
                choice = sc.nextInt();
                switch (choice){
                    case 1 -> opretSvømmer();
                    case 2 -> cashierMenu();
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
                case "ja","j":
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
        Svømmer.createSvømmer(name, age, isActive, competetiv);
    }

}
