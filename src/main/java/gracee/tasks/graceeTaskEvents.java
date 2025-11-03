package gracee.tasks;

import gracee.graceeDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Handle events with start and end time
 */

public class graceeTaskEvents extends graceeTaskDetails {
    private final LocalDate fromDate;
    private final LocalTime fromTime;

    private final LocalDate toDate;
    private final LocalTime toTime;

    /**
     * Create new event task with date and time
     * @param description
     * @param fromDate
     * @param fromTime
     * @param toDate
     * @param toTime
     */
    public graceeTaskEvents(String description, String fromDate, String fromTime, String toDate, String toTime) {
        super(description);

        LocalDate parseFromDate = graceeDateTime.parseDateFlexible(fromDate);
        LocalTime parseFromTime = graceeDateTime.parseTimeFlexible(fromTime);

        LocalDate parseToDate = graceeDateTime.parseDateFlexible(toDate);
        LocalTime parseToTime = graceeDateTime.parseTimeFlexible(toTime);

        this.fromDate = parseFromDate;
        this.fromTime = parseFromTime;
        this.toDate = parseToDate;
        this.toTime = parseToTime;

        if(parseToDate.isBefore(parseFromDate) || (parseToDate.equals(parseFromDate) && parseToTime.isBefore(parseFromTime))) {
            throw new IllegalArgumentException("ERROR! End date time cannot be before Start date time.");
        }
    }

    public graceeTaskEvents(String description, LocalDate fromDate, LocalTime fromTime,LocalDate toDate, LocalTime toTime) {
        super(description);

        if(toDate.isBefore(fromDate) || (toDate.equals(fromDate) && toTime.isBefore(fromTime))) {
            throw new IllegalArgumentException("ERROR! End date time cannot be before Start date time.");
        }

        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    public LocalDate getFromDate(){
        return  fromDate;
    }

    public LocalTime getFromTime(){
        return fromTime;
    }

    public LocalDate getToDate(){
        return toDate;
    }

    public LocalTime getToTime(){
        return toTime;
    }

    /**
     * Display string for events with start and end datetime.
     * @return
     */
    @Override
    public String toString(){

        DateTimeFormatter displayFromDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter displayFromTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter displayToDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter displayToTime = DateTimeFormatter.ofPattern("HH:mm");

        return "Event" + " | " + getStatus() + " | " + description + " | " + fromDate.format(displayFromDate) + " | " + fromTime.format(displayFromTime) +
                " | " + toDate.format(displayToDate) + " | " + toTime.format(displayToTime);
    }
}

