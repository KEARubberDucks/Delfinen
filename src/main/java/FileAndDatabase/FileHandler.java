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
    PrintStream output;
    Scanner input;
    public FileHandler(){
        file = new File("Resources/Svømmer.txt");
    }
    //Swimmere skal med lille s
    public void saveSwimmers(ArrayList<Swimmer> Swimmere)throws FileNotFoundException{
        output = new PrintStream(file);
        if (!Swimmere.isEmpty()){
            for (Swimmer swimmer : Swimmere){
                    if (swimmer instanceof CompetitiveSwimmer){
                        output.println(swimmer.getName()+ "; "+swimmer.getAge()+ "; " + swimmer.getIsActive()+ "; " + swimmer.getIsCompetitive()+ "; " +
                                        swimmer.getSwimmingDisciplines()+ "; " + swimmer.getBestResult()+ "; " + swimmer.getDateOfResult()+ "; " +
                                swimmer.getCompetitionOfResults()+ "; " + swimmer.getPlaceOfResult()+ "; " + swimmer.getTrainer());
                    } else {
                        output.println(swimmer.getName()+ "; "+swimmer.getAge()+ "; " + swimmer.getIsActive()+ "; " + swimmer.getIsCompetitive());
                    }
            }
        }
    }
    public ArrayList<Swimmer> loadSvømmer() throws FileNotFoundException{
        ArrayList<Swimmer> returnList = new ArrayList<>();
        Swimmer svømmerToAdd;
        input = new Scanner(file);
        while (input.hasNextLine()){
            String[] attributeList = input.nextLine().split("; ");
            svømmerToAdd = new Swimmer(
                    attributeList[0],
                    Integer.parseInt(attributeList[1]),
                    attributeList[2].equals("ja"),
                    attributeList[3].equals("ja")

            );
            returnList.add(svømmerToAdd);
        }
        return returnList;
    }
}
