package MainClasses;

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
                    case 1 -> coachMenu();
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

}
