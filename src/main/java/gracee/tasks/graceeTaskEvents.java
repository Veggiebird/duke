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
     * @param description Event description
     * @param fromDate Event from Date
     * @param fromTime Event from Time
     * @param toDate Event to Date
     * @param toTime Event to Time
     */
    public graceeTaskEvents(String description, String fromDate, String fromTime, String toDate, String toTime) {
        super(description);

        assert description != null && !description.isEmpty() : "Description cannot be null or empty";

        LocalDate parseFromDate = graceeDateTime.parseDateFlexible(fromDate);
        LocalTime parseFromTime = graceeDateTime.parseTimeFlexible(fromTime);

        LocalDate parseToDate = graceeDateTime.parseDateFlexible(toDate);
        LocalTime parseToTime = graceeDateTime.parseTimeFlexible(toTime);

        assert parseToDate.isBefore(parseFromDate) : "Start date must be earlier than end date";

        this.fromDate = parseFromDate;
        this.fromTime = parseFromTime;
        this.toDate = parseToDate;
        this.toTime = parseToTime;

        if (parseToDate.isBefore(parseFromDate) || (parseToDate.equals(parseFromDate) && parseToTime.isBefore(parseFromTime))) {
            throw new IllegalArgumentException("ERROR! End date time cannot be before Start date time.");
        }
    }

    /**
     *
     * @param description Event description
     * @param fromDate Event from Date
     * @param fromTime Event from Time
     * @param toDate Event to Date
     * @param toTime Event to Time
     * @throws IllegalArgumentException if the end date/time is earlier than start date/time
     */

    public graceeTaskEvents(String description, LocalDate fromDate, LocalTime fromTime,LocalDate toDate, LocalTime toTime) {
        super(description);

        if (toDate.isBefore(fromDate) || (toDate.equals(fromDate) && toTime.isBefore(fromTime))) {
            throw new IllegalArgumentException("ERROR! End date time cannot be before Start date time.");
        }

        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }


    /**
     * Return start date
     * @return from Date
     */
    public LocalDate getFromDate(){
        return  fromDate;
    }

    /**
     * Return start time
     * @return from Time
     */

    public LocalTime getFromTime(){
        return fromTime;
    }

    /**
     * Return end date
     * @return To date
     */

    public LocalDate getToDate(){
        return toDate;
    }

    /**
     * Return end time
     * @return to time
     */

    public LocalTime getToTime(){
        return toTime;
    }

    /**
     * Return formatted string for events:
     * Events | status | description | from Date | from Time | to Date | to Time
     * @return Event string
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

