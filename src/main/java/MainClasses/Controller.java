package MainClasses;
import Enums.Signals;
import FileAndDatabase.Database;
import FileAndDatabase.FileHandler;

import javax.xml.crypto.Data;
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
    public Controller(){
        sc = new Scanner(System.in);
        ui = new UserInterface();
        fileHandler = new FileHandler();
        database = new Database();
    }
    public void startProgram() throws FileNotFoundException {
        shouldRun = true;
        ArrayList<Swimmer> swimmers = new ArrayList<>();
        swimmers.add(new Swimmer("Test",21,false,true));
        swimmers.add(new Swimmer("tore",100,false,true));
        database.initSwimmers(swimmers);
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
                    //Todo: Dette skal fjernes og gøres automatisk, hvis der har været ændringer i filen (se Superhero projekt Controller.java l. 131)
                    case 3 ->{

                        fileHandler.saveSvømmer(database.getSwimmers());
                    }
                    case 4 -> deleteSwimmer();
                    case 9 -> shouldRun = false;
                }
            }
            else {
                ui.signalMessage(Signals.NOT_A_NUMBER);
                sc.nextLine();
            }
        }
    }
    private void deleteSwimmer(){
        boolean loopEndValue = false;
        int indexDelete = 0;
        Swimmer swimmerDelete = null;
        while(!loopEndValue){
            ui.signalMessage(Signals.CHOOSE_SWIMMER);
            ui.printSwimmers(database.getSwimmers());
            try{
                indexDelete = sc.nextInt();
            } catch (InputMismatchException mismatchException){
                ui.signalMessage(Signals.INVALID_INPUT);
            }
            try {
                swimmerDelete = database.getSwimmers().get(indexDelete - 1);
                loopEndValue = true;
            } catch (IndexOutOfBoundsException outOfBoundsException){

                loopEndValue = false;
            }
        }
        database.deleteSwimmer(swimmerDelete);
    }
    private void coachMenu() {
        ui.signalMessage(Signals.NOT_IMPLEMENTED);
    }
    private void cashierMenu() {
        ui.signalMessage(Signals.NOT_IMPLEMENTED);
    }

}
