package gracee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * To capture different user input date and time format type and parse into a standard date time format
 */

public class graceeDateTime {
    private graceeDateTime(){}

    private static final Locale EN = Locale.ENGLISH;

    private static final DateTimeFormatter[] DATE_FORMATS = new DateTimeFormatter[]{
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("ddMMyyyy").toFormatter(EN),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d MMM uuuu").toFormatter(EN),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d MMMM uuuu").toFormatter(EN),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd/MM/uuuu").toFormatter(EN),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MM-uuuu").toFormatter(EN)
    };

    private static final DateTimeFormatter[] TIME_FORMATS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("HHmm"),
            DateTimeFormatter.ofPattern("HH:mm"),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("h:mma").toFormatter(EN),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("ha").toFormatter(EN)
    };

    /**
     * Parse date
     * @param text
     * @return
     */
    public static LocalDate parseDateFlexible(String text) {

        assert text != null : "Date cannot be empty";

        if (text == null || text.trim().isEmpty()){
            throw new IllegalArgumentException("Date cannot be empty");
        }

        String s = text.trim();
        for (DateTimeFormatter f : DATE_FORMATS){
            try{

                LocalDate parsed = LocalDate.parse(s, f);

                if (!parsed.isAfter(LocalDate.now())){
                    throw new IllegalArgumentException(
                            "Date must be a future date! You've entered " + parsed
                    );
                }
                return parsed;
            } catch (DateTimeParseException ignored){}
        }

        throw new IllegalArgumentException(
                "Invalid format. Please try '11 Oct 2025' or '11102025'."
        );
    }

     /**
     * Parse time
     * @param text
     * @return
     */
    public static LocalTime parseTimeFlexible(String text) {

        assert text != null : "Time cannot be empty";

        if (text == null || text.trim().isEmpty()){
            throw new IllegalArgumentException("Time cannot be empty");
        }

        String s = text.trim().replaceAll("\\s+","");
        for (DateTimeFormatter f : TIME_FORMATS){
            try{
                return LocalTime.parse(s, f);
            } catch (DateTimeParseException ignored){}
        }

        throw new IllegalArgumentException("Invalid format. Please try '1800' or '18:00' ");
    }
}
