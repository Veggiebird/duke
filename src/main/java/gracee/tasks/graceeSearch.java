package gracee.tasks;

import gracee.storage.graceeStorage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handle search of task based on date / date range / keyword in storage file
 */

public class graceeSearch {

    /**
     * Storage handler to retrieve data from file
     */
    private final graceeStorage storage;

    public graceeSearch() {

        this.storage = new graceeStorage();
    }

    /**
     *
     * @param searchDate Search task based on date
     * @return Task(s) that matched the date
     */
    public List<graceeTaskDetails> searchDate(LocalDate searchDate){

        List<graceeTaskDetails> allTasks = storage.load();
        List<graceeTaskDetails> matchingTasks = new ArrayList<>();

        for (graceeTaskDetails task : allTasks){
            if (isTaskDate(task, searchDate)){
                matchingTasks.add(task);
                System.out.println("Added task " + task.toString());
            }
        }

        return matchingTasks;
    }

    /**
     *
     * @param task Task to evaluate if matched the requested date
     * @param searchDate The date to check against
     * @return If matching date task is found in file
     */

    private boolean isTaskDate(graceeTaskDetails task, LocalDate searchDate){

        if (task instanceof graceeTaskTodo){
            return false;
        } else if (task instanceof graceeTaskDeadline d){
            return d.getDeadlineDate().equals(searchDate);
        } else if (task instanceof graceeTaskEvents e){
            return !searchDate.isBefore(e.getFromDate()) && !searchDate.isAfter(e.getToDate());
        }
        return false;
    }

    /**
     *
     * @param fromDate From date range to check
     * @param toDate To date range to check
     * @return Matching task(s) within given date range
     */

    public List<graceeTaskDetails> searchDateRange(LocalDate fromDate, LocalDate toDate){
        List<graceeTaskDetails> allTasks = storage.load();
        List<graceeTaskDetails> matchingRangeTasks = new ArrayList<>();

        for (graceeTaskDetails task : allTasks){
            if (isTaskDateRange(task,fromDate,toDate)){
                matchingRangeTasks.add(task);
            }
        }

        return matchingRangeTasks;
    }

    /**
     *
     * @param task Task to check if fall within given date range
     * @param fromDate Given date range from Date
     * @param toDate Given date range to Date
     * @return if task is within given date range
     */

    private boolean isTaskDateRange(graceeTaskDetails task, LocalDate fromDate, LocalDate toDate){

        if (task instanceof graceeTaskTodo){
            return false;
        } else if (task instanceof graceeTaskDeadline d){
            LocalDate deadlineDate = d.getDeadlineDate();
            return !deadlineDate.isBefore(fromDate) && !deadlineDate.isAfter(toDate);
        } else if (task instanceof graceeTaskEvents e){
            return !e.getToDate().isBefore(fromDate) && !e.getFromDate().isAfter(toDate);
        }

        return false;
    }

    /**
     * Extract date pattern
     * @param line
     * @return Date in specific format
     */

    private List<LocalDate> extractDatesFromLine(String line){
        List<LocalDate> dates = new ArrayList<>();
        Pattern pattern = java.util.regex.Pattern.compile("\\b\\d{2}/\\d{2}/\\d{4}\\b");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String dateStr = matcher.group();
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            dates.add(date);
        }

        return dates;
    }

    /**
     * Find task based on keyword
     * @param keyword Keyword to check
     * @return Task with matching keyword
     */
    public List<graceeTaskDetails> searchKeyword(String keyword){
        List<graceeTaskDetails> allTasks = storage.load();
        List<graceeTaskDetails> matchingTasks = new ArrayList<>();

        String lowerCaseKW = keyword.toLowerCase();
        for (graceeTaskDetails task : allTasks){
            if (isTaskContainsKeyword(task,lowerCaseKW)){
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    /**
     *
     * @param task Task to check
     * @param lowerCaseKW Convert keyword to lower case
     * @return if any task contains keyword
     */

    private boolean isTaskContainsKeyword(graceeTaskDetails task, String lowerCaseKW){
        if (task.toString().toLowerCase().contains(lowerCaseKW)){
            return true;
        }
        return false;
    }
}
