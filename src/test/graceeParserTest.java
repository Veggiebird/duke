package gracee.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class graceeParserTest {

    @Test
    void parseCorrect(){
        assertEquals(graceeParser.MainCmd.SHOP, graceeParser.parseMain("1"));
        assertEquals(graceeParser.MainCmd.PRICE, graceeParser.parseMain("2"));
        assertEquals(graceeParser.MainCmd.HISTORY, graceeParser.parseMain("3"));
        assertEquals(graceeParser.MainCmd.TASKS, graceeParser.parseMain("4"));
        assertEquals(graceeParser.MainCmd.EXIT, graceeParser.parseMain("5"));
    }

    @Test
    void parseInvalid(){
        assertEquals(graceeParser.MainCmd.INVALID, graceeParser.parseMain("x"));
        assertEquals(graceeParser.MainCmd.INVALID, graceeParser.parseMain(""));
        assertEquals(graceeParser.MainCmd.INVALID, graceeParser.parseMain("99"));
    }

    void parseSubTaskCorrect(){
        assertEquals(graceeParser.subTaskCmd.ADD, graceeParser.parseSubTask("1"));
        assertEquals(graceeParser.subTaskCmd.UPDATE, graceeParser.parseSubTask("2"));
        assertEquals(graceeParser.subTaskCmd.LIST, graceeParser.parseSubTask("3"));
        assertEquals(graceeParser.subTaskCmd.REMOVE, graceeParser.parseSubTask("4"));
        assertEquals(graceeParser.subTaskCmd.BACK, graceeParser.parseSubTask("5"));
    }

    void parseSubTaskInvalid(){
        assertEquals(graceeParser.subTaskCmd.INVALID, graceeParser.parseSubTask("abc"));
        assertEquals(graceeParser.subTaskCmd.INVALID, graceeParser.parseSubTask(""));
        assertEquals(graceeParser.subTaskCmd.INVALID, graceeParser.parseSubTask("6"));

    }
}