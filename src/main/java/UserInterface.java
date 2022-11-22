public class UserInterface {

    public UserInterface(){
    }

    public void welcome() {
        System.out.println("Velkommen til administrative system!");
        //todo: slet dette
        System.out.println("DETTE ER FRA TESTBRANCH, HVIS DETTE ENDER I MASTER BRANCH VIRKER DET AGGGHHHHHH SLET NÅR FÆRDIGT");
    }

    public void mainMenu() {
        System.out.print("Vælg en mulighed: \n" +
                "1: Se oplysninger om klubbens svømmmere (ikke implementeret)\n" +
                "2: Se oplysninger om kontigentbetalinger (ikke implementeret)\n" +
                "9: Afslut\n"
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
