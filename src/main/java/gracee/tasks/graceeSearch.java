package gracee.tasks;

import gracee.graceeDateTime;
import gracee.storage.graceeStorage;
import gracee.tasks.graceeTaskDetails;
import gracee.tasks.graceeTaskDeadline;
import gracee.tasks.graceeTaskEvents;
import gracee.tasks.graceeTaskTodo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class graceeSearch {

    private final graceeStorage storage;

    public graceeSearch() {

        this.storage = new graceeStorage();
    }

    public List<graceeTaskDetails> searchDate(LocalDate searchDate){
        List<graceeTaskDetails> allTasks = storage.load();
        List<graceeTaskDetails> matchingTasks = new ArrayList<>();

        for(graceeTaskDetails task : allTasks){
            if(taskDate(task, searchDate)){
                matchingTasks.add(task);
                System.out.println("Added task " + task.toString());
            }
        }

        return matchingTasks;
    }

    private boolean taskDate(graceeTaskDetails task, LocalDate searchDate){

        if(task instanceof graceeTaskTodo){
            return false;
        } else if(task instanceof graceeTaskDeadline d){
            return d.getDeadlineDate().equals(searchDate);
        } else if (task instanceof graceeTaskEvents e){
            return !searchDate.isBefore(e.getFromDate()) && !searchDate.isAfter(e.getToDate());
        }
        return false;
    }

    public List<graceeTaskDetails> searchDateRange(LocalDate fromDate, LocalDate toDate){
        List<graceeTaskDetails> allTasks = storage.load();
        List<graceeTaskDetails> matchingRangeTasks = new ArrayList<>();

        for(graceeTaskDetails task : allTasks){
            if(taskDateRange(task,fromDate,toDate)){
                matchingRangeTasks.add(task);
            }
        }

        return matchingRangeTasks;
    }

    private boolean taskDateRange(graceeTaskDetails task, LocalDate fromDate, LocalDate toDate){

        if(task instanceof graceeTaskTodo){
            return false;
        } else if(task instanceof graceeTaskDeadline d){
            LocalDate deadlineDate = d.getDeadlineDate();
            return !deadlineDate.isBefore(fromDate) && !deadlineDate.isAfter(toDate);
        } else if(task instanceof graceeTaskEvents e){
            return !e.getToDate().isBefore(fromDate) && !e.getFromDate().isAfter(toDate);
        }

        return false;
    }

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

    public List<graceeTaskDetails> searchKeyword(String keyword){
        List<graceeTaskDetails> allTasks = storage.load();
        List<graceeTaskDetails> matchingTasks = new ArrayList<>();

        String lowerCaseKW = keyword.toLowerCase();
        for(graceeTaskDetails task : allTasks){
            if(taskContainsKeyword(task,lowerCaseKW)){
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    private boolean taskContainsKeyword(graceeTaskDetails task, String lowerCaseKW){
        if(task.toString().toLowerCase().contains(lowerCaseKW)){
            return true;
        }

        return false;
    }
}
