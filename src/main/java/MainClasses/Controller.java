package MainClasses;
import Enums.Signals;
import FileAndDatabase.FileHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

public class Controller {
    boolean shouldRun;
    Scanner sc;
    UserInterface ui;
    FileHandler fileHandler;
    public Controller(){
        sc = new Scanner(System.in);
        ui = new UserInterface();
        fileHandler = new FileHandler();
    }
    public void startProgram() throws FileNotFoundException {
        shouldRun = true;
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
                    case 1 -> coachMenu();
                    case 2 -> cashierMenu();
                    case 3 ->{

                        ArrayList<Swimmer> svømmere = new ArrayList<>();
                        svømmere.add(new Swimmer("tore",100,false,true));
                        svømmere.add(new Swimmer("adam",21,false,true));
                        fileHandler.saveSvømmer(svømmere);
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
        ui.signalMessage(Signals.NOT_IMPLEMENTED);
    }
    private void cashierMenu() {
        ui.signalMessage(Signals.NOT_IMPLEMENTED);
    }

}
