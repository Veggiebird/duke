package gracee.tasks;

import gracee.graceeDateTime;
import java.time.LocalDate;
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
     * @param description Deadline Task description
     * @param byDate Deadline by date
     * @param byTime Deadline by time
     */
    public graceeTaskDeadline(String description, String byDate, String byTime) {
        super(description);

        LocalDate date = graceeDateTime.parseDateFlexible(byDate);
        LocalTime time = graceeDateTime.parseTimeFlexible(byTime);

        this.deadlineDate = date;
        this.deadlineTime = time;
    }

    /**
     * Construct new deadline task
     * @param description Deadline task description
     * @param deadlineDate Deadline date
     * @param deadlineTime Deadline time
     */

    public graceeTaskDeadline(String description, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     *
     * @return deadline date
     */

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    /**
     *
     * @return deadline time
     */

    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }

    /**
     * Return formatted deadline task string in format:
     * Deadline | status | description | by Date | by Time
     * @return string for deadline task with description and datetime.
     */

    @Override
    public String toString(){
        DateTimeFormatter displayDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter displayTime = DateTimeFormatter.ofPattern("HH:mm");
        return "Deadline | " + getStatus() + " | " + description + " | " + deadlineDate.format(displayDate) + " | " + deadlineTime.format(displayTime);
    }
}
