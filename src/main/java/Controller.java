import java.util.Scanner;

public class Controller {
    boolean shouldRun;
    Scanner sc;
    UserInterface ui;
    public Controller(){
        shouldRun = true;
        sc = new Scanner(System.in);
        ui = new UserInterface();
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
                    case 1 -> coachMenu();
                    case 2 -> cashierMenu();
                }
            }
            else ui.signalMessage(Signals.NOT_A_NUMBER);

        }
    }
    private void coachMenu() {
        ui.signalMessage(Signals.NOT_IMPLEMENTED);
    }
    private void cashierMenu() {
        ui.signalMessage(Signals.NOT_IMPLEMENTED);
    }
}
