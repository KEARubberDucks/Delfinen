package FileAndDatabase;
import Enums.Discipline;
import Swimmers.CompetitiveResult;
import Swimmers.CompetitiveSwimmer;
import Swimmers.Swimmer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
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
                if (swimmer instanceof CompetitiveSwimmer && ((CompetitiveSwimmer) swimmer).getResults().isEmpty() == false){
                    output.print(swimmer);
                    output.print("; " + ((CompetitiveSwimmer) swimmer).getResults().get(0).getTimeInSeconds());
                    DateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    output.print("; " + outputDateFormat.format(((CompetitiveSwimmer) swimmer).getResults().get(0).getDate()));
                    output.println("; " + ((CompetitiveSwimmer) swimmer).getResults().get(0).getPlace());
                } else {
                    output.println(swimmer);
                }

            }
        }
    }
    public ArrayList<Swimmer> loadSwimmer() throws FileNotFoundException{
        ArrayList<Swimmer> returnList = new ArrayList<>();
        Swimmer swimmerToAdd;
        input = new Scanner(file);
        while (input.hasNextLine()){
            String[] attributeList = input.nextLine().split("; ");
            if (attributeList[3].equals("ja")) {
                swimmerToAdd = new CompetitiveSwimmer(
                        attributeList[0],
                        Integer.parseInt(attributeList[1]),
                        attributeList[2].equals("ja"),
                        true,
                        attributeList[4].equals("ja"),
                        attributeList[5],
                        getDiscipline(attributeList[6])
                );
                try {
                    //TODO: den fejler med den første Competitive Svømmer og laver dem uden Results
                    getBestResults(Integer.parseInt(attributeList[7]), setDate(attributeList[8]), attributeList[9], setDiscipline(attributeList[6]), (CompetitiveSwimmer) returnList.get(returnList.size()-1));
                } catch (ClassCastException e) {
                    System.out.println("ERROR: kunne ikke loade beste resultater fra database");
                }
            } else {
                swimmerToAdd = new Swimmer(
                        attributeList[0],
                        Integer.parseInt(attributeList[1]),
                        attributeList[2].equals("ja"),
                        false,
                        attributeList[4].equals("ja")
                );
            }
            returnList.add(swimmerToAdd);
        }
        return returnList;
    }

    private Date setDate(String s){
        Date newDate = null;
        try {
            newDate = new SimpleDateFormat("dd/MM/yyyy").parse(s);
        } catch (ParseException e){
            System.out.println("ERROR: kunne ikke hente fileHandler til Results i database");
        }
        return newDate;
    }

    private ArrayList<Discipline> getDiscipline(String s) {
        String[] disciplines = s.split(", ");
        ArrayList<Discipline> returnArray = new ArrayList<>();
        for (String discipline : disciplines) {
            returnArray.add(switch (discipline) {
                case "BUTTERFLY" -> Discipline.BUTTERFLY;
                case "CRAWL" -> Discipline.CRAWL;
                case "RYGCRAWL" -> Discipline.RYGCRAWL;
                case "BRYSTSVØMNING" -> Discipline.BRYSTSVØMNING;
                default -> null;
            });
        }
        return returnArray;
    }

    private Discipline setDiscipline(String s){
        Discipline chosenDiscipline;
        switch (s){
            case "BUTTERFLY" -> chosenDiscipline = Discipline.BUTTERFLY;
            case "CRAWL" -> chosenDiscipline = Discipline.CRAWL;
            case "RYGCRAWL" -> chosenDiscipline = Discipline.RYGCRAWL;
            case "BRYSTSVØMNING" -> chosenDiscipline = Discipline.BRYSTSVØMNING;
            default -> chosenDiscipline = null;
        }
        return chosenDiscipline;
    }

    private void getBestResults(int timeInSeconds, Date date, String place, Discipline discipline, CompetitiveSwimmer swimmer){
        ArrayList<CompetitiveResult> returnArray = new ArrayList<>();

        swimmer.createCompetitiveResult(timeInSeconds, date, place, discipline);
    }
}
