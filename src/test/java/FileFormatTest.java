import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class FileFormatTest {

    @Test(expected = Exception.class)
    public void wrongFormatTest(){

        String[] args = { "--guesses", "2", "--hints", "4", "word_bad.txt" };

        Hangman hangman = new Hangman();

        hangman.main(args);

    }
}
