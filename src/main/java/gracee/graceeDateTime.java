package gracee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

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

    public static LocalDate parseDateFlexible(String text) {
        if(text == null || text.trim().isEmpty()){
            throw new IllegalArgumentException("Date cannot be empty");
        }

        String s = text.trim();
        for(DateTimeFormatter f : DATE_FORMATS){
            try{
                return LocalDate.parse(s, f);
            } catch (DateTimeParseException ignored){}
        }

        throw new IllegalArgumentException("Invalid format. Please try '11 Oct 2025' or '11102025' ");
    }

public static LocalTime parseTimeFlexible(String text) {
    if(text == null || text.trim().isEmpty()){
        throw new IllegalArgumentException("Time cannot be empty");
    }

    String s = text.trim().replaceAll("\\s+","");
    for(DateTimeFormatter f : TIME_FORMATS){
        try{
            return LocalTime.parse(s, f);
        } catch (DateTimeParseException ignored){}
    }

    throw new IllegalArgumentException("Invalid format. Please try '1800' or '18:00' ");
}
}
