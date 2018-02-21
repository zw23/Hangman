

import static org.junit.Assert.*;

import org.junit.Test;


import java.util.ArrayList;
import java.util.Scanner;

public class GameStateTest {
    String dundee = "Dundee";
    String edinburgh = "Edinburgh";
    String perthAndKinrosse = "Prth and Kinross";
    GameState game,game2;
    ArrayList<Character> got;


    @Test
    public void singleInputTest1() {



        //a new game with target word Dundee, input letter "d".
        game = new GameState(dundee,10,10);
        String input = "d\n";
        assertEquals(true,game.guessLetter(new Scanner(input)));

        //a new game with target word Edinburgh, input letter "c".
        game2 = new GameState(edinburgh,10,10);
        String input2 = "c\n";

        assertEquals(false,game2.guessLetter(new Scanner(input2)));


    }

    @Test
    public void arraylistTest() {

        ArrayList<Character> actual = new ArrayList<>();
        actual.add('D');
        actual.add('d');

        game = new GameState(dundee,10,10);

        //the got array should contain [D,d]
        String input = "d\n";
        game.guessLetter(new Scanner(input));
        assertEquals(game.got,actual);

        //the got array should still contain only [D,d]
        String input2 = "c\n";
        game.guessLetter(new Scanner(input2));
        assertEquals(game.got,actual);

        //the got array should now contain [D,d,e]
        String input3 = "e\n";
        actual.add('e');
        game.guessLetter(new Scanner(input3));
        assertEquals(game.got,actual);

        int notSize = 2;
        assertEquals(notSize,game.not.size());


    }

    @Test
    public void disallowInvalidInput(){

        int ExpectedRemainingGuesses = 9;
        game = new GameState(perthAndKinrosse,10,10);

        //A white space input should not be allowed.
        String input = " \n";
        game.guessLetter(new Scanner(input));

        assertEquals(game.remainingGuesses,ExpectedRemainingGuesses);
    }

}
