package FileAndDatabase;

import MainClasses.CompetitiveSwimmer;
import MainClasses.Swimmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandler {
    File file;
    File file2;
    PrintStream output;
    PrintStream output2;
    Scanner input;

    public FileHandler() {
        file = new File("Resources/Svømmer.txt");
        file2 = new File("Resources/CompetitiveSvømmer.txt");
    }

    //Swimmere skal med lille s
    public void saveSwimmers(ArrayList<Swimmer> Swimmere) throws FileNotFoundException {
        output = new PrintStream(file);
        output2 = new PrintStream(file2);
        if (!Swimmere.isEmpty()) {
            for (Swimmer swimmer : Swimmere) {
                if (swimmer instanceof CompetitiveSwimmer) {
                    output2.println(swimmer.getName() + "; " + swimmer.getAge() + "; " + swimmer.getIsActive() + "; " + swimmer.getIsCompetitive() + "; " +
                            swimmer.getSwimmingDisciplines() + "; " + swimmer.getBestResult() + "; " + swimmer.getDateOfResult() + "; " +
                            swimmer.getCompetitionOfResults() + "; " + swimmer.getPlaceOfResult() + "; " + swimmer.getTrainer());
                } else {
                    output.println(swimmer.getName() + "; " + swimmer.getAge() + "; " + swimmer.getIsActive() + "; " + swimmer.getIsCompetitive());
                }
            }
        }
    }

    public ArrayList<Swimmer> loadSvømmer() throws FileNotFoundException {
        ArrayList<Swimmer> returnList = new ArrayList<>();
        Swimmer swimmerToAdd;
        input = new Scanner(file);
        while (input.hasNextLine()) {
            String[] attributeList = input.nextLine().split("; ");

            swimmerToAdd = new Swimmer(
                    attributeList[0],
                    Integer.parseInt(attributeList[1]),
                    attributeList[2].equals("ja"),
                    attributeList[3].equals("ja")
            );
            returnList.add(swimmerToAdd);
        }
        input = new Scanner(file2);
        while (input.hasNextLine()) {
            String[] attributeList = input.nextLine().split("; ");
            swimmerToAdd = new CompetitiveSwimmer(
                    attributeList[0],
                    Integer.parseInt(attributeList[1]),
                    attributeList[2].equals("ja"),
                    attributeList[3].equals("ja"),
                    attributeList[4],
                    attributeList[5],
                    attributeList[6],
                    Integer.parseInt(attributeList[7]),
                    attributeList[8],
                    attributeList[9]
            );
            returnList.add(swimmerToAdd);
        }
        return returnList;
    }
}
