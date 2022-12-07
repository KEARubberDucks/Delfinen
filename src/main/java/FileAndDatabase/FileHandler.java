package FileAndDatabase;
import Enums.Discipline;
import Swimmers.CompetitiveSwimmer;
import Swimmers.Swimmer;

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
                output.println(svømmer);
            }
        }
    }
    public ArrayList<Swimmer> loadSvømmer() throws FileNotFoundException{
        ArrayList<Swimmer> returnList = new ArrayList<>();
        Swimmer svømmerToAdd;
        input = new Scanner(file);
        while (input.hasNextLine()){
            String[] attributeList = input.nextLine().split("; ");
            if (attributeList[3].equals("ja"))
                svømmerToAdd = new CompetitiveSwimmer(
                    attributeList[0],
                    Integer.parseInt(attributeList[1]),
                    attributeList[2].equals("ja"),
                    true, attributeList[4],
                     getDiscipline(attributeList[5])
                );
            else
                svømmerToAdd = new Swimmer(
                        attributeList[0],
                        Integer.parseInt(attributeList[1]),
                        attributeList[2].equals("ja"),
                        false

                );
            returnList.add(svømmerToAdd);
        }
        return returnList;
    }

    private ArrayList<Discipline> getDiscipline(String s) {
        String[] disciplines = s.split(", ");
        ArrayList<Discipline> returnArray = new ArrayList<>();
        for (int i = 0; i < disciplines.length; i++){
            returnArray.add( switch (disciplines[i]) {
                case "BUTTERFLY" -> Discipline.BUTTERFLY;
                case "CRAWL" -> Discipline.CRAWL;
                case "RYGCRAWL" -> Discipline.RYGCRAWL;
                case "BRYSTSVMØMNING" -> Discipline.BRYSTSVØMNING;
                default -> null;
            });
        }
        return returnArray;
    }
}
