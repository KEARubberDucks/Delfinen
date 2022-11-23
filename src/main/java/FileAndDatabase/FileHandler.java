import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

package FileAndDatabase;

public class FileHandler {
    File file;
    PrintStream output;
    Scanner input;
    public FileHandler(){
        file = new File("Resources/Svømmer.txt");
    }
    //Svømmere skal med lille s
    public void saveSvømmer(ArrayList<Svømmer> Svømmere)throws FileNotFoundException{
        output = new PrintStream(file);
        if (!Svømmere.isEmpty()){
            for (Svømmer svømmer : Svømmere){
                output.println(svømmer.getName()+ "; "+svømmer.age+ "; " + svømmer.isActive+ "; " +svømmer.competetiv);

            }
        }
    }
    public ArrayList<Svømmer> loadSvømmer() throws FileNotFoundException{
        ArrayList<Svømmer> returnList = new ArrayList<>();
        Svømmer svømmerToAdd;
        input = new Scanner(file);
        while (input.hasNextLine()){
            String[] attributeList = input.nextLine().split("; ");
            svømmerToAdd = new Svømmer(
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
