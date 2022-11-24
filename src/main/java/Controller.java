import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    boolean shouldRun;
    Scanner sc;
    UserInterface ui;
    Svømmer sv;

    public Controller(){
        sc = new Scanner(System.in);
        ui = new UserInterface();
        sv = new Svømmer(null, 0, false, false);
    }
    public void startProgram() {
        sv.createTestData();
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
                    case 3 -> editSvimmer();
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

    public void editSvimmer(){
        System.out.println("Search for a hero to start edit");
        String searchTerm = sc.next();

        // opret arrayliste til søgeresultater
        ArrayList<Svømmer> searchResult = sv.searchAndEdit(searchTerm);

        sv.searchAndEdit(searchTerm);

        //System.out.println(searchResult.size());
    }
}
