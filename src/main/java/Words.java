

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Words {

    static String[] words1 = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
            "East Lothian", "Highland", "Dumfries and Galloway",
            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };
    static String[] words2 = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
            "France", "Germany", "Netherlands", "Spain", "Portugal",
            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };
    static String[] words3 = {"Dundee" };

    static ArrayList<String> customwords;

    public static String randomWord(int category) {
        if (category == 1)
            return words1[(int)(Math.random()*9)];
        if (category == 2)
            return words2[(int)(Math.random()*15)];
        if (category == 3)
            return words3[(int)(Math.random()*10)];
        if (category > 3 ||category<1){
            System.out.println("Please enter a valid number.");
            return null;
        }
        return null;
    }

    public static String randomWord(String wordsource) {
        String line;
        customwords = new ArrayList<String>();
        Pattern p = Pattern.compile("[a-zA-Z^\\s]+");


        try {
            FileReader file = new FileReader(wordsource);
            BufferedReader reader = new BufferedReader(file);
            while((line = reader.readLine()) != null) {
                Matcher match = p.matcher(line);
                boolean valid = match.matches();
                if(line.length()>=100) return null;
                if(valid) customwords.add(line);else return null;
            }
            return customwords.get((int)(Math.random()*customwords.size()));
        } catch(FileNotFoundException e) {
            System.out.println("File error");
            return "";
        } catch(IOException e) {
            System.out.println("IO error");
            return "";
        }
    }
}
