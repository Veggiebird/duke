package gracee.tasks;

import gracee.graceeDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class graceeTaskDeadline extends graceeTaskDetails {

    private final LocalDateTime deadline;


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

    @Override
    public String toString(){
        DateTimeFormatter display = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Deadline | " + getStatus() + " | " + description + " | by " + deadline.format(display);
    }
}
