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
        file = new File("Resources/Svømmer.txt");
    }
    //Svømmere skal med lille s
    public void saveSwimmers(ArrayList<Swimmer> Svømmere)throws FileNotFoundException{
        output = new PrintStream(file);
        if (!Svømmere.isEmpty()){
            for (Swimmer svømmer : Svømmere){
                output.println(svømmer.getName()+ "; "+svømmer.getAge()+ "; " + svømmer.getisActive()+ "; " +svømmer.getIsCompetetiv());
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
                    attributeList[2].equals("true"),
                    attributeList[3].equals("true")

            );
            returnList.add(svømmerToAdd);
        }
        return returnList;
    }
}
