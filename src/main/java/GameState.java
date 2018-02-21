


import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
    public String word;
    public int numberOfGuessesTook;
    public int remainingGuesses;
    public int hintsLeft;

    ArrayList<Character> got;
    ArrayList<Character> not;





    public GameState(String target, int maxGuess, int maxHints) {
        this.word = target;
        not = new ArrayList<Character>();
        got = new ArrayList<Character>();

        for(int i = 0; i < target.length(); ++i) {
            if (!not.contains(Character.toLowerCase(target.charAt(i))))
                not.add(Character.toLowerCase(target.charAt(i)));
        }
        //System.out.println(missing);

        this.numberOfGuessesTook = 0;
        remainingGuesses = maxGuess;
        this.hintsLeft = maxHints;
    }

    void showWord() {
        for (int i = 0; i < word.length(); ++i) {
            //System.out.println(got);
            if (got.contains(word.charAt(i))) {

                System.out.print(word.charAt(i)+"("+i+")");

            } else {
                System.out.print("-");
            }
        }
        System.out.println("");

    }


    boolean guessLetter(){
         return guessLetter(new Scanner(System.in).useDelimiter("\n"));
    }

    boolean guessLetter(Scanner sc) {
        int i;
        char letter;

        System.out.print("Guess a letter or word (? for a hint): ");

        String str = sc.next().toLowerCase();
       // in = sc.next().toString().toLowerCase();
        if (str.length() > 1) {

            if (str==word) {
                not.clear();
                return true;
            } else return false;
        }

        letter = str.charAt(0);

        if (letter == '?') {
            hint();
            return false;
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
                return true;
            }
        }

        numberOfGuessesTook++; // One more guess
        remainingGuesses--;
        return false;
    }

    boolean won() {
        if (not.size() == 0) return true; else return false;
    }

    boolean lost() {
        if (not.size() > 0 && remainingGuesses == 0) return true; else return false;
    }

    void hint() {
        if (hintsLeft == 0) {
            System.out.println("No more hints allowed");
        }

        System.out.print("Try: ");
        System.out.println(not.get((int)(Math.random()*not.size())));
    }
}
