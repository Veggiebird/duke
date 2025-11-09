package gracee;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class graceeDateTimeTest {

    @Test
    void parse_ddMMyyy(){
        LocalDate date = graceeDateTime.parseDateFlexible("20092026");
    }

    @Test
    void parse_ddMMdd_withSlash(){
        LocalDate date = graceeDateTime.parseDateFlexible("20/09/2026");
        assertEquals(LocalDate.parse("20/09/2026"), date);
    }

    @Test
    void parse_dMMMyyy(){
        LocalDate date = graceeDateTime.parseDateFlexible("20 sep 2026");
        assertEquals(LocalDate.parse("20/09/2026"), date);
    }

    @Test
    void parse_dMMMMyyy(){
        LocalDate date = graceeDateTime.parseDateFlexible("20 september 2026");
        assertEquals(LocalDate.parse("20/09/2026"), date);
    }

    @Test
    void parse_dMMMyyy_caseSensitive(){
        LocalDate date = graceeDateTime.parseDateFlexible("20 SEP 2026");
        assertEquals(LocalDate.parse("20/09/2026"), date);
    }

    @Test
    void invalidMonth(){
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
            graceeDateTime.parseDateFlexible("20/13/2026")
        );
        assertTrue(ex.getMessage().contains("Invalid month"));
    }

    @Test
    void invalidDay(){
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                graceeDateTime.parseDateFlexible("32/09/2026")
        );
        assertTrue(ex.getMessage().contains("Invalid day"));
    }

    @Test
    void oldDate(){
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                graceeDateTime.parseDateFlexible("03/09/2024")
        );
        assertTrue(ex.getMessage().contains("Entered previous day, need to be future date"));
    }

    @Test
    void isEmpty(){
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                graceeDateTime.parseDateFlexible(null)
        );
        assertTrue(ex.getMessage().contains("Empty date"));
    }

    @Test
    void isOutscope(){
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                graceeDateTime.parseDateFlexible(20.11.2026)
        );
        assertTrue(ex.getMessage().contains("Invalid format"));
    }
}
