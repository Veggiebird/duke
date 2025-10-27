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
    private final LocalDateTime fromDT;
    private final LocalDateTime toDT;

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

        LocalDate ParseFromDate = graceeDateTime.parseDateFlexible(fromDate);
        LocalTime ParseFromTime = graceeDateTime.parseTimeFlexible(fromTime);

        LocalDate ParseToDate = graceeDateTime.parseDateFlexible(toDate);
        LocalTime ParseToTime = graceeDateTime.parseTimeFlexible(toTime);

        this.fromDT = LocalDateTime.of(ParseFromDate, ParseFromTime);
        this.toDT = LocalDateTime.of(ParseToDate, ParseToTime);

        if(toDT.isBefore(fromDT)) {
            throw new IllegalArgumentException("ERROR! End date time cannot be before Start date time.");
        }
    }

    public graceeTaskEvents(String description, LocalDateTime from, LocalDateTime to) {
        super(description);

        if(to.isBefore(from)) {
            throw new IllegalArgumentException("ERROR! End date time cannot be before Start date time.");
        }

        this.fromDT = from;
        this.toDT = to;
    }


    /**
     * Display string for events with start and end datetime.
     * @return
     */
    @Override
    public String toString(){

        DateTimeFormatter display = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return "Events | " + getStatus() + " | " + description + " | " + fromDT.format(display) + " to " + toDT.format(display);
    }
}

