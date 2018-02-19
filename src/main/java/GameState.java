

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
    public String word;
    public int g;
    public int wrong;
    public int h;

    ArrayList<Character> got;
    ArrayList<Character> not;

    public Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public GameState(String target, int g, int h) {
        this.word = target;
        not = new ArrayList<Character>();
        got = new ArrayList<Character>();

        for(int i = 0; i < target.length(); ++i) {
            if (!not.contains(Character.toLowerCase(target.charAt(i))))
                not.add(Character.toLowerCase(target.charAt(i)));
        }
        //System.out.println(missing);

        this.g = 0;
        wrong = g;
        this.h = h;
    }

    void showWord() {
        for (int i = 0; i < word.length(); ++i) {
            System.out.println(got);
            if (got.contains(word.charAt(i))) {

                System.out.print(word.charAt(i)+"("+i+")");

            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
        // System.out.println(missing);
    }

    boolean guessLetter() {
        int i;
        char letter;

        System.out.print("Guess a letter or word (? for a hint): ");

        String str = sc.next().toLowerCase();

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
                System.out.println(not.get(i));
                System.out.println(word.length());
                if(Character.toUpperCase(letter) == (word.charAt(0))){

                    got.add(Character.toUpperCase(not.get(i)));
                    got.add(Character.toLowerCase(letter));

                }else{

                    got.add(Character.toLowerCase(letter));
            }

                not.remove(i);


                g++;
                return true;
            }
        }

        g++; // One more guess
        wrong--;
        return false;
    }

    boolean won() {
        if (not.size() == 0) return true; else return false;
    }

    boolean lost() {
        if (not.size() > 0 && wrong == 0) return true; else return false;
    }

    void hint() {
        if (h == 0) {
            System.out.println("No more hints allowed");
        }

        System.out.print("Try: ");
        System.out.println(not.get((int)(Math.random()*not.size())));
    }
}
