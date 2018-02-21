import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class FileFormatTest {

    @Test
    public void wrongFormatTest(){


        assertEquals(null,Words.randomWord("word_bad.txt"));
        assertEquals(null,Words.randomWord("word_bad2.txt"));
        assertEquals(null,Words.randomWord("word_badLong.txt"));

    }
}
