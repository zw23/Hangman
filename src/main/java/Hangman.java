

import java.util.InputMismatchException;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        GameState game; CommandOpts opts;
        boolean correct;

        opts = new CommandOpts(args);

        if (opts.wordsource == "") {

            System.out.println("  1. Counties");
            System.out.println("  2. Countries");
            System.out.println("  3. Cities");

            System.out.print("Pick a category:");

            String word = null;
            while(word == null){
                try {
                    word = Words.randomWord(sc.nextInt());
                }catch (InputMismatchException e){
                    System.out.println("Please enter an integer(1-3).");
                    sc.nextLine();
                }
            }

            game = new GameState(word, opts.maxguesses, opts.maxhints);
        }
        else {
            game = new GameState(Words.randomWord(opts.wordsource) , opts.maxguesses, opts.maxhints);
        }

        while(!game.won() && !game.lost()) {
            game.showWord();
            game.guessLetter();

        }

        if (game.won()) {
            System.out.println("Well done!");
            System.out.println("You took " + game.numberOfGuessesTook + " guesses");
        } else {
            System.out.println("You lost! The word was " + game.word);
        }
    }

}
