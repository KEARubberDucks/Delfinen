package FileAndDatabase;
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
        file = new File("Resources/Swimmer.txt");
    }
    //Swimmers skal med lille s
    public void saveSwimmers(ArrayList<Swimmer> Swimmers)throws FileNotFoundException{
        output = new PrintStream(file);
        if (!Swimmers.isEmpty()){
            for (Swimmer swimmer : Swimmers){
                output.println(swimmer.getName()+ "; "+swimmer.getAge()+ "; " + swimmer.getIsActive()+ "; " +swimmer.getIsCompetitive());
            }
        }
    }
    public ArrayList<Swimmer> loadSwimmer() throws FileNotFoundException{
        ArrayList<Swimmer> returnList = new ArrayList<>();
        Swimmer swimmerToAdd;
        input = new Scanner(file);
        while (input.hasNextLine()){
            String[] attributeList = input.nextLine().split("; ");
            swimmerToAdd = new Swimmer(
                    attributeList[0],
                    Integer.parseInt(attributeList[1]),
                    attributeList[2].equals("ja"),
                    attributeList[3].equals("ja")

            );
            returnList.add(swimmerToAdd);
        }
        return returnList;
    }
}
