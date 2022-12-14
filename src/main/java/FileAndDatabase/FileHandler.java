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
    private File file;
    private File file2;
    private PrintStream output;
    private Scanner input;
    public FileHandler(){
        file = new File("Resources/Swimmer.txt");
        file2 = new File("Resources/currentYear.txt");
    }
    public void saveYear(int currentYear)throws FileNotFoundException{
        output = new PrintStream(file2);
        output.println(currentYear);
    }
    public int loadYear() throws FileNotFoundException{
        int currentYear;
        input = new Scanner(file2);
        currentYear = Integer.parseInt(input.nextLine());
        return currentYear;
    }
    //Swimmers skal med lille s
    public void saveSwimmers(ArrayList<Swimmer> Swimmers)throws FileNotFoundException{
        output = new PrintStream(file);
        if (!Swimmers.isEmpty()){
            for (Swimmer swimmer : Swimmers){
                output.println(swimmer);
            }
        }
    }
    public ArrayList<Swimmer> loadSwimmer() throws FileNotFoundException{
        ArrayList<Swimmer> returnList = new ArrayList<>();
        Swimmer swimmerToAdd;
        input = new Scanner(file);
        while (input.hasNextLine()){
            String[] attributeList = input.nextLine().split("; ");
            if (attributeList[3].equals("ja"))
                swimmerToAdd = new CompetitiveSwimmer(
                        attributeList[0],
                        Integer.parseInt(attributeList[1]),
                        attributeList[2].equals("ja"),
                        true,
                        attributeList[4].equals("ja"),
                        attributeList[5],
                        getDiscipline(attributeList[6])
                );
            else
                swimmerToAdd = new Swimmer(
                        attributeList[0],
                        Integer.parseInt(attributeList[1]),
                        attributeList[2].equals("ja"),
                        false,
                        attributeList[4].equals("ja")
                );
            returnList.add(swimmerToAdd);
        }
        return returnList;
    }

    private ArrayList<Discipline> getDiscipline(String s) {
        String[] disciplines = s.split(", ");
        ArrayList<Discipline> returnArray = new ArrayList<>();
        for (String discipline : disciplines) {
            returnArray.add(switch (discipline) {
                case "BUTTERFLY" -> Discipline.BUTTERFLY;
                case "CRAWL" -> Discipline.CRAWL;
                case "RYGCRAWL" -> Discipline.RYGCRAWL;
                case "BRYSTSV??MNING" -> Discipline.BRYSTSV??MNING;
                default -> null;
            });
        }
        return returnArray;
    }
}
