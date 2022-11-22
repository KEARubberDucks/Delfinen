public class UserInterface {

    public UserInterface(){
    }

    public void welcome() {
        System.out.println("Velkommen til administrative system!");
    }

    public void mainMenu() {
        System.out.print("Vælg en mulighed: \n" +
                "1: Se oplysninger om klubbens svømmmere (ikke implementeret)\n" +
                "2: Se oplysninger om kontigentbetalinger (ikke implementeret)\n"
        );
    }

    public void signalMessage(Signals signal) {
        switch (signal){
            case NOT_A_NUMBER -> System.out.println("Indtast venligst et nummer");
            case NOT_IMPLEMENTED -> System.out.println("Denne funktionalitet er ikke implementeret endnu");
            default -> System.out.println("HurrDurr, dette skal ikke kunne findes blah, ret dine enums");
        }
    }
}
