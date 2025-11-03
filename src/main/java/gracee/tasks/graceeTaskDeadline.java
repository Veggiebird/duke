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

    private final LocalDate deadlineDate;
    private final LocalTime deadlineTime;

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

        this.deadlineDate = date;
        this.deadlineTime = time;
    }

    public graceeTaskDeadline(String description, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }

    /**
     * Return string for deadline task with description and datetime.
     * @return
     */

    @Override
    public String toString(){
        DateTimeFormatter displayDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter displayTime = DateTimeFormatter.ofPattern("HH:mm");
        return "Deadline | " + getStatus() + " | " + description + " | " + deadlineDate.format(displayDate) + " | " + deadlineTime.format(displayTime);
    }
}
