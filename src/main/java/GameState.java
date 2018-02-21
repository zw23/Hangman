


import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameState {
    public String word;
    public int numberOfGuessesTook;
    public int remainingGuesses;
    public int hintsLeft;

    ArrayList<Character> got;
    ArrayList<Character> not;
    private char space = ' ';




    public GameState(String target, int maxGuess, int maxHints) {
        this.word = target;
        not = new ArrayList<Character>();
        got = new ArrayList<Character>();

        for(int i = 0; i < target.length(); ++i) {


            if (target.charAt(i) != space && !not.contains(Character.toLowerCase(target.charAt(i))) )
                not.add(Character.toLowerCase(target.charAt(i)));

        }


        this.numberOfGuessesTook = 0;
        remainingGuesses = maxGuess;
        this.hintsLeft = maxHints;
    }

    void showWord() {
        for (int i = 0; i < word.length(); ++i) {
            //System.out.println(got);
            if (got.contains(word.charAt(i))) {

                System.out.print(word.charAt(i));

            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
        System.out.println("Guesses remaining: " + remainingGuesses);
        System.out.println("hints remaining: " + hintsLeft);

    }


    boolean guessLetter(){

         return guessLetter(new Scanner(System.in).useDelimiter("\n"));
    }

    boolean guessLetter(Scanner sc) {
        int i;
        char letter;
        String fullWord = "";
        boolean validLetter = false;
        Pattern p = Pattern.compile("[a-zA-Z?]+");

        String str = " ";

        System.out.print("Guess a letter or word (? for a hint): ");

        while(!validLetter){


            str = sc.nextLine().toLowerCase();
            if(str.length() <= 1){
                Matcher match = p.matcher(str);

                validLetter = match.matches();

                if(!validLetter)System.out.print("Please enter a valid input[a-zA-Z] (? for a hint): ");
            }else{
                fullWord = word.toLowerCase();


                validLetter = true;
            }



        }


       // in = sc.next().toString().toLowerCase();
        if (str.length() > 1) {

            if (str.equals(fullWord)) {
                not.clear();
                numberOfGuessesTook++;
                return message(true);
            } else return message(false);
        }

        letter = str.charAt(0);

        if (letter == '?') {
            if(hintsLeft != 0){
                hintsLeft--;
                hint();
                return true;
            }else{
                System.out.println("SORRY, You have used all your hints.");
                return false;
            }

        }
        for(int j = 0; j<got.size();++j){
            if(got.get(j) == letter){
                System.out.println("This letter is already guessed, please enter another letter.");
                return false;
            }
        }
        for(i = 0; i < not.size(); ++i) { // Loop over the not got
            if (not.get(i) == letter) {

                if(Character.toUpperCase(letter) == (word.charAt(0))){

                    got.add(Character.toUpperCase(not.get(i)));
                    got.add(Character.toLowerCase(letter));

                }else{

                    got.add(Character.toLowerCase(letter));
            }

                not.remove(i);


                numberOfGuessesTook++;
                return message(true);
            }
        }


        numberOfGuessesTook++; // One more guess
        remainingGuesses--;
        return message(false);
    }

    boolean message(boolean result){
        if(result){
            System.out.println("Good guess!");
            return true;
        }else{
            System.out.println("Wrong guess!");
            return false;
        }
    }


    boolean won() {
        if (not.size() == 0) return true; else return false;
    }

    boolean lost() {
        if (not.size() > 0 && remainingGuesses == 0) return true; else return false;
    }

    void hint() {

        System.out.print("Try: ");
        System.out.println(not.get((int)(Math.random()*not.size())));
    }
}
