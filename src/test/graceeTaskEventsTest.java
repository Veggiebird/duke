package gracee.tasks;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class graceeTaskEventsTest{

    @Test
    void createEventSuccss(){
        graceeTaskEvents event = new graceeTaskEvents(
                "Sales 50% off workmanship",
                "2025-11-02", "0000",
                "2025-11-10", "0000"
        );

        String output = event.toString();
        assertTrue(output.contains("Sales 50% off workmanship"));
        assertTrue(output.contains("00:00"));
    }

    @Test
    void createEventWrongDate(){
        Exception ex = assertThrows(IllegalArgumentException.class,()->{

            new graceeTaskEvents(
                    "Any Event",
                    "2025-10-01", "0000",
                    "2025-09-30", "0000"
            )
        });

        assertTrue(ex.getMessage().contains("End date time cannot earlier than start time"));
    }

}