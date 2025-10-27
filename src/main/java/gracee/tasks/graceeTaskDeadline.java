package gracee.tasks;

import gracee.graceeDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles deadline task
 */

public class graceeTaskDeadline extends graceeTaskDetails {

    private final LocalDateTime deadline;

    /**
     * Create new deadline task with date and time
     * @param description
     * @param byDate
     * @param byTime
     */
    public graceeTaskDeadline(String description, String byDate, String byTime) {
        super(description);

        LocalDate date = graceeDateTime.parseDateFlexible(byDate);
        LocalTime time = graceeDateTime.parseTimeFlexible(byTime);

        this.deadline = LocalDateTime.of(date, time);
    }

    public graceeTaskDeadline(String description, LocalDateTime deadline){
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Return string for deadline task with description and datetime.
     * @return
     */

    @Override
    public String toString(){
        DateTimeFormatter display = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Deadline | " + getStatus() + " | " + description + " | by " + deadline.format(display);
    }
}
